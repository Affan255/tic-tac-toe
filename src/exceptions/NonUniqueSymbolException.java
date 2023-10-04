package exceptions;

public class NonUniqueSymbolException extends RuntimeException{
    public NonUniqueSymbolException() {
    }

    public NonUniqueSymbolException(String message) {
        super(message);
    }

    public NonUniqueSymbolException(String message, Throwable cause) {
        super(message, cause);
    }
}
