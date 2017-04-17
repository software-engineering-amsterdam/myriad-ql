package org.uva.taxfree.ql;

import org.uva.taxfree.ql.ast.QlAstBuilder;
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

        QlAstBuilder builder = new QlAstBuilder(inputFile);
        MessageList semanticsMessages = new MessageList();
        FormNode ast = builder.generateAst(semanticsMessages);

        checkMessages(semanticsMessages);

        if (semanticsMessages.hasFatalErrors()) {
            System.exit(EXIT_AST_ERROR);
        }

        SymbolTable symbolTable = new SymbolTable();
        ast.fillSymbolTable(symbolTable);
        ast.checkSemantics(symbolTable, semanticsMessages);

        checkMessages(semanticsMessages);

        if (semanticsMessages.hasFatalErrors()) {
            System.exit(EXIT_FORM_ERROR);
        }

        QuestionForm taxForm = new QuestionForm(ast.toString(), symbolTable);
        File qlsFile = createStyleFile(inputFile);
        if (qlsFile.exists()) {
            QlsStyleBuilder qlsStyleBuilder = new QlsStyleBuilder(qlsFile);
            QlsStyle qlsStyle = qlsStyleBuilder.generateStyle(semanticsMessages);
            qlsStyle.checkSemantics(symbolTable, semanticsMessages);
            checkMessages(semanticsMessages);
            if (semanticsMessages.hasFatalErrors()) {
                System.exit(EXIT_QLS_ERROR);
            }
            QlsForm qls = new QlsForm(ast.toString(), symbolTable, qlsStyle);
            taxForm = qls;
        }
        ast.fillQuestionForm(taxForm);
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

    private static final int EXIT_AST_ERROR = 1;
    private static final int EXIT_FORM_ERROR = 2;
    private static final int EXIT_QLS_ERROR = 3;

}
