package io.ruslan.jirareportgenerator.model.report;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;

public record JiraReport(ByteArrayResource fileData, String fileName, MediaType mediaType) {
}
