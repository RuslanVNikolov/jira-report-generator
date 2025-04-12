package io.ruslan.jirareportgenerator.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.ruslan.jirareportgenerator.model.report.ReportFormat;

public class FailedToWriteReportException extends RuntimeException {
    private final ReportFormat format;

    public FailedToWriteReportException(JsonProcessingException e, ReportFormat format) {
        super(e);
        this.format = format;
    }

    @Override
    public String getMessage() {
        return "Failed to write report for " + format + "format: \n" + super.getMessage();
    }
}
