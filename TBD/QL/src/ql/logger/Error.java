package ql.logger;


/**
 * Created by Erik on 21-2-2017.
 */
public class Error {
    private final int row;
    private final String message;

    public Error(String message, int row) {
        this.message = message;
        this.row = row;
    }

    public int getRow() {
        return row;
    }

    public String getMessage() {
        return message;
    }
}
