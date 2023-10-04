package exceptions;

public class InvalidBotCountException extends RuntimeException{
    public InvalidBotCountException() {
    }

    public InvalidBotCountException(String message) {
        super(message);
    }

    public InvalidBotCountException(String message, Throwable cause) {
        super(message, cause);
    }
}
