package test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.uva.taxfree.gui.MessageList;
import org.uva.taxfree.model.environment.SymbolTable;
import org.uva.taxfree.model.node.expression.BinaryExpressionNode;
import org.uva.taxfree.model.node.expression.ExpressionNode;
import org.uva.taxfree.model.node.literal.BooleanLiteralNode;
import org.uva.taxfree.model.node.literal.IntegerLiteralNode;
import org.uva.taxfree.model.node.operators.NumericOperator;
import org.uva.taxfree.model.node.operators.UniformOperator;

public class TypeCheckTests {
    private SymbolTable mSymbolTable;
    private MessageList mMessageList;

    @BeforeMethod
    public void setUp() {
        mSymbolTable = new SymbolTable();
        mMessageList = new MessageList();
    }

    @Test
    public void testCorrectTypes() throws Exception {
        assertTypes(new BinaryExpressionNode(new IntegerLiteralNode("5"),
                        new NumericOperator("+"),
                        new IntegerLiteralNode("6")),
                0);

    }

    @Test
    public void testIncorrectTypes() throws Exception {
        assertTypes(new BinaryExpressionNode(new IntegerLiteralNode("10"),
                        new UniformOperator("=="),
                        new BooleanLiteralNode("false")),
                1);
    }

    @Test
    public void testIncorrectTypesAndOperator() throws Exception {
        assertTypes(new BinaryExpressionNode(new IntegerLiteralNode("12"),
                        new NumericOperator("*"),
                        new BooleanLiteralNode("true")),
                2
        );
    }

    public void assertTypes(ExpressionNode expression, int expectedErrorAmount) {
        expression.fillSymbolTable(mSymbolTable);
        expression.checkSemantics(mSymbolTable, mMessageList);
        Assert.assertEquals(mMessageList.size(), expectedErrorAmount, "Invalid amount of messaged received");
    }
}
