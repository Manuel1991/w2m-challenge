package org.w2m_challenge.fflix_service.exception;

import org.springframework.http.HttpStatus;

public class RestException extends RuntimeException{

    private final HttpStatus httpStatus;
    private final String message;

    public RestException(HttpStatus httpStatus, String message) {
        super();
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus(){
        return httpStatus;
    }

    public String getMessage(){
        return this.message;
    }

}
