package org.lemonade.exceptions;

/**
 *
 */
public class QLExpressionException extends Exception {
    public QLExpressionException() {
        super();
    }

    public QLExpressionException(String message) {
        super(message);
    }

    public QLExpressionException(String message, Throwable cause) {
        super(message, cause);
    }

    public QLExpressionException(Throwable cause) {
        super(cause);
    }

    protected QLExpressionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
