package org.uva.taxfree.main;//package main;

import org.antlr.v4.gui.TestRig;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.uva.taxfree.ast.OurQLGrammarListener;
import org.uva.taxfree.gen.QLGrammarLexer;
import org.uva.taxfree.gen.QLGrammarParser;

import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Alex on 7-2-2017.
 */
public class Main extends BaseErrorListener {
    public static void main(String[] args) {
        System.out.println("** Starting our parser **");
        try {
            visitAntlrTree();

            String [] testRigArgs = {"QLGrammar", "form", "-gui", "input"};
            try {
                TestRig.main(testRigArgs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void visitAntlrTree() throws IOException {
        ANTLRInputStream inputStream = new ANTLRInputStream(new FileReader("input"));
        QLGrammarLexer qlGrammarLexer = new QLGrammarLexer(inputStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(qlGrammarLexer);
        QLGrammarParser qlGrammarParser = new QLGrammarParser(commonTokenStream);

        QLGrammarParser.FormContext formContext= qlGrammarParser.form();

        // Walk it and attach our listener
        ParseTreeWalker walker = new ParseTreeWalker();
        OurQLGrammarListener listener = new OurQLGrammarListener();
        walker.walk(listener, formContext);
    }
}


