package test;

import org.uva.taxfree.ast.AstBuilder;
import org.uva.taxfree.gui.QuestionForm;
import org.uva.taxfree.model.environment.SymbolTable;
import org.uva.taxfree.model.node.blocks.FormNode;
import org.uva.taxfree.qls.QlsStyleBuilder;
import test.org.uva.taxfree.ast.SemanticsTester;

import java.io.File;
import java.io.IOException;

public class QLSFormTest extends SemanticsTester {
    public static void main(String args[]) {
        QLSFormTest main = new QLSFormTest();
        try {
            main.executeMain();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void executeMain() throws IOException {
        // Generate AST
        AstBuilder builder = new AstBuilder(testFile("SimpleForm.txfrm"));
        FormNode ast = builder.generateTree();
        SymbolTable symbolTable = new SymbolTable();
        ast.fillSymbolTable(symbolTable);
        // Create form
        QuestionForm taxForm = new QuestionForm(ast.toString(), symbolTable);
        ast.fillQuestionForm(taxForm);
        // Apply style
        QlsStyleBuilder qlsStyleBuilder = new QlsStyleBuilder(testFile("styles/SimpleFormStyle.qls"));
        taxForm.applyStyle(qlsStyleBuilder.generateStyle());
        // Show form
        taxForm.show();
    }

    @Override
    protected File testFile(String fileName) {
        return new File("src\\test\\org\\uva\\taxfree\\ast\\qlsTestForms\\" + fileName);
    }
}