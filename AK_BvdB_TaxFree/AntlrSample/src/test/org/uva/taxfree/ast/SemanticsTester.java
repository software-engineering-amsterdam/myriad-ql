package test.org.uva.taxfree.ast;

import org.testng.Assert;
import org.uva.taxfree.ast.AstBuilder;
import org.uva.taxfree.gui.MessageList;
import org.uva.taxfree.model.environment.SymbolTable;
import org.uva.taxfree.model.node.blocks.FormNode;

import java.io.File;
import java.io.IOException;

public abstract class SemanticsTester {
    protected void assertSemantics(String fileName, int expectedErrorAmount, String description) throws IOException {
        boolean expectedValid = 0 == expectedErrorAmount;
        FormNode ast = createAst(fileName);
        MessageList semanticsMessages = new MessageList();
        SymbolTable symbolTable = new SymbolTable();
        ast.fillSymbolTable(symbolTable);
        Assert.assertEquals(!semanticsMessages.hasMessages(), expectedValid, "Expecting errors: " + description);
        Assert.assertEquals(semanticsMessages.messageAmount(), expectedErrorAmount, "Invalid error amount");
    }

    private FormNode createAst(String fileName) throws IOException {
        AstBuilder builder = new AstBuilder(testFile(fileName));
        return builder.generateTree();
    }

    protected abstract File testFile(String fileName);
}
