package org.uva.taxfree.ql.main;

import org.uva.taxfree.ql.ast.AstBuilder;
import org.uva.taxfree.ql.gui.*;
import org.uva.taxfree.ql.model.environment.SymbolTable;
import org.uva.taxfree.ql.model.node.blocks.FormNode;

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

        AstBuilder builder = new AstBuilder(inputFile);
        MessageList semanticsMessages = new MessageList();
        FormNode ast = builder.generateTree(semanticsMessages);

        if (semanticsMessages.hasMessages()) {
            MessageWindow.showMessages(semanticsMessages);
        }

        if (semanticsMessages.fatalErrors()) {
            System.exit(1);
        }

        SymbolTable symbolTable = new SymbolTable();
        ast.fillSymbolTable(symbolTable);
        ast.checkSemantics(symbolTable, semanticsMessages);

        if (semanticsMessages.hasMessages()) {
            MessageWindow.showMessages(semanticsMessages);
        }

        if (semanticsMessages.fatalErrors()) {
            System.exit(2);
        }
        QuestionForm taxForm = new QuestionForm(ast.toString(), symbolTable);
        ast.fillQuestionForm(taxForm);
        taxForm.show();
    }
}




