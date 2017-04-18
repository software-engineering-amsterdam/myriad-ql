package org.uva.taxfree.ql.ast;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.uva.taxfree.ql.model.SourceInfo;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.BitSet;

public class AntlrBuilder {

    private final File mInputFile;

    public AntlrBuilder(File inputFile) {
        mInputFile = inputFile;
    }

    // Unfortunately, we need to make these methods public, since the QlsStyleBuilder is in another package.
    public ANTLRInputStream createInputStream() throws IOException {
        FileReader reader = new FileReader(mInputFile);
        return new ANTLRInputStream(reader);
    }

    public TokenStream createTokenStream(TokenSource tokenSource) {
        return new CommonTokenStream(tokenSource);
    }

    public ANTLRErrorListener createErrorListener() {
        return new ANTLRErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object o, int line, int column, String message, RecognitionException e) {
                throw new UnsupportedOperationException("(" + line + ":" + column + "): " + message);
            }

            @Override
            public void reportAmbiguity(Parser parser, DFA dfa, int line, int column, boolean b, BitSet bitSet, ATNConfigSet atnConfigSet) {

            }

            @Override
            public void reportAttemptingFullContext(Parser parser, DFA dfa, int i, int i1, BitSet bitSet, ATNConfigSet atnConfigSet) {

            }

            @Override
            public void reportContextSensitivity(Parser parser, DFA dfa, int i, int i1, int i2, ATNConfigSet atnConfigSet) {

            }
        };
    }

    public void walkParseTree(ParseTreeListener listener, ParserRuleContext context) {
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, context);
    }

    public SourceInfo createSourceInfo(ParserRuleContext context) {
        int startLineNumber = context.getStart().getLine();
        int startColumn = context.getStart().getCharPositionInLine();
        int endLineNumber = context.getStop().getLine();
        int endColumn = calcEndColumn(startLineNumber, startColumn, endLineNumber, context);
        return new SourceInfo(startLineNumber, startColumn, endLineNumber, endColumn);
    }

    private int calcEndColumn(int startLineNumber, int endLineNumber, int startColumn, ParserRuleContext context) {
        int endColumn = context.getStop().getCharPositionInLine();
        if (startLineNumber == endLineNumber && startColumn == endColumn) {
            // Literals have an invalid endColumn, so we need to calculate it by ourselves
            endColumn = startColumn + context.getText().length();
        }
        return endColumn;
    }
}

