package org.uva.taxfree.ql;

import org.uva.taxfree.ql.ast.AstBuilder;
import org.uva.taxfree.ql.gui.*;
import org.uva.taxfree.ql.model.environment.SymbolTable;
import org.uva.taxfree.ql.model.node.blocks.FormNode;
import org.uva.taxfree.ql.util.FileUtility;
import org.uva.taxfree.qls.QlsStyle;
import org.uva.taxfree.qls.QlsStyleBuilder;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File inputFile = FileSelector.select();
        if (!inputFile.exists()) {
            if (MessageWindow.retryDialog(new ErrorMessage("input file: No file selected...\nRetry?"))) {
                main(args);
            }
            return;
        }

        AstBuilder builder = new AstBuilder(inputFile);
        MessageList semanticsMessages = new MessageList();
        FormNode ast = builder.generateTree(semanticsMessages);

        checkMessages(semanticsMessages);

        if (semanticsMessages.hasFatalErrors()) {
            System.exit(1);
        }

        SymbolTable symbolTable = new SymbolTable();
        ast.fillSymbolTable(symbolTable);
        ast.checkSemantics(symbolTable, semanticsMessages);

        checkMessages(semanticsMessages);

        if (semanticsMessages.hasFatalErrors()) {
            System.exit(2);
        }

        QuestionForm taxForm = new QuestionForm(ast.toString(), symbolTable);
        ast.fillQuestionForm(taxForm);

        File qlsFile = createStyleFile(inputFile);
        if (qlsFile.exists()) {
            QlsStyleBuilder qlsStyleBuilder = new QlsStyleBuilder(qlsFile);
            QlsStyle qlsStyle = qlsStyleBuilder.generateStyle();
            qlsStyle.checkSemantics(symbolTable, semanticsMessages);
            QlsForm qls = new QlsForm(ast.toString(), symbolTable, qlsStyle);
            taxForm = qls;
        }
        taxForm.show();
    }

    private static void checkMessages(MessageList semanticsMessages) {
        if (semanticsMessages.hasMessages()) {
            MessageWindow.showMessages(semanticsMessages);
        }
    }

    private static File createStyleFile(File inputFile) {
        String qlsFile = FileUtility.replaceExtension(inputFile.getAbsolutePath(), ".qls");
        return new File(qlsFile);
    }
}
