package org.uva.taxfree.ast;

import org.uva.taxfree.gen.QLGrammarBaseListener;
import org.uva.taxfree.gen.QLGrammarParser;
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
import java.util.List;

//public class OurQLGrammarListener implements ParseTreeListener { // Now we do not need to override everything while developing
public class OurQLGrammarListener extends QLGrammarBaseListener{ // To enforce us to override every method. We can also extend the base one to not override everything

    private Ast mAst;
    private SymbolTable mSymbolTable;
    private FormNode mRootNode;

    private final List<Node> mCachedChildren = new ArrayList<>();
    private final List<ConditionNode> mCachedConditions = new ArrayList<>();

    public OurQLGrammarListener(SymbolTable symbolTable) {
        mSymbolTable = symbolTable;
    }

    public Ast getAst() {
        return mAst;
    }

    private ConditionNode popCachedCondition() {
        return mCachedConditions.remove(mCachedConditions.size());
    }

    // Enters
    @Override
    public void enterQuestion(QLGrammarParser.QuestionContext ctx) {
        super.enterQuestion(ctx);
        Node questionNode;
//        LiteralNode variableLiteralNode = new VariableLiteralNode(ctx.VARIABLE_LITERAL().getText());
        if ("boolean".equals(ctx.varType().getText())) {
            questionNode = new BooleanQuestion(ctx.QUESTION().toString(), ctx.VARIABLE_LITERAL().getText());
        } else if ("string".equals(ctx.varType().getText())) {
            questionNode = new StringQuestion(ctx.QUESTION().toString(), ctx.VARIABLE_LITERAL().getText());
        } else if ("integer".equals(ctx.varType().getText())) {
            questionNode = new IntegerQuestion(ctx.QUESTION().toString(), ctx.VARIABLE_LITERAL().getText());
        } else {
            // TODO: Bail out!
            throw new RuntimeException("Found unexpected variable type: " + ctx.varType().getText());
        }
        mCachedChildren.add(questionNode);
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
        mCachedChildren.add(calculatedFieldNode);
    }

    @Override
    public void enterBooleanLiteral(QLGrammarParser.BooleanLiteralContext ctx) {
        super.enterBooleanLiteral(ctx);
        ConditionNode booleanLiteralNode = new BooleanLiteralNode(ctx.getText());
        mCachedConditions.add(booleanLiteralNode);
    }

    @Override
    public void enterStringLiteral(QLGrammarParser.StringLiteralContext ctx) {
        super.enterStringLiteral(ctx);
        ConditionNode stringLiteralNode = new StringLiteralNode(ctx.getText());
        mCachedConditions.add(stringLiteralNode);
    }

    @Override
    public void enterIntegerLiteral(QLGrammarParser.IntegerLiteralContext ctx) {
        super.enterIntegerLiteral(ctx);
        ConditionNode integerLiteralNode = new IntegerLiteralNode(ctx.getText());
        mCachedConditions.add(integerLiteralNode);
    }

    @Override
    public void enterVarNameLiteral(QLGrammarParser.VarNameLiteralContext ctx) {
        super.enterVarNameLiteral(ctx);
        // TODO: Do not add to conditions in case we are in an questionNode (or some fix for this)
        // We do not know to which question this variable belongs, it can even be that no question is related to this variable
        ConditionNode varNameLiteral = new VariableLiteralNode(ctx.getText(), mSymbolTable);
        mCachedConditions.add(varNameLiteral);
    }

    // Exits
    @Override
    public void exitBooleanExpression(QLGrammarParser.BooleanExpressionContext ctx) {
        super.exitBooleanExpression(ctx);
        ConditionNode booleanExpressionNode = new BooleanExpressionNode(popCachedCondition(), ctx.operator.getText(), popCachedCondition());
        mCachedConditions.add(booleanExpressionNode);
    }

    @Override
    public void exitCalculationExpression(QLGrammarParser.CalculationExpressionContext ctx) {
        super.exitCalculationExpression(ctx);
        ConditionNode calculationExpressionNode = new CalculationExpressionNode(popCachedCondition(), ctx.operator.getText(), popCachedCondition());
        mCachedConditions.add(calculationExpressionNode);
    }

    @Override
    public void exitUniformExpression(QLGrammarParser.UniformExpressionContext ctx) {
        super.exitUniformExpression(ctx);
        ConditionNode uniformExpressionNode = new UniformExpressionNode(popCachedCondition(), ctx.operator.getText(), popCachedCondition());
        mCachedConditions.add(uniformExpressionNode);
    }

    @Override
    public void exitParenthesizedExpression(QLGrammarParser.ParenthesizedExpressionContext ctx) {
        super.exitParenthesizedExpression(ctx);
        ConditionNode parenthesizedExpressionNode = new ParenthesizedExpressionNode(popCachedCondition());
        mCachedConditions.add(parenthesizedExpressionNode);
    }

    @Override
    public void exitIfStatement(QLGrammarParser.IfStatementContext ctx) {
        super.exitIfStatement(ctx);
        BlockNode ifStatementNode = new IfStatementNode(popCachedCondition(), mCachedChildren);
        mCachedChildren.clear();
        mCachedChildren.add(ifStatementNode);
    }

    @Override
    public void exitIfElseStatement(QLGrammarParser.IfElseStatementContext ctx) {
        super.exitIfElseStatement(ctx);
        // Retrieve the if statement, which is the first child.
        // Then remove it, remaining childs are the childs in the ELSE block.
        // Childs in the IF block are contained in the ifStatement node already, which is a member of the IfElseStatementNode
        Node ifStatementNode = mCachedChildren.remove(0);
        BlockNode ifElseStatementNode = new IfElseStatementNode(ifStatementNode, mCachedChildren);
        mCachedChildren.clear();
        mCachedChildren.add(ifElseStatementNode);
    }

    @Override
    public void exitForm(QLGrammarParser.FormContext ctx) {
        super.exitForm(ctx);
        mRootNode = new FormNode(ctx.VARIABLE_LITERAL().toString(), mCachedChildren);
        mCachedChildren.clear();
    }
}
