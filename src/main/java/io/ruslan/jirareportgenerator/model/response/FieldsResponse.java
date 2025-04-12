package io.ruslan.jirareportgenerator.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class FieldsResponse {
    private String summary;
    private IssueTypeResponse issuetype;
    private PriorityResponse priority;
    private AuthorResponse reporter;
    private Date created;
    private CommentWrapperResponse comment;
}
