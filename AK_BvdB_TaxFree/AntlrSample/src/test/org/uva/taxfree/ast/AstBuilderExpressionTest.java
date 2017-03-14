package test.org.uva.taxfree.ast;

import org.testng.annotations.Test;
import org.uva.taxfree.model.node.Node;
import org.uva.taxfree.model.node.blocks.IfStatementNode;
import org.uva.taxfree.model.node.expression.ExpressionNode;
import org.uva.taxfree.model.node.literal.BooleanLiteralNode;

import java.util.ArrayList;
import java.util.List;

public class AstBuilderExpressionTest {
    @Test
    public void testLiteralExpression() throws Exception {
        // if (true)
        ExpressionNode booleanLiteralNodeTrue = new BooleanLiteralNode("true");
        List<Node> childNodes = new ArrayList<>();

        Node ifStatementNode = new IfStatementNode(booleanLiteralNodeTrue, childNodes);
    }

    @Test
    public void testSimpleBooleanExpression() throws Exception {
        // if (true || false)
        ExpressionNode booleanLiteralNodeTrue = new BooleanLiteralNode("true");
        ExpressionNode booleanLiteralNodeFalse = new BooleanLiteralNode("false");
//        ExpressionNode booleanExpressionNode = new BooleanBinaryExpressionNode(booleanLiteralNodeTrue, "||", booleanLiteralNodeFalse);
        List<Node> childNodes = new ArrayList<>();

//        Node ifStatementNode = new IfStatementNode(booleanExpressionNode, childNodes);
    }

    @Test
    public void testNestedBooleanExpression() throws Exception {
        // if (true || false && true)
        ExpressionNode booleanLiteralNodeTrue = new BooleanLiteralNode("true");
        ExpressionNode booleanLiteralNodeFalse = new BooleanLiteralNode("false");
//        ExpressionNode booleanExpressionNodeAnd = new BooleanBinaryExpressionNode(booleanLiteralNodeFalse, "&&", booleanLiteralNodeTrue);
//        ExpressionNode booleanExpressionNodeOr = new BooleanBinaryExpressionNode(booleanLiteralNodeTrue, "||", booleanExpressionNodeAnd);
        List<Node> childNodes = new ArrayList<>();

//        Node ifStatementNode = new IfStatementNode(booleanExpressionNodeOr, childNodes);
    }
}