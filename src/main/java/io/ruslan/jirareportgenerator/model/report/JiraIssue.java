package io.ruslan.jirareportgenerator.model.report;

import java.util.Date;
import java.util.List;

public record JiraIssue(
        String summary,
        String key,
        String browseUrl,
        String issueType,
        String issuePriority,
        String description,
        String reporter,
        Date created,
        List<JiraComment> comments
) {
}