package org.w2m_challenge.fflix_service.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends RestException {
    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
