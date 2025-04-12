package io.ruslan.jirareportgenerator.model.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class JiraIssue {
    private String key;
    private String summary;
    private String description;
    private String issueType;
    private String issuePriority;
    private String reporter;
    private Date created;
    private String browseUrl;
    private List<JiraComment> comments;
}
