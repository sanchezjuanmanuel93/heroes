package com.hiberus.heroes.expection;

public class GenericException extends RuntimeException {
    public GenericException() {
    }

    public GenericException(String message) {
        super(message);
    }

    public GenericException(Throwable cause) {
        super(cause);
    }
}
