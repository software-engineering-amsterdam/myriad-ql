package org.uva.taxfree.ast;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.uva.taxfree.gen.QLGrammarLexer;
import org.uva.taxfree.gen.QLGrammarParser;
import org.uva.taxfree.model.node.blocks.FormNode;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.BitSet;

public class AstBuilder {
    private final File mInputFile;

    public AstBuilder(File inputFile) throws IOException {
        mInputFile = inputFile;
    }

    private QLGrammarParser createGrammarParser(File inputFile) throws IOException {
        FileReader reader = new FileReader(inputFile);
        ANTLRInputStream inputStream = new ANTLRInputStream(reader);
        QLGrammarLexer qlGrammarLexer = new QLGrammarLexer(inputStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(qlGrammarLexer);
        return new QLGrammarParser(commonTokenStream);
    }


    public FormNode generateTree() throws IOException {
        ANTLRErrorListener errorListener = new ANTLRErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object o, int line, int column, String message, RecognitionException e) {
                throw new UnsupportedOperationException(e);
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
        QLGrammarParser parser = createGrammarParser(mInputFile);
        parser.addErrorListener(errorListener);
        parser.addErrorListener(errorListener);

        QLGrammarParser.FormContext formContext = parser.form();

        ParseTreeWalker walker = new ParseTreeWalker();
        GrammarListener listener = new GrammarListener();
        walker.walk(listener, formContext);
        return listener.getAst();
    }
}

