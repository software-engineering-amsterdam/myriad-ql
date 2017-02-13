package org.uva.taxfree.main;//package main;

import org.antlr.v4.gui.TestRig;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.uva.taxfree.ast.AST;
import org.uva.taxfree.ast.OurQLGrammarListener;
import org.uva.taxfree.gen.QLGrammarLexer;
import org.uva.taxfree.gen.QLGrammarParser;

import java.io.*;

/**
 * Created by Alex on 7-2-2017.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("** Starting our parser **");
        System.out.println("- Parsing the input");
        try {
            parse(new File("input"));
        } catch (IOException e) {
            System.out.println("Oops! An error while parsing the input");
            e.printStackTrace();
        }
        System.out.println("Rootnode name: " + AST.getInstance().getRootNode().getName());

        System.out.println("- Visualizing the input");
        try {
            String [] testRigArgs = {"QLGrammar", "form", "-gui", "input"};
            TestRig.main(testRigArgs);
        } catch (Exception e) {
            System.out.println("Oops! Couldn't visualize the tree");
            e.printStackTrace();
        }
    }
    private static void parse(String string) throws IOException {
        visitAntlrTree(new StringReader(string));
    }

    private static void parse(File absoluteFilePath) throws IOException {
        visitAntlrTree(new FileReader(absoluteFilePath));
    }

    private static void visitAntlrTree(Reader reader) throws IOException {
        ANTLRInputStream inputStream = new ANTLRInputStream(reader);
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


