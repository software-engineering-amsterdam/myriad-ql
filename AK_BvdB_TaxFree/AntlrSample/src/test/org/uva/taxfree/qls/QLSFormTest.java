package test.org.uva.taxfree.qls;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.uva.taxfree.ql.ast.AstBuilder;
import org.uva.taxfree.ql.gui.MessageList;
import org.uva.taxfree.ql.gui.QuestionForm;
import org.uva.taxfree.ql.model.environment.SymbolTable;
import org.uva.taxfree.ql.model.node.blocks.FormNode;
import org.uva.taxfree.ql.util.FileUtility;
import org.uva.taxfree.qls.QlsStyle;
import org.uva.taxfree.qls.QlsStyleBuilder;
import test.org.uva.taxfree.ql.SemanticsTester;

import java.io.File;
import java.io.IOException;

public class QLSFormTest extends SemanticsTester {
    QlsStyleBuilder mStyleBuilder;
    QlsStyle mQlsStyle;

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
        FormNode ast = builder.generateTree(new MessageList());
        SymbolTable symbolTable = new SymbolTable();
        ast.fillSymbolTable(symbolTable);
        // Create form
        QuestionForm taxForm = new QuestionForm(ast.toString(), symbolTable);
        ast.fillQuestionForm(taxForm);
        // Apply style
        createQls("SimpleForm.qls");
        taxForm.applyStyle(mQlsStyle);
        // Show form
        taxForm.show();
    }

    @Test
    public void testSemantics() throws Exception {
        assertSemantics("SimpleForm.txfrm", 0, "Qls should not contain errors");
    }

    private void createQls(String taxFile) {
        String styleFile = FileUtility.replaceExtension(taxFile, ".qls");
        File style = testFile(styleFile);
        Assert.assertTrue(style.exists(), "File not found: " + styleFile);
        mStyleBuilder = new QlsStyleBuilder(style);
        mQlsStyle = mStyleBuilder.generateStyle();
    }

    protected void assertSemantics(String fileName, int expectedErrorAmount, String description) throws IOException {
        super.assertSemantics(fileName, 0, "only works for valid tax forms");
        createQls(fileName);
        MessageList messages = new MessageList();
        mQlsStyle.checkSemantics(getSymbolTable(), messages);
        System.out.println(messages);
        Assert.assertEquals(expectedErrorAmount, messages.messageAmount(), "invalid error amount detected: " + description);
    }

    @Override
    protected File testFile(String fileName) {
        return new File("src\\test\\org\\uva\\taxfree\\qls\\" + fileDirectory() + "\\" + fileName);
    }

    @Override
    protected String fileDirectory() {
        return "testFiles";
    }
}
