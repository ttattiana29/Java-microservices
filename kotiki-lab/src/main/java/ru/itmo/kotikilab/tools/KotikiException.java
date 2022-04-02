package ru.itmo.kotikilab.tools;
public class KotikiException extends RuntimeException {
    public KotikiException() {
    }

    public KotikiException(String message) {
        super(message);
    }
}
