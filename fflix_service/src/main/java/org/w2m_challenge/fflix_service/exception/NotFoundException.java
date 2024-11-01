package org.w2m_challenge.fflix_service.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RestException {
    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
