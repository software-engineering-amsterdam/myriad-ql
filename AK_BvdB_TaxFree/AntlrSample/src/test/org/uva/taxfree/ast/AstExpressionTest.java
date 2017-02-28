package test.org.uva.taxfree.ast;

import org.testng.annotations.Test;
import org.uva.taxfree.model.node.Node;
import org.uva.taxfree.model.node.blocks.IfStatementNode;
import org.uva.taxfree.model.node.expression.BooleanExpressionNode;
import org.uva.taxfree.model.node.expression.ConditionNode;
import org.uva.taxfree.model.node.literal.BooleanLiteralNode;

import java.util.ArrayList;
import java.util.List;

public class AstExpressionTest {
    @Test
    public void testLiteralExpression() throws Exception {
        // if (true)
        ConditionNode booleanLiteralNodeTrue = new BooleanLiteralNode("true");
        List<Node> childNodes = new ArrayList<>();

        Node ifStatementNode = new IfStatementNode(booleanLiteralNodeTrue, childNodes);
    }

    @Test
    public void testSimpleBooleanExpression() throws Exception {
        // if (true || false)
        ConditionNode booleanLiteralNodeTrue = new BooleanLiteralNode("true");
        ConditionNode booleanLiteralNodeFalse = new BooleanLiteralNode("false");
        ConditionNode booleanExpressionNode = new BooleanExpressionNode(booleanLiteralNodeTrue, "||", booleanLiteralNodeFalse);
        List<Node> childNodes = new ArrayList<>();

        Node ifStatementNode = new IfStatementNode(booleanExpressionNode, childNodes);
    }

    @Test
    public void testNestedBooleanExpression() throws Exception {
        // if (true || false && true)
        ConditionNode booleanLiteralNodeTrue = new BooleanLiteralNode("true");
        ConditionNode booleanLiteralNodeFalse = new BooleanLiteralNode("false");
        ConditionNode booleanExpressionNodeAnd = new BooleanExpressionNode(booleanLiteralNodeFalse, "&&", booleanLiteralNodeTrue);
        ConditionNode booleanExpressionNodeOr = new BooleanExpressionNode(booleanLiteralNodeTrue, "||", booleanExpressionNodeAnd);
        List<Node> childNodes = new ArrayList<>();

        Node ifStatementNode = new IfStatementNode(booleanExpressionNodeOr, childNodes);
    }
}