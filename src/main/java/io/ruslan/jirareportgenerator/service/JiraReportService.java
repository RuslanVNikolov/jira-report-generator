package io.ruslan.jirareportgenerator.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.ruslan.jirareportgenerator.model.report.JiraIssues;
import io.ruslan.jirareportgenerator.model.report.JiraReport;
import io.ruslan.jirareportgenerator.model.report.ReportFormat;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class JiraReportService {

    @SneakyThrows
    public JiraReport generateReport(JiraIssues issues, ReportFormat format){
        String dateStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String fileName = "jira-report-" + dateStr + "." + format.getValue();
        MediaType mediaType;
        byte[] fileData;
        switch (format) {
            case XML -> {
                XmlMapper xmlMapper = new XmlMapper();
                fileData = xmlMapper.writeValueAsBytes(issues);
                mediaType = MediaType.APPLICATION_XML;
            }
            case JSON -> {
                ObjectMapper objectMapper = new ObjectMapper();
                fileData = objectMapper.writeValueAsBytes(issues);
                mediaType = MediaType.APPLICATION_JSON;
            }
            default -> throw new RuntimeException("Type not supported!");
        }
        ByteArrayResource resource = new ByteArrayResource(fileData);
        return new JiraReport(resource, fileName, mediaType);
    }
}
