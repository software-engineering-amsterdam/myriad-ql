package org.lemonade.exeptions;

/**
 *
 */
public class QLOperatorException extends Exception {
    public QLOperatorException() {
        super();
    }

    public QLOperatorException(String message) {
        super(message);
    }

    public QLOperatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public QLOperatorException(Throwable cause) {
        super(cause);
    }

    protected QLOperatorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
