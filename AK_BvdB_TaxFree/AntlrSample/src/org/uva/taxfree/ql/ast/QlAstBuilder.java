package org.uva.taxfree.ql.ast;

import org.uva.taxfree.ql.gen.QLGrammarLexer;
import org.uva.taxfree.ql.gen.QLGrammarParser;
import org.uva.taxfree.ql.gui.MessageList;
import org.uva.taxfree.ql.model.node.blocks.FormNode;

import java.io.File;
import java.io.IOException;

public class QlAstBuilder extends AntlrBuilder {

    public QlAstBuilder(File inputFile) {
        super(inputFile);
    }

    public FormNode generateAst(MessageList semanticsMessages) {
        try {
            QLGrammarParser parser;
            try {
                parser = createGrammarParser();
            } catch (IOException e) {
                semanticsMessages.addError("(AntlrBuilder.java:52): Unable to create grammarParser: " + e.getMessage());
                return null;
            }
            QlGrammarListener listener = new QlGrammarListener();

            parser.addErrorListener(createErrorListener());
            walkParseTree(listener, parser.form());
            return listener.getAst();
        } catch (UnsupportedOperationException e) {
            semanticsMessages.addError(e.getMessage());
            return null;
        }
    }

    private QLGrammarParser createGrammarParser() throws IOException {
        QLGrammarLexer qlGrammarLexer = new QLGrammarLexer(createInputStream());
        return new QLGrammarParser(createTokenStream(qlGrammarLexer));
    }
}
