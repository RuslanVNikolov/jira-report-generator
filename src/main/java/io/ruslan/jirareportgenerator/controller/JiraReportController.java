package io.ruslan.jirareportgenerator.controller;

import io.ruslan.jirareportgenerator.client.JiraClient;
import io.ruslan.jirareportgenerator.model.converter.IssueConverter;
import io.ruslan.jirareportgenerator.model.report.JiraReport;
import io.ruslan.jirareportgenerator.model.report.ReportFormat;
import io.ruslan.jirareportgenerator.service.JiraReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/reports/jira")
public class JiraReportController {

    private final JiraReportService jiraReportService;
    private final JiraClient jiraClient;

    @GetMapping
    public ResponseEntity<ByteArrayResource> getReport(@RequestParam(name = "format", defaultValue = "json") String format) {
        JiraReport report = jiraReportService.generateReport(IssueConverter.issuesFromResponse(jiraClient.fetchJiraIssues()), ReportFormat.fromString(format));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + report.fileName())
                .contentType(report.mediaType())
                .contentLength(report.fileData().contentLength())
                .body(report.fileData());
    }
}