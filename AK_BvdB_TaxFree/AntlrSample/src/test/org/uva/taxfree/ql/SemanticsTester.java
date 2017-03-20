package test.org.uva.taxfree.ql;

import org.testng.Assert;
import org.uva.taxfree.ql.ast.AstBuilder;
import org.uva.taxfree.ql.gui.MessageList;
import org.uva.taxfree.ql.model.environment.SymbolTable;
import org.uva.taxfree.ql.model.node.blocks.FormNode;

import java.io.File;
import java.io.IOException;

public abstract class SemanticsTester {
    protected void assertSemantics(String fileName, int expectedErrorAmount, String description) throws IOException {
        boolean expectedValid = 0 == expectedErrorAmount;
        FormNode ast = createAst(fileName);
        MessageList semanticsMessages = new MessageList();
        SymbolTable symbolTable = new SymbolTable();
        ast.fillSymbolTable(symbolTable);
        ast.checkSemantics(symbolTable, semanticsMessages);
        Assert.assertEquals(!semanticsMessages.hasMessages(), expectedValid, "Expecting errors: " + description);
        Assert.assertEquals(semanticsMessages.messageAmount(), expectedErrorAmount, "Invalid error amount");
    }

    private FormNode createAst(String fileName) throws IOException {
        AstBuilder builder = new AstBuilder(testFile(fileName));
        return builder.generateTree();
    }

    protected abstract File testFile(String fileName);
}
