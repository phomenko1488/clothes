package iam.phomenko.clothes.exception;

public class UsernameExistException extends Exception {
    public UsernameExistException() {
    }

    public UsernameExistException(String message) {
        super(message);
    }
}
