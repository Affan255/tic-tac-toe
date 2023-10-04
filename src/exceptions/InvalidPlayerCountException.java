package exceptions;

public class InvalidPlayerCountException extends RuntimeException {
    public InvalidPlayerCountException() {
    }

    public InvalidPlayerCountException(String message) {
        super(message);
    }

    public InvalidPlayerCountException(String message, Throwable cause) {
        super(message, cause);
    }
}
