package com.matthewchapman.ql.parsing;

import com.matthewchapman.ql.errorhandling.ErrorLogger;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

/**
 * Created by matt on 24/02/2017.
 */
class ParseTreeErrorListener extends BaseErrorListener {

    private final ErrorLogger logger;

    ParseTreeErrorListener() {
        this.logger = new ErrorLogger();
    }

    public ErrorLogger getLogger() {
        return this.logger;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        logger.addError(line, charPositionInLine, "Parser", msg);
    }
}
