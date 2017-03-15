package com.matthewchapman.ql.parsing;

import com.matthewchapman.ql.core.QLErrorLogger;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

/**
 * Created by matt on 24/02/2017.
 */
public class AntlrErrorListener extends BaseErrorListener {

    private QLErrorLogger logger;

    public AntlrErrorListener() {
        this.logger = new QLErrorLogger();
    }

    public QLErrorLogger getLogger() {
        return this.logger;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        logger.addError(line, charPositionInLine, "Parser", msg);
    }
}
