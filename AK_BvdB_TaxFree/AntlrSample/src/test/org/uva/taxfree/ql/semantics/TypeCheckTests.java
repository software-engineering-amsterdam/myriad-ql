package test.org.uva.taxfree.ql.semantics;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.uva.taxfree.ql.gui.MessageList;
import org.uva.taxfree.ql.model.environment.SymbolTable;
import org.uva.taxfree.ql.model.node.Node;
import org.uva.taxfree.ql.model.node.declarations.CalculationNode;
import org.uva.taxfree.ql.model.node.expression.BinaryExpressionNode;
import org.uva.taxfree.ql.model.node.literal.BooleanLiteralNode;
import org.uva.taxfree.ql.model.node.literal.IntegerLiteralNode;
import org.uva.taxfree.ql.model.operators.AddOperator;
import org.uva.taxfree.ql.model.operators.EqualsOperator;
import org.uva.taxfree.ql.model.operators.GreaterThanOperator;
import org.uva.taxfree.ql.model.operators.MultiplyOperator;
import org.uva.taxfree.ql.model.types.BooleanType;
import org.uva.taxfree.ql.model.types.IntegerType;
import org.uva.taxfree.ql.model.types.Type;

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
        assertTypes(new BinaryExpressionNode(new IntegerLiteralNode(5),
                        new AddOperator(),
                        new IntegerLiteralNode(6)),
                0);

    }

    @Test(timeOut = 10000L)
    public void testCalculationSemantics() throws Exception {
        assertTypes(new CalculationNode("MyCalc", "intCalculation",
                        new IntegerType(),
                        new BinaryExpressionNode(new IntegerLiteralNode(10),
                                new AddOperator(),
                                new IntegerLiteralNode(5))),
                0);

    }

    @Test
    public void testIncorrectTypes() throws Exception {
        assertTypes(new BinaryExpressionNode(new IntegerLiteralNode(10),
                        new EqualsOperator(),
                        new BooleanLiteralNode(false)),
                1);
    }

    @Test
    public void testIncorrectTypesAndOperator() throws Exception {
        assertTypes(new BinaryExpressionNode(new IntegerLiteralNode(12),
                        new MultiplyOperator(),
                        new BooleanLiteralNode(true)),
                2
        );
    }

    public void assertTypes(Node node, int expectedErrorAmount) {
        node.fillSymbolTable(mSymbolTable);
        node.checkSemantics(mSymbolTable, mMessageList);
        Assert.assertEquals(mMessageList.messageAmount(), expectedErrorAmount, "Invalid amount of messaged received");
    }

    @Test
    public void testTypes() throws Exception {
        BinaryExpressionNode b = new BinaryExpressionNode(new IntegerLiteralNode(5), new GreaterThanOperator(), new IntegerLiteralNode(15));
        Type expressionType = b.getType();
        Assert.assertTrue(expressionType.equals(new BooleanType()), "Comparing ints should yield boolean");
    }

    @Test
    public void testBooleanTypes() throws Exception {
        BinaryExpressionNode b = new BinaryExpressionNode(new BooleanLiteralNode(true), new EqualsOperator(), new BooleanLiteralNode(true));
        Type expressionType = b.getType();
        Assert.assertTrue(expressionType.equals(new BooleanType()), "Comparing booleans should yield booleans");
    }

}
