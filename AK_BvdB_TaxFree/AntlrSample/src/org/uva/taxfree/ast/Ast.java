package org.uva.taxfree.ast;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.uva.taxfree.gen.QLGrammarLexer;
import org.uva.taxfree.gen.QLGrammarParser;
import org.uva.taxfree.model.node.Node;
import org.uva.taxfree.model.node.blocks.BlockNode;
import org.uva.taxfree.model.node.statement.NamedNode;

import java.io.*;
import java.util.BitSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Ast {

    private Ast() {
        // Private to prevent empty initialization
    }

    private BlockNode mRootNode;

    public Ast(BlockNode rootNode) {
        mRootNode = rootNode;
    }

    public static Ast generateAst(File input) throws IOException {
        return generateAst(new FileReader(input));
    }

    public static Ast generateAst(String input) throws IOException {
        return generateAst(new StringReader(input));
    }

    public Set<NamedNode> getDeclarations() {
        Set<NamedNode> questions = new LinkedHashSet<>();
        mRootNode.retrieveDeclarations(questions);
        return questions;
    }

    // By implicitely modifying the list
    public Set<Node> getConditions() {
        Set<Node> conditions = new LinkedHashSet<>();
        mRootNode.retrieveConditions(conditions);
        return conditions;
    }

    public String getFormName() {
        return mRootNode.toString();
    }

    private static Ast generateAst(Reader reader) throws IOException {
        ANTLRInputStream inputStream = new ANTLRInputStream(reader);
        QLGrammarLexer qlGrammarLexer = new QLGrammarLexer(inputStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(qlGrammarLexer);
        QLGrammarParser qlGrammarParser = new QLGrammarParser(commonTokenStream);

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

        qlGrammarParser.addErrorListener(errorListener);
        qlGrammarLexer.addErrorListener(errorListener);

        QLGrammarParser.FormContext formContext = qlGrammarParser.form();

        // Walk it and attach our listener
        ParseTreeWalker walker = new ParseTreeWalker();
        OurQLGrammarListener listener = new OurQLGrammarListener();
        walker.walk(listener, formContext);
        return listener.getAst();
    }
}

