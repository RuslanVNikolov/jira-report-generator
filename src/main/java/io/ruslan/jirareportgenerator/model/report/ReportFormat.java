package io.ruslan.jirareportgenerator.model.report;

import io.ruslan.jirareportgenerator.exceptions.InvalidReportFormatException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ReportFormat {
    JSON("json"),
    XML("xml");

    private final String value;

    public static ReportFormat fromString(String format) {
        try {
            return ReportFormat.valueOf(format.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidReportFormatException("Invalid report format: '" + format
                    + "'. Allowed values: " + Arrays.toString(ReportFormat.values()));
        }
    }
}

