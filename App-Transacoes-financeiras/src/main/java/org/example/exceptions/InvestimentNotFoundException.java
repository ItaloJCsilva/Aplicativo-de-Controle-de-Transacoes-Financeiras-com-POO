package org.example.exceptions;

public class InvestimentNotFoundException extends RuntimeException {
    public InvestimentNotFoundException(String message) {
        super(message);
    }
}
