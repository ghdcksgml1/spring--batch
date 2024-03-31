package io.spring.batch.retry;

public class SkippableException extends RuntimeException {
    public SkippableException(String message) {
        super(message);
    }
}
