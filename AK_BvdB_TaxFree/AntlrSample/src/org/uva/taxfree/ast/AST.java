package org.uva.taxfree.ast;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.uva.taxfree.gen.QLGrammarLexer;
import org.uva.taxfree.gen.QLGrammarParser;
import org.uva.taxfree.model.Node;

import java.io.*;

public class Ast {

    private Ast() {

    }

    public static Ast generateAst(File input) throws FileNotFoundException {
        return generateAst(new FileReader(input));
    }

    public static Ast generateAst(String input) {
        return generateAst(new StringReader(input));
    }

    private static Ast generateAst(Reader reader) {
        ANTLRInputStream inputStream;
        try {
            inputStream = new ANTLRInputStream(reader);
        } catch (IOException e) {
            // TODO: Parse error? Catch this and notify user
            e.printStackTrace();
            return null;
        }
        QLGrammarLexer qlGrammarLexer = new QLGrammarLexer(inputStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(qlGrammarLexer);
        QLGrammarParser qlGrammarParser = new QLGrammarParser(commonTokenStream);

        QLGrammarParser.FormContext formContext= qlGrammarParser.form();

        // Walk it and attach our listener
        ParseTreeWalker walker = new ParseTreeWalker();
        OurQLGrammarListener listener = new OurQLGrammarListener();
        walker.walk(listener, formContext);
        return listener.getAst();
    }



    private Node mRootNode;

    public Ast(Node rootNode) {
        mRootNode = rootNode;
    }

    public Node getRootNode() {
        return mRootNode;
    }
}

