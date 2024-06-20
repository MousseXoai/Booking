package org.booking.bookingapp.exception;

public class UnauthorizeException extends RuntimeException{
    public UnauthorizeException(String message) {
        super(message);
    }
}
