package org.example.exceptions;

public class NoFundsEnoughtException extends RuntimeException {
    public NoFundsEnoughtException(String message) {
        super(message);
    }
}
