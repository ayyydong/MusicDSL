package exceptions;

public class FailedStaticCheckException extends RuntimeException { 
    public FailedStaticCheckException(String errorMessage) {
        super(errorMessage);
    }

    public FailedStaticCheckException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}