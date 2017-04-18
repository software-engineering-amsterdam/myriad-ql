package org.lemonade;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class QLParserErrorListener extends BaseErrorListener {

    private List<SyntaxErrorItem> items;

    public QLParserErrorListener() {
        this.items = new ArrayList<>();
    }

    public List<SyntaxErrorItem> getItems() {
        return items;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        items.add(new SyntaxErrorItem(line, charPositionInLine, msg));
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

        private int column;

        private String msg;

        SyntaxErrorItem(int line, int column, String msg) {
            this.line = line;
            this.column = column;
            this.msg = msg;
        }

        @Override
        public String toString() {
            return String.format("[%d:%d] %s", line, column, msg);
        }

    }

}
