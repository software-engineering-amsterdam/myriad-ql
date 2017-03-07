package test.org.uva.taxfree.ast;

import org.testng.annotations.Test;
import org.uva.taxfree.model.node.Node;
import org.uva.taxfree.model.node.blocks.IfStatementNode;
import org.uva.taxfree.model.node.expression.BooleanBinaryExpressionNode;
import org.uva.taxfree.model.node.expression.ExpressionNode;
import org.uva.taxfree.model.node.literal.BooleanLiteralNode;

import java.util.LinkedHashSet;
import java.util.Set;

public class AbstractSyntaxTreeBuilderExpressionTest {
    @Test
    public void testLiteralExpression() throws Exception {
        // if (true)
        ExpressionNode booleanLiteralNodeTrue = new BooleanLiteralNode("true");
        Set<Node> childNodes = new LinkedHashSet<>();

        Node ifStatementNode = new IfStatementNode(booleanLiteralNodeTrue, childNodes);
    }

    @Test
    public void testSimpleBooleanExpression() throws Exception {
        // if (true || false)
        ExpressionNode booleanLiteralNodeTrue = new BooleanLiteralNode("true");
        ExpressionNode booleanLiteralNodeFalse = new BooleanLiteralNode("false");
        ExpressionNode booleanExpressionNode = new BooleanBinaryExpressionNode(booleanLiteralNodeTrue, "||", booleanLiteralNodeFalse);
        Set<Node> childNodes = new LinkedHashSet<>();

        Node ifStatementNode = new IfStatementNode(booleanExpressionNode, childNodes);
    }

    @Test
    public void testNestedBooleanExpression() throws Exception {
        // if (true || false && true)
        ExpressionNode booleanLiteralNodeTrue = new BooleanLiteralNode("true");
        ExpressionNode booleanLiteralNodeFalse = new BooleanLiteralNode("false");
        ExpressionNode booleanExpressionNodeAnd = new BooleanBinaryExpressionNode(booleanLiteralNodeFalse, "&&", booleanLiteralNodeTrue);
        ExpressionNode booleanExpressionNodeOr = new BooleanBinaryExpressionNode(booleanLiteralNodeTrue, "||", booleanExpressionNodeAnd);
        Set<Node> childNodes = new LinkedHashSet<>();

        Node ifStatementNode = new IfStatementNode(booleanExpressionNodeOr, childNodes);
    }
}