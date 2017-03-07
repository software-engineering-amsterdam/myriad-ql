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

        File inputFile = FileSelector.select();
        if (!inputFile.exists()) {
            if (MessageWindow.retryDialog(new ErrorMessage("No file selected...\nRetry?"))) {
                main(args);
            }
            return;
        }
        Environment environment = Ast.generateAst(inputFile);
        SemanticsAnalyzer semanticsAnalyzer = new SemanticsAnalyzer(environment);
        semanticsAnalyzer.validSemantics();
    }

}


