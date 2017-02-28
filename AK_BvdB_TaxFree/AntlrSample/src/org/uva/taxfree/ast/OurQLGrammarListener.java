package org.uva.taxfree.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.uva.taxfree.gen.QLGrammarBaseListener;
import org.uva.taxfree.gen.QLGrammarParser;
import org.uva.taxfree.model.node.FormNode;
import org.uva.taxfree.model.node.Node;
import org.uva.taxfree.model.node.condition.IfElseStatementNode;
import org.uva.taxfree.model.node.condition.IfStatementNode;
import org.uva.taxfree.model.node.expression.BooleanExpressionNode;
import org.uva.taxfree.model.node.expression.CalculationExpressionNode;
import org.uva.taxfree.model.node.expression.ParenthesizedExpressionNode;
import org.uva.taxfree.model.node.expression.UniformExpressionNode;
import org.uva.taxfree.model.node.statement.*;
import org.uva.taxfree.model.node.literal.BooleanLiteralNode;
import org.uva.taxfree.model.node.literal.IntegerLiteralNode;
import org.uva.taxfree.model.node.literal.StringLiteralNode;
import org.uva.taxfree.model.node.literal.VariableLiteralNode;

import java.util.Stack;

//public class OurQLGrammarListener implements ParseTreeListener { // Now we do not need to override everything while developing
public class OurQLGrammarListener extends QLGrammarBaseListener{ // To enforce us to override every method. We can also extend the base one to not override everything

    private Ast mAst;
    private Stack<Node> mParentStack = new Stack<>();

    private void popParent() {
        mParentStack.pop();
    }

    private void addParentNodeToAst(Node node) {
        addNodeToAst(node);
        mParentStack.push(node);
    }

    private void addNodeToAst(Node node) {
        mParentStack.peek().addChild(node);
    }

    public Ast getAst() {
        return mAst;
    }

    // Enters
    @Override
    public void enterForm(QLGrammarParser.FormContext ctx) {
        super.enterForm(ctx);
        Node formNode = new FormNode(ctx.formId.toString());
        if (mAst == null) {
            // It's the root!
            mAst = new Ast(formNode);
            mParentStack.push(formNode);
        } else {
            addParentNodeToAst(formNode);
        }
    }

    @Override
    public void enterIfStatement(QLGrammarParser.IfStatementContext ctx) {
        super.enterIfStatement(ctx);
        Node ifStatementNode = new IfStatementNode();
        addParentNodeToAst(ifStatementNode);
    }

    @Override
    public void enterIfElseStatement(QLGrammarParser.IfElseStatementContext ctx) {
        super.enterIfElseStatement(ctx);
        Node ifElseStatementNode = new IfElseStatementNode();
        addParentNodeToAst(ifElseStatementNode);
    }

    @Override
    public void enterQuestion(QLGrammarParser.QuestionContext ctx) {
        super.enterQuestion(ctx);
        Node questionNode;
        if ("boolean".equals(ctx.varType().getText())) {
            questionNode = new BooleanQuestion(ctx.QUESTION().toString(), ctx.VARIABLE_LITERAL().toString());
        } else if ("string".equals(ctx.varType().getText())) {
            questionNode = new StringQuestion(ctx.QUESTION().toString(), ctx.VARIABLE_LITERAL().toString());
        } else if ("integer".equals(ctx.varType().getText())) {
            questionNode = new IntegerQuestion(ctx.QUESTION().toString(), ctx.VARIABLE_LITERAL().toString());
        } else {
            // TODO: Bail out!
            throw new RuntimeException("Found unexpected variable type: " + ctx.varType().getText());
        }
        addNodeToAst(questionNode);
    }

    @Override
    public void enterCalculation(QLGrammarParser.CalculationContext ctx) {
        super.enterCalculation(ctx);
        Node calculatedFieldNode;
        if ("boolean".equals(ctx.varType().getText())) {
            calculatedFieldNode = new BooleanCalculatedField(ctx.DESCRIPTION().toString(), ctx.VARIABLE_LITERAL().toString());
        } else if ("integer".equals(ctx.varType().getText())) {
            calculatedFieldNode = new IntegerCalculatedField(ctx.DESCRIPTION().toString(), ctx.VARIABLE_LITERAL().toString());
        } else {
            // TODO: Bail out!
            throw new RuntimeException("Found unexpected variable type: " + ctx.varType().getText());
        }
        addParentNodeToAst(calculatedFieldNode);
    }

    @Override
    public void enterBooleanExpression(QLGrammarParser.BooleanExpressionContext ctx) {
        super.enterBooleanExpression(ctx);
        Node booleanExpressionNode = new BooleanExpressionNode(ctx.operator.toString());
        addParentNodeToAst(booleanExpressionNode);
    }

