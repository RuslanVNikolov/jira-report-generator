package io.ruslan.jirareportgenerator.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CommentResponse(AuthorResponse author, String body) {
}
