package org.uva.taxfree.ql.ast;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.BitSet;

public class AntlrBuilder {

    private final File mInputFile;

    public AntlrBuilder(File inputFile) {
        mInputFile = inputFile;
    }

    protected ANTLRInputStream createInputStream() throws IOException {
        FileReader reader = new FileReader(mInputFile);
        return new ANTLRInputStream(reader);
    }

    protected TokenStream createTokenStream(TokenSource tokenSource) {
        return new CommonTokenStream(tokenSource);
    }

    protected ANTLRErrorListener createErrorListener() {
        return new ANTLRErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object o, int line, int column, String message, RecognitionException e) {
                throw new UnsupportedOperationException("(" + line + ":" + column + "):" + message);
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

    protected void walkParseTree(ParseTreeListener listener, ParserRuleContext context) {
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, context);
    }
}

