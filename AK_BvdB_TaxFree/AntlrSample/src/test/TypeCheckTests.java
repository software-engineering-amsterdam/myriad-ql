package test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.uva.taxfree.gui.MessageList;
import org.uva.taxfree.model.environment.SymbolTable;
import org.uva.taxfree.model.node.expression.BooleanBinaryExpressionNode;
import org.uva.taxfree.model.node.expression.ExpressionNode;
import org.uva.taxfree.model.node.literal.IntegerLiteralNode;
import org.uva.taxfree.model.node.operators.AddOperator;

public class TypeCheckTests {
    private SymbolTable mSymbolTable;
    private MessageList mMessageList;

    @BeforeMethod
    public void setUp() {
        mSymbolTable = new SymbolTable();
        mMessageList = new MessageList();
    }

    @Test
    public void testDifferentTypes() throws Exception {

        ExpressionNode expr = new BooleanBinaryExpressionNode(new IntegerLiteralNode("5"), new AddOperator(), new IntegerLiteralNode("6"));
        expr.fillSymbolTable(mSymbolTable);
        expr.checkSemantics(mSymbolTable, mMessageList);
        Assert.assertTrue(mMessageList.isEmpty(), "Valid binary expression should result in no errors");
    }
}
