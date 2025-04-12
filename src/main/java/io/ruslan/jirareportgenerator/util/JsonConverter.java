package io.ruslan.jirareportgenerator.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.ruslan.jirareportgenerator.model.report.JiraIssue;

public class JsonConverter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String convertIssueToJson(JiraIssue issue) throws Exception {
        return objectMapper.writeValueAsString(issue);
    }
}
