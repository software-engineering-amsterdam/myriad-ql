package org.uva.taxfree.main;//package main;

import org.uva.taxfree.ast.Ast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Alex on 7-2-2017.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("** Starting our parser **");
        System.out.println("- Parsing the input");

        if (1==1) {

        }
        if (true && true || false || true && true) {

        }

        if ('r' >= 's') {

        }
        int i = 0;
        try {
            Ast ast = Ast.generateAst(new File("input"));
            System.out.println("Rootnode name: " + ast.getRootNode().getId());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Ast ast2 = null;
        try {
            ast2 = Ast.generateAst("form {}");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Rootnode name: " + ast2.getRootNode().getId());
    }
}


