package exceptions;

public class InvalidBoardSizeException extends RuntimeException{
    public InvalidBoardSizeException() {
    }

    public InvalidBoardSizeException(String message) {
        super(message);
    }

    public InvalidBoardSizeException(String message, Throwable cause) {
        super(message, cause);
    }
}
