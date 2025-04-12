package io.ruslan.jirareportgenerator.model.converter;

import io.ruslan.jirareportgenerator.model.report.JiraComment;
import io.ruslan.jirareportgenerator.model.report.JiraIssue;
import io.ruslan.jirareportgenerator.model.report.JiraIssues;
import io.ruslan.jirareportgenerator.model.response.JiraResponse;

import java.util.List;
import java.util.stream.Collectors;

public class IssueConverter {

    private static final String JIRA_ISSUE_URL_PREFIX = "https://jira.atlassian.com/browse/";

    public static JiraIssues issuesFromResponse(JiraResponse response) {
        return new JiraIssues(response.issues().stream().map(issue -> {
            List<JiraComment> jiraComments = issue.fields().comment().comments().stream().map(comment ->
                            new JiraComment(comment.body(), comment.author().name()))
                    .collect(Collectors.toList());
            return new JiraIssue(
                    issue.fields().summary(),
                    issue.key(),
                    JIRA_ISSUE_URL_PREFIX + issue.key(),
                    issue.fields().issuetype().name(),
                    issue.fields().priority().name(),
                    issue.fields().issuetype().description(),
                    issue.fields().reporter().name(),
                    issue.fields().created(),
                    jiraComments);
        }).collect(Collectors.toList()));
    }
}
