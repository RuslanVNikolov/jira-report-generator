package io.ruslan.jirareportgenerator.client;

import io.ruslan.jirareportgenerator.model.response.JiraResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
@Slf4j
public class JiraClient {

    private static final String JIRA_BASE_URL = "https://jira.atlassian.com";
    private static final String SEARCH_ENDPOINT = "/rest/api/2/search";
    private static final String JQL_QUERY = "issuetype in (Bug, Documentation, Enhancement) and updated > startOfWeek()";
    private static final String FIELDS = "summary,key,issuetype,priority,description,reporter,created,comment";

    private final RestTemplate restTemplate = new RestTemplate();

    public JiraResponse fetchJiraIssues() {
        URI uri = UriComponentsBuilder.fromHttpUrl(JIRA_BASE_URL)
                .path(SEARCH_ENDPOINT)
                .queryParam("jql", JQL_QUERY)
                .queryParam("fields", FIELDS)
                .build()
                .encode()
                .toUri();

        ResponseEntity<JiraResponse> response = restTemplate.getForEntity(uri, JiraResponse.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to fetch Jira issues. HTTP Status: " + response.getStatusCode());
        }
    }
}
