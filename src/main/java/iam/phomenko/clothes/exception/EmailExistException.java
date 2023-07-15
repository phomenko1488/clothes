package iam.phomenko.clothes.exception;

public class EmailExistException extends Throwable {
    public EmailExistException() {
    }

    public EmailExistException(String message) {
        super(message);
    }
}
