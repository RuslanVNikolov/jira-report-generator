package io.ruslan.jirareportgenerator.controller.handler;

import io.ruslan.jirareportgenerator.exceptions.InvalidReportFormatException;
import io.ruslan.jirareportgenerator.model.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(InvalidReportFormatException.class)
    public ResponseEntity<ErrorResponse> handleInvalidReportFormatException(InvalidReportFormatException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(BAD_REQUEST).body(new ErrorResponse(BAD_REQUEST, ex.getMessage(), LocalDateTime.now()));
    }
}
