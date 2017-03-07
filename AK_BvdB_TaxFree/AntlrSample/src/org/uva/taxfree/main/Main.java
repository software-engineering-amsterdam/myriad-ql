package org.uva.taxfree.main;//package main;

import org.uva.taxfree.ast.AbstractSyntaxTreeBuilder;
import org.uva.taxfree.gui.ErrorMessage;
import org.uva.taxfree.gui.FileSelector;
import org.uva.taxfree.gui.MessageWindow;
import org.uva.taxfree.gui.QuestionForm;
import org.uva.taxfree.model.environment.Environment;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        File inputFile = FileSelector.select();
        if (!inputFile.exists()) {
            if (MessageWindow.retryDialog(new ErrorMessage("No file selected...\nRetry?"))) {
                main(args);
            }
            return;
        }
     // parse
        // AST =
        // Symboltable = new symboltable
        // checker
        // errors = new errors
        // checker check(symbolTable, errorList)
        // if isEmpty () {
        // etc.

        Environment environment = AbstractSyntaxTreeBuilder.generateAst(inputFile); // , symboltable); // in main
        SemanticsAnalyzer semanticsAnalyzer = new SemanticsAnalyzer(environment); // , symboltable);
        // sematicsAnalyzer.check();
        if (semanticsAnalyzer.hasMessages()) {
            MessageWindow.showMessages(semanticsAnalyzer.getSemanticErrors());
        }
        if (semanticsAnalyzer.validSemantics()) {
            QuestionForm taxForm = new QuestionForm(environment);
            taxForm.show();
        }
    }

}


