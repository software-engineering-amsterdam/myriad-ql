package test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.uva.taxfree.gui.MessageList;
import org.uva.taxfree.model.environment.SymbolTable;
import org.uva.taxfree.model.node.Node;
import org.uva.taxfree.model.node.declarations.CalculationNode;
import org.uva.taxfree.model.node.expression.BinaryExpressionNode;
import org.uva.taxfree.model.node.literal.BooleanLiteralNode;
import org.uva.taxfree.model.node.literal.IntegerLiteralNode;
import org.uva.taxfree.model.node.operators.BooleanOperator;
import org.uva.taxfree.model.node.operators.NumericOperator;
import org.uva.taxfree.model.node.operators.UniformOperator;
import org.uva.taxfree.model.types.IntegerType;

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
    public void testCalcualtionSemantics() throws Exception {
        assertTypes(new CalculationNode("MyCalc", "intCalculation",
                        new IntegerType(),
                        new BinaryExpressionNode(new IntegerLiteralNode("10"),
                                new BooleanOperator("||"),
                                new IntegerLiteralNode("5"))),
                1);

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

    public void assertTypes(Node node, int expectedErrorAmount) {
        node.fillSymbolTable(mSymbolTable);
        node.checkSemantics(mSymbolTable, mMessageList);
        Assert.assertEquals(mMessageList.messageAmount(), expectedErrorAmount, "Invalid amount of messaged received");
    }
}
