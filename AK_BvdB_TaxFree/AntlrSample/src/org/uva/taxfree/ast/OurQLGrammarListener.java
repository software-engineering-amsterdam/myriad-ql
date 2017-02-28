package org.uva.taxfree.ast;

import org.uva.taxfree.gen.QLGrammarBaseListener;
import org.uva.taxfree.gen.QLGrammarParser;
import org.uva.taxfree.model.environment.Environment;
import org.uva.taxfree.model.environment.SymbolTable;
import org.uva.taxfree.model.node.Node;
import org.uva.taxfree.model.node.blocks.BlockNode;
import org.uva.taxfree.model.node.blocks.FormNode;
import org.uva.taxfree.model.node.blocks.IfElseStatementNode;
import org.uva.taxfree.model.node.blocks.IfStatementNode;
import org.uva.taxfree.model.node.expression.*;
import org.uva.taxfree.model.node.literal.BooleanLiteralNode;
import org.uva.taxfree.model.node.literal.IntegerLiteralNode;
import org.uva.taxfree.model.node.literal.StringLiteralNode;
import org.uva.taxfree.model.node.literal.VariableLiteralNode;
import org.uva.taxfree.model.node.statement.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class OurQLGrammarListener extends QLGrammarBaseListener {

    private SymbolTable mSymbolTable;
    private FormNode mRootNode;

    private final List<Node> mCachedChildren = new ArrayList<>();
    private final List<BlockNode> mCachedIfStatementNodes = new ArrayList<>(); // The only list that won't be empty in the end
    private final List<ConditionNode> mCachedConditions = new ArrayList<>();

    public OurQLGrammarListener() {
        mSymbolTable = new SymbolTable();
    }

    public Environment getEnvironment() {
        return new Environment(mSymbolTable, mRootNode);
    }

    private ConditionNode popCachedCondition() {
        return mCachedConditions.remove(mCachedConditions.size()-1);
    }

    private void cacheCondition(ConditionNode conditionNode) {
        mCachedConditions.add(conditionNode);
    }

    // Enters
    @Override
    public void enterQuestion(QLGrammarParser.QuestionContext ctx) {
        super.enterQuestion(ctx);
        NamedNode questionNode;
        String questionText = ctx.QUESTION().getText();
        String questionId = ctx.VARIABLE_LITERAL().getText();

        if ("boolean".equals(ctx.varType().getText())) {
            questionNode = new BooleanQuestion(questionText, questionId);
        } else if ("string".equals(ctx.varType().getText())) {
            questionNode = new StringQuestion(questionText, questionId);
        } else if ("integer".equals(ctx.varType().getText())) {
            questionNode = new IntegerQuestion(questionText, questionId);
        } else {
            // TODO: Bail out!
            throw new RuntimeException("Found unexpected variable type: " + ctx.varType().getText());
        }
        mSymbolTable.addSymbol(questionNode);
        mCachedChildren.add(questionNode);
    }

    @Override
    public void enterCalculation(QLGrammarParser.CalculationContext ctx) {
        super.enterCalculation(ctx);
        NamedNode calculatedFieldNode;
        String fieldDescription = ctx.DESCRIPTION().getText();
        String fieldId = ctx.VARIABLE_LITERAL().getText();

        if ("boolean".equals(ctx.varType().getText())) {
            calculatedFieldNode = new BooleanCalculatedField(fieldDescription, fieldId, popCachedCondition());
        } else if ("integer".equals(ctx.varType().getText())) {
            calculatedFieldNode = new IntegerCalculatedField(fieldDescription, fieldId, popCachedCondition());
        } else {
            // TODO: Bail out!
            throw new RuntimeException("Found unexpected variable type: " + ctx.varType().getText());
        }
        mSymbolTable.addSymbol(calculatedFieldNode);
        mCachedChildren.add(calculatedFieldNode);
    }

    @Override
    public void enterBooleanLiteral(QLGrammarParser.BooleanLiteralContext ctx) {
        super.enterBooleanLiteral(ctx);
        ConditionNode booleanLiteralNode = new BooleanLiteralNode(ctx.getText());
        cacheCondition(booleanLiteralNode);
    }

    @Override
    public void enterStringLiteral(QLGrammarParser.StringLiteralContext ctx) {
        super.enterStringLiteral(ctx);
        ConditionNode stringLiteralNode = new StringLiteralNode(ctx.getText());
        cacheCondition(stringLiteralNode);
    }

    @Override
    public void enterIntegerLiteral(QLGrammarParser.IntegerLiteralContext ctx) {
        super.enterIntegerLiteral(ctx);
        ConditionNode integerLiteralNode = new IntegerLiteralNode(ctx.getText());
        cacheCondition(integerLiteralNode);
    }

    @Override
    public void enterVarNameLiteral(QLGrammarParser.VarNameLiteralContext ctx) {
        super.enterVarNameLiteral(ctx);
        ConditionNode varNameLiteral = new VariableLiteralNode(ctx.getText(), mSymbolTable);
        cacheCondition(varNameLiteral);
    }

    // Exits
    @Override
    public void exitBooleanExpression(QLGrammarParser.BooleanExpressionContext ctx) {
        super.exitBooleanExpression(ctx);
        ConditionNode booleanExpressionNode = new BooleanExpressionNode(popCachedCondition(), ctx.operator.getText(), popCachedCondition());
        cacheCondition(booleanExpressionNode);
    }

    @Override
    public void exitCalculationExpression(QLGrammarParser.CalculationExpressionContext ctx) {
        super.exitCalculationExpression(ctx);
        ConditionNode calculationExpressionNode = new CalculationExpressionNode(popCachedCondition(), ctx.operator.getText(), popCachedCondition());
        cacheCondition(calculationExpressionNode);
    }

    @Override
    public void exitUniformExpression(QLGrammarParser.UniformExpressionContext ctx) {
        super.exitUniformExpression(ctx);
        ConditionNode uniformExpressionNode = new UniformExpressionNode(popCachedCondition(), ctx.operator.getText(), popCachedCondition());
        cacheCondition(uniformExpressionNode);
    }

    @Override
    public void exitParenthesizedExpression(QLGrammarParser.ParenthesizedExpressionContext ctx) {
        super.exitParenthesizedExpression(ctx);
        ConditionNode parenthesizedExpressionNode = new ParenthesizedExpressionNode(popCachedCondition());
        cacheCondition(parenthesizedExpressionNode);
    }

    @Override
    public void exitIfStatement(QLGrammarParser.IfStatementContext ctx) {
        super.exitIfStatement(ctx);
        BlockNode ifStatementNode = new IfStatementNode(popCachedCondition(), new LinkedHashSet<>(mCachedChildren));
        mCachedChildren.clear();
        mCachedChildren.add(ifStatementNode);
        mCachedIfStatementNodes.add(ifStatementNode);
    }

    @Override
    public void exitIfElseStatement(QLGrammarParser.IfElseStatementContext ctx) {
        super.exitIfElseStatement(ctx);
        // Retrieve the if statement, which is the first child.
        // Then remove it, remaining childs are the childs in the ELSE block.
        // Childs in the IF block are contained in the ifStatement node already, which is a member of the IfElseStatementNode
        BlockNode ifStatementNode = mCachedIfStatementNodes.remove(mCachedIfStatementNodes.size()-1);
        mCachedChildren.remove(0); // Remove the ifStatementNode from the children
        BlockNode ifElseStatementNode = new IfElseStatementNode(ifStatementNode, new LinkedHashSet<>(mCachedChildren));
        mCachedChildren.clear();
        mCachedChildren.add(ifElseStatementNode);
    }

    @Override
    public void exitForm(QLGrammarParser.FormContext ctx) {
        super.exitForm(ctx);
        mRootNode = new FormNode(ctx.VARIABLE_LITERAL().toString(), new LinkedHashSet<>(mCachedChildren));
        mCachedChildren.clear();
    }
}
