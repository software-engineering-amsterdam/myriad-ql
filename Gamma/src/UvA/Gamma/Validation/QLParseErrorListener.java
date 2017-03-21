package UvA.Gamma.Validation;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

/**
 * Created by Tjarco, 28-02-17.
 */
public class QLParseErrorListener extends BaseErrorListener {
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object o, int line, int charPositionInLine, String msg, RecognitionException e) {
        System.err.println("Your QL input could not be parsed correctly. Reason: line " +
                line + ":" + charPositionInLine + " " + msg);
        System.exit(1);
    }
}
