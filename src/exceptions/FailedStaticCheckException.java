package exceptions;

public class FailedStaticCheckException extends RuntimeException { 
    public FailedStaticCheckException(String errorMessage) {
        super(errorMessage);
    }
}