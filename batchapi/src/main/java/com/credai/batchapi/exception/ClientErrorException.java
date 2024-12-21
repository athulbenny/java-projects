package com.credai.batchapi.exception;

public class ClientErrorException extends RuntimeException {

    public ClientErrorException(String message) {
        super(message);
    }

    public ClientErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}