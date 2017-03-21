package org.lemonade;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

/**
 *
 */
public class QLParserErrorListener extends BaseErrorListener {

    private List<SyntaxErrorItem> items;

    public QLParserErrorListener() {
        this.items = new ArrayList<SyntaxErrorItem>();
    }

    public List<SyntaxErrorItem> getItems() {
        return items;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        items.add(new SyntaxErrorItem(line, charPositionInLine, msg, offendingSymbol, e));
    }

    public boolean hasErrors() {
        return this.items.size() > 0;
    }

    @Override
    public String toString() {
        if (!hasErrors())
            return "0 errors";
        StringBuilder builder = new StringBuilder();
        for (SyntaxErrorItem s : items) {
            builder.append(String.format("%s\n", s));
        }
        return builder.toString();
    }

    public class SyntaxErrorItem {
        private int line;

        private Object offendingSymbol;

        private int column;

        private String msg;

        private RecognitionException oops;

        SyntaxErrorItem(int line, int column, String msg, Object symbol, RecognitionException oops) {
            this.line = line;
            this.column = column;
            this.msg = msg;
            this.offendingSymbol = symbol;
            this.oops = oops;
        }

        public int getLine() {
            return line;
        }

        @Override
        public String toString() {
            return String.format("[%d:%d] %s", line, column, msg);
        }

    }

}
