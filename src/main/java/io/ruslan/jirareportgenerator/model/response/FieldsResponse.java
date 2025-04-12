package io.ruslan.jirareportgenerator.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public record FieldsResponse(
        String summary,
        IssueTypeResponse issuetype,
        PriorityResponse priority,
        AuthorResponse reporter,
        Date created,
        CommentWrapperResponse comment
) {
}
