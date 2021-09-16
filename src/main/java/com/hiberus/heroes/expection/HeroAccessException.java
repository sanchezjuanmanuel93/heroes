package com.hiberus.heroes.expection;

public class HeroAccessException extends RuntimeException {

    public HeroAccessException() {
    }

    public HeroAccessException(String message) {
        super(message);
    }

    public HeroAccessException(Throwable cause) {
        super(cause);
    }
}
