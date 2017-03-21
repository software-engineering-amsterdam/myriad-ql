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
        FormNode ast = builder.generateTree();
        SymbolTable symbolTable = new SymbolTable();
        MessageList semanticsMessages = new MessageList();
        ast.fillSymbolTable(symbolTable);
        ast.checkSemantics(symbolTable, semanticsMessages);

        if (semanticsMessages.hasMessages()) {
            MessageWindow.showMessages(semanticsMessages);
        }

        if (!semanticsMessages.fatalErrors()) {
            QuestionForm taxForm = new QuestionForm(ast.toString(), symbolTable);
            ast.fillQuestionForm(taxForm);
            taxForm.show();
        }
    }
}




