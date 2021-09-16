package com.hiberus.heroes.expection;

public class InconsistentDataException extends RuntimeException {
    public InconsistentDataException() {
    }

    public InconsistentDataException(String message) {
        super(message);
    }

    public InconsistentDataException(Throwable cause) {
        super(cause);
    }
}
