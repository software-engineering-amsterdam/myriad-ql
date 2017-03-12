package org.uva.taxfree.ast;

import org.uva.taxfree.gen.QLGrammarBaseListener;
import org.uva.taxfree.gen.QLGrammarParser;
import org.uva.taxfree.model.environment.SymbolTable;
import org.uva.taxfree.model.node.Node;
import org.uva.taxfree.model.node.blocks.BlockNode;
import org.uva.taxfree.model.node.blocks.FormNode;
import org.uva.taxfree.model.node.blocks.IfElseStatementNode;
import org.uva.taxfree.model.node.blocks.IfStatementNode;
import org.uva.taxfree.model.node.declarations.*;
import org.uva.taxfree.model.node.expression.*;
import org.uva.taxfree.model.node.literal.BooleanLiteralNode;
import org.uva.taxfree.model.node.literal.IntegerLiteralNode;
import org.uva.taxfree.model.node.literal.StringLiteralNode;
import org.uva.taxfree.model.node.literal.VariableLiteralNode;
import org.uva.taxfree.model.types.BooleanType;
import org.uva.taxfree.model.types.IntegerType;

import java.util.*;

public class GrammarListener extends QLGrammarBaseListener {
    private FormNode mRootNode;
    private final SymbolTable mSymbolTable;

    private final List<ExpressionNode> mCachedConditions = new ArrayList<>();
    private final Stack<List<Node>> mChildsStack = new Stack<>();
    private boolean insideIfElse = false;

    public GrammarListener() {
        mSymbolTable = new SymbolTable();
    }

    public FormNode getAst() {
        return mRootNode;
    }

    private ExpressionNode popCachedCondition() {
        return mCachedConditions.remove(mCachedConditions.size() - 1);
    }

    // Enters
    @Override
    public void enterForm(QLGrammarParser.FormContext ctx) {
        super.enterForm(ctx);
        createStack();
    }

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
        addDeclaration(questionNode);
    }

    private void addDeclaration(NamedNode node) {
        mSymbolTable.addDeclaration(node);
        addToStack(node);
    }

    private void addToStack(Node node) {
        mChildsStack.peek().add(node);
    }

    private void addToStack(ExpressionNode node) {
        mCachedConditions.add(node);
    }

    @Override
    public void enterBooleanLiteral(QLGrammarParser.BooleanLiteralContext ctx) {
        super.enterBooleanLiteral(ctx);
        ExpressionNode booleanLiteralNode = new BooleanLiteralNode(ctx.getText());
        addToStack(booleanLiteralNode);
    }

    @Override
    public void enterStringLiteral(QLGrammarParser.StringLiteralContext ctx) {
        super.enterStringLiteral(ctx);
        ExpressionNode stringLiteralNode = new StringLiteralNode(ctx.getText());
        addToStack(stringLiteralNode);
    }

    @Override
    public void enterIntegerLiteral(QLGrammarParser.IntegerLiteralContext ctx) {
        super.enterIntegerLiteral(ctx);
        ExpressionNode integerLiteralNode = new IntegerLiteralNode(ctx.getText());
        addToStack(integerLiteralNode);
    }

    @Override
    public void enterVarNameLiteral(QLGrammarParser.VarNameLiteralContext ctx) {
        super.enterVarNameLiteral(ctx);
        ExpressionNode varNameLiteral = new VariableLiteralNode(ctx.getText(), mSymbolTable);
        addToStack(varNameLiteral);
        mSymbolTable.addVariable(ctx.getText());
    }

    @Override
    public void enterIfStatement(QLGrammarParser.IfStatementContext ctx) {
        super.enterIfStatement(ctx);
        createStack();
    }

    @Override
    public void enterIfElseStatement(QLGrammarParser.IfElseStatementContext ctx) {
        super.enterIfElseStatement(ctx);
        insideIfElse = true;
    }

    private void createStack() {
        mChildsStack.push(new ArrayList<>());
    }

    // Exits
    @Override
    public void exitCalculation(QLGrammarParser.CalculationContext ctx) {
        super.exitCalculation(ctx);
        NamedNode calculatedFieldNode;
        String fieldDescription = ctx.DESCRIPTION().getText();
        String fieldId = ctx.VARIABLE_LITERAL().getText();

        if ("boolean".equals(ctx.varType().getText())) {
            calculatedFieldNode = new CalculatedField(fieldDescription, fieldId, new BooleanType(), popCachedCondition());
        } else if ("integer".equals(ctx.varType().getText())) {
            calculatedFieldNode = new CalculatedField(fieldDescription, fieldId, new IntegerType(), popCachedCondition());
        } else {
            // TODO: Bail out!
            throw new RuntimeException("Found unexpected variable type: " + ctx.varType().getText());
        }
        addDeclaration(calculatedFieldNode);
    }

    @Override
    public void exitBooleanExpression(QLGrammarParser.BooleanExpressionContext ctx) {
        super.exitBooleanExpression(ctx);
        ExpressionNode booleanExpressionNode = new BooleanBinaryExpressionNode(popCachedCondition(), ctx.operator.getText(), popCachedCondition());
        addToStack(booleanExpressionNode);
    }

    @Override
    public void exitCalculationExpression(QLGrammarParser.CalculationExpressionContext ctx) {
        super.exitCalculationExpression(ctx);
        ExpressionNode calculationExpressionNode = new CalculationBinaryExpressionNode(popCachedCondition(), ctx.operator.getText(), popCachedCondition());
        addToStack(calculationExpressionNode);
    }

    @Override
    public void exitUniformExpression(QLGrammarParser.UniformExpressionContext ctx) {
        super.exitUniformExpression(ctx);
        ExpressionNode uniformExpressionNode = new UniformBinaryExpressionNode(popCachedCondition(), ctx.operator.getText(), popCachedCondition());
        addToStack(uniformExpressionNode);
    }

    @Override
    public void exitParenthesizedExpression(QLGrammarParser.ParenthesizedExpressionContext ctx) {
        super.exitParenthesizedExpression(ctx);
        ExpressionNode parenthesizedExpressionNode = new ParenthesizedExpressionNode(popCachedCondition());
        addToStack(parenthesizedExpressionNode);
    }

    @Override
    public void exitIfStatement(QLGrammarParser.IfStatementContext ctx) {
        super.exitIfStatement(ctx);
        BlockNode ifStatementNode = new IfStatementNode(popCachedCondition(), popStack());
        addToStack(ifStatementNode);
        if (insideIfElse) {
            createStack();
            insideIfElse = false;
        }
    }

    private Set<Node> popStack() {
        return new LinkedHashSet<>(mChildsStack.pop());
    }

    @Override
    public void exitIfElseStatement(QLGrammarParser.IfElseStatementContext ctx) {
        super.exitIfElseStatement(ctx);
        Set<Node> content = popStack();
        BlockNode ifStatementNode = lastBlock();

        BlockNode ifElseStatementNode = new IfElseStatementNode(ifStatementNode, content);
        addToStack(ifElseStatementNode);
    }

    private BlockNode lastBlock() {
        // TODO: Fix static cast
        return (BlockNode) mChildsStack.peek().remove(mChildsStack.peek().size() - 1);
    }

    @Override
    public void exitForm(QLGrammarParser.FormContext ctx) {
        super.exitForm(ctx);
        mRootNode = new FormNode(ctx.VARIABLE_LITERAL().toString(), popStack());
        if (!mChildsStack.isEmpty()) {
            throw new AssertionError("Stack should be empty when we finished creating our AST.");
        }
    }
}
