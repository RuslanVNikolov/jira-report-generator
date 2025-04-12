package io.ruslan.jirareportgenerator.util;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.ruslan.jirareportgenerator.model.report.JiraIssue;

public class XmlConverter {

    private static final XmlMapper xmlMapper = new XmlMapper();

    public static String convertIssueToXml(JiraIssue issue) throws Exception {
        return xmlMapper.writeValueAsString(issue);
    }
}