    @Override
    public void enterCalculationExpression(QLGrammarParser.CalculationExpressionContext ctx) {
        super.enterCalculationExpression(ctx);
        Node calculationExpressionNode = new CalculationExpressionNode(ctx.operator.toString());
        addParentNodeToAst(calculationExpressionNode);
    }

    @Override
    public void enterUniformExpression(QLGrammarParser.UniformExpressionContext ctx) {
        super.enterUniformExpression(ctx);
        Node uniformExpressionNode = new UniformExpressionNode(ctx.operator.toString());
        addParentNodeToAst(uniformExpressionNode);
    }

    @Override
    public void enterParenthesizedExpression(QLGrammarParser.ParenthesizedExpressionContext ctx) {
        super.enterParenthesizedExpression(ctx);
        Node parenthesizedExpression = new ParenthesizedExpressionNode();
        addParentNodeToAst(parenthesizedExpression);
    }

    @Override
    public void enterBooleanLiteral(QLGrammarParser.BooleanLiteralContext ctx) {
        super.enterBooleanLiteral(ctx);
        Node booleanLiteralNode = new BooleanLiteralNode(ctx.getText());
        addNodeToAst(booleanLiteralNode);
    }

    @Override
    public void enterIntegerLiteral(QLGrammarParser.IntegerLiteralContext ctx) {
        super.enterIntegerLiteral(ctx);
        Node integerLiteralNode = new IntegerLiteralNode(ctx.getText());
        addNodeToAst(integerLiteralNode);
    }

    @Override
    public void enterStringLiteral(QLGrammarParser.StringLiteralContext ctx) {
        super.enterStringLiteral(ctx);
        Node stringLiteralNode = new StringLiteralNode(ctx.getText());
        addNodeToAst(stringLiteralNode);
    }

    @Override
    public void enterVarNameLiteral(QLGrammarParser.VarNameLiteralContext ctx) {
        super.enterVarNameLiteral(ctx);
        Node variableLiteralNode = new VariableLiteralNode(ctx.getText());
        addNodeToAst(variableLiteralNode);
    }

    // Exits
    @Override
    public void exitIfStatement(QLGrammarParser.IfStatementContext ctx) {
        super.exitIfStatement(ctx);
        popParent();
    }

    @Override
    public void exitIfElseStatement(QLGrammarParser.IfElseStatementContext ctx) {
        super.exitIfElseStatement(ctx);
        popParent();
    }

    @Override
    public void exitCalculation(QLGrammarParser.CalculationContext ctx) {
        super.exitCalculation(ctx);
        popParent();
    }

    @Override
    public void exitCalculationExpression(QLGrammarParser.CalculationExpressionContext ctx) {
        super.exitCalculationExpression(ctx);
        popParent();
    }

    @Override
    public void exitBooleanExpression(QLGrammarParser.BooleanExpressionContext ctx) {
        super.exitBooleanExpression(ctx);
        popParent();
    }

    @Override
    public void exitUniformExpression(QLGrammarParser.UniformExpressionContext ctx) {
        super.exitUniformExpression(ctx);
        popParent();
    }

    @Override
    public void exitParenthesizedExpression(QLGrammarParser.ParenthesizedExpressionContext ctx) {
        super.exitParenthesizedExpression(ctx);
        popParent();
    }

    @Override
    public void exitForm(QLGrammarParser.FormContext ctx) {
        super.exitForm(ctx);
        popParent(); // Clear stack
        if (!mParentStack.isEmpty()) {
            throw new AssertionError("After parsing the form, the stack should be empty");
        }
    }

    // Unused calls
    @Override
    public void exitQuestion(QLGrammarParser.QuestionContext ctx) {
        super.exitQuestion(ctx);
    }

    @Override
    public void enterBooleanType(QLGrammarParser.BooleanTypeContext ctx) {
        super.enterBooleanType(ctx);
    }

    @Override
    public void exitBooleanType(QLGrammarParser.BooleanTypeContext ctx) {
        super.exitBooleanType(ctx);
    }

    @Override
    public void enterStringType(QLGrammarParser.StringTypeContext ctx) {
        super.enterStringType(ctx);
    }

    @Override
    public void exitStringType(QLGrammarParser.StringTypeContext ctx) {
        super.exitStringType(ctx);
    }

    @Override
    public void enterIntegerType(QLGrammarParser.IntegerTypeContext ctx) {
        super.enterIntegerType(ctx);
    }

    @Override
    public void exitIntegerType(QLGrammarParser.IntegerTypeContext ctx) {
        super.exitIntegerType(ctx);
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
        super.enterEveryRule(ctx);
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
        super.exitEveryRule(ctx);
    }

    @Override
    public void visitTerminal(TerminalNode node) {
        super.visitTerminal(node);
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
        super.visitErrorNode(node);
    }
}
