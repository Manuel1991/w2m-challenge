package org.w2m_challenge.fflix_service.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.w2m_challenge.fflix_service.service.LoggerService;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private LoggerService logger;

    @ExceptionHandler(value = {BadRequestException.class, NotFoundException.class})
    private ResponseEntity<String> registerRestException(RestException e) {

        logger.error(e.getMessage());

        return ResponseEntity
                .status(e.getHttpStatus().value())
                .body(e.getMessage());
    }
}
