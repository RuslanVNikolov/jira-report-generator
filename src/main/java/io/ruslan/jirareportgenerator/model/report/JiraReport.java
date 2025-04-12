package io.ruslan.jirareportgenerator.model.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;

@Data
@Builder
@AllArgsConstructor
public class JiraReport {
    private ByteArrayResource fileData;
    private String fileName;
    private MediaType mediaType;
}
