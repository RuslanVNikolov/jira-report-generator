package io.ruslan.jirareportgenerator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.ruslan.jirareportgenerator.exceptions.FailedToWriteReportException;
import io.ruslan.jirareportgenerator.model.report.JiraIssues;
import io.ruslan.jirareportgenerator.model.report.JiraReport;
import io.ruslan.jirareportgenerator.model.report.ReportFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static io.ruslan.jirareportgenerator.model.report.ReportFormat.XML;

@RequiredArgsConstructor
@Service
@Slf4j
public class JiraReportService {

    private static final String REPORT_FILE_NAME_FORMAT = "jira-report-%s.%s";
    private static final String REPORT_FILE_NAME_DATE_FORMAT = "yyyyMMdd";

    public JiraReport generateReport(JiraIssues issues, ReportFormat format) {
        log.info("Generating report for issues {} in {} format", issues, format);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(REPORT_FILE_NAME_DATE_FORMAT);
        String dateStr = LocalDate.now().format(formatter);
        String fileName = String.format(REPORT_FILE_NAME_FORMAT, dateStr, format.getValue());
        MediaType mediaType;
        byte[] fileData;
        try {
            if (format == XML) {
                XmlMapper xmlMapper = new XmlMapper();
                fileData = xmlMapper.writeValueAsBytes(issues);
                mediaType = MediaType.APPLICATION_XML;
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                fileData = objectMapper.writeValueAsBytes(issues);
                mediaType = MediaType.APPLICATION_JSON;
            }
        } catch (JsonProcessingException e) {
            throw new FailedToWriteReportException(e, format);
        }
        ByteArrayResource resource = new ByteArrayResource(fileData);
        return new JiraReport(resource, fileName, mediaType);
    }
}
