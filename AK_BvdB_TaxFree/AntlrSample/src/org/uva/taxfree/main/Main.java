package org.uva.taxfree.main;//package main;

import org.uva.taxfree.ast.Ast;
import org.uva.taxfree.gui.ErrorMessage;
import org.uva.taxfree.gui.FileSelector;
import org.uva.taxfree.gui.MessageWindow;
import org.uva.taxfree.model.environment.Environment;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("** Starting our parser **");
        System.out.println("- Parsing the input");

//        try {
//            Ast ast = Ast.generateAst(new File("input"));
//            System.out.println("Rootnode name: " + ast.getFormName());
//            SemanticsAnalyzer semanticsAnalyzer = new SemanticsAnalyzer(ast);
//            semanticsAnalyzer.validSemantics();
////            new QuestionForm(ast.getRootNode()).show();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Ast ast2 = null;
//        try {
//            ast2 = Ast.generateAst("form {}");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Rootnode name: " + ast2.getRootNode().toString());

        File inputFile = FileSelector.select();
        if (!inputFile.exists()) {
            if (MessageWindow.retryDialog(new ErrorMessage("No file selected...\n retry?"))) {
                main(args);
            }
            return;
        }

        Environment environment = Ast.generateAst(inputFile);
        SemanticsAnalyzer semanticsAnalyzer = new SemanticsAnalyzer(environment);
        semanticsAnalyzer.validSemantics();
    }

}


