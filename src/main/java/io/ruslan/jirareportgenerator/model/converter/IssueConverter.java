package io.ruslan.jirareportgenerator.model.converter;

import io.ruslan.jirareportgenerator.model.report.JiraComment;
import io.ruslan.jirareportgenerator.model.report.JiraIssue;
import io.ruslan.jirareportgenerator.model.report.JiraIssues;
import io.ruslan.jirareportgenerator.model.response.JiraResponse;

import java.util.List;
import java.util.stream.Collectors;

public class IssueConverter {
    public static JiraIssues issuesFromResponse(JiraResponse response) {
        return new JiraIssues(response.getIssues().stream().map(issue -> {
            List<JiraComment> jiraComments = issue.getFields().getComment().getComments().stream().map(comment -> JiraComment.builder()
                    .text(comment.getBody())
                    .author(comment.getAuthor().getName())
                    .build()).collect(Collectors.toList());
            return JiraIssue.builder()
                    .summary(issue.getFields().getSummary())
                    .key(issue.getKey())
                    .browseUrl("https://jira.atlassian.com/browse/" + issue.getKey())
                    .issueType(issue.getFields().getIssuetype().getName())
                    .issuePriority(issue.getFields().getPriority().getName())
                    .description(issue.getFields().getIssuetype().getDescription())
                    .reporter(issue.getFields().getReporter().getName())
                    .created(issue.getFields().getCreated())
                    .comments(jiraComments)
                    .build();
        }).collect(Collectors.toList()));
    }
}
