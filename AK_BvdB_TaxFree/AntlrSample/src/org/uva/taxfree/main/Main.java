package org.uva.taxfree.main;//package main;

import org.antlr.v4.gui.TestRig;
import org.uva.taxfree.ast.Ast;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Alex on 7-2-2017.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("** Starting our parser **");
        System.out.println("- Parsing the input");

        try {
            Ast ast = Ast.generateAst(new File("input"));
            System.out.println("Rootnode name: " + ast.getRootNode().getId());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Ast ast2 = Ast.generateAst("form {}");
        System.out.println("Rootnode name: " + ast2.getRootNode().getId());

        System.out.println("- Visualizing the input");
        try {
            String [] testRigArgs = {"QLGrammar", "form", "-gui", "input"};
            TestRig.main(testRigArgs);
        } catch (Exception e) {
            System.out.println("Oops! Couldn't visualize the tree");
            e.printStackTrace();
        }
    }
}


