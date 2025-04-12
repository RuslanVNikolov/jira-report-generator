package it;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.ruslan.jirareportgenerator.Application;
import io.ruslan.jirareportgenerator.model.dto.response.JiraIssues;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class JiraReportIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testJsonReportGeneration() throws Exception {
        String url = "http://localhost:" + port + "/api/reports?format=json";
        ResponseEntity<byte[]> response = restTemplate.getForEntity(url, byte[].class);

        assertEquals(200, response.getStatusCode().value(), "HTTP status should be 200 OK");
        MediaType contentType = response.getHeaders().getContentType();
        assertNotNull(contentType, "Content-Type header must be present");
        assertEquals(MediaType.APPLICATION_JSON, contentType, "Content-Type must be application/json");

        byte[] body = response.getBody();
        assertNotNull(body, "Response body should not be null");
        ObjectMapper objectMapper = new ObjectMapper();
        JiraIssues issues = objectMapper.readValue(body, JiraIssues.class);
        assertNotNull(issues, "Deserialized JiraIssues should not be null");
    }

    @Test
    void testXmlReportGeneration() throws Exception {
        String url = "http://localhost:" + port + "/api/reports?format=xml";
        ResponseEntity<byte[]> response = restTemplate.getForEntity(url, byte[].class);

        assertEquals(200, response.getStatusCode().value(), "HTTP status should be 200 OK");
        MediaType contentType = response.getHeaders().getContentType();
        assertNotNull(contentType, "Content-Type header must be present");
        assertEquals(MediaType.APPLICATION_XML, contentType, "Content-Type must be application/xml");

        byte[] body = response.getBody();
        assertNotNull(body, "Response body should not be null");
        XmlMapper xmlMapper = new XmlMapper();
        JiraIssues issues = xmlMapper.readValue(body, JiraIssues.class);
        assertNotNull(issues, "Deserialized JiraIssues should not be null");
    }
}
