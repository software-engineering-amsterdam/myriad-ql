package org.uva.taxfree.main;//package main;

import org.uva.taxfree.ast.Ast;
import org.uva.taxfree.model.environment.SymbolTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Alex on 7-2-2017.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("** Starting our parser **");
        System.out.println("- Parsing the input");

        try {
            Ast ast = Ast.generateAst(new File("input"));
            System.out.println("Rootnode name: " + ast.getFormName());
            SemanticsAnalyzer semanticsAnalyzer = new SemanticsAnalyzer(ast);
            semanticsAnalyzer.validSemantics();
//            new QuestionForm(ast.getRootNode()).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Ast ast2 = null;
//        try {
//            ast2 = Ast.generateAst("form {}");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Rootnode name: " + ast2.getRootNode().toString());

        // New way
        SymbolTable symbolTable = new SymbolTable();

        Ast ast = Ast.generateAst(new File("input"), symbolTable);
        SemanticsAnalyzer semanticsAnalyzer = new SemanticsAnalyzer(ast, symbolTable);
        semanticsAnalyzer.validSemantics();
    }

}


