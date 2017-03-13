package org.uva.taxfree.ast;

import org.uva.taxfree.gen.QLGrammarBaseListener;
import org.uva.taxfree.gen.QLGrammarParser;
import org.uva.taxfree.model.environment.SymbolTable;
import org.uva.taxfree.model.node.Node;
import org.uva.taxfree.model.node.blocks.BlockNode;
import org.uva.taxfree.model.node.blocks.FormNode;
import org.uva.taxfree.model.node.blocks.IfElseStatementNode;
import org.uva.taxfree.model.node.blocks.IfStatementNode;
import org.uva.taxfree.model.node.declarations.CalculationNode;
import org.uva.taxfree.model.node.declarations.DeclarationNode;
import org.uva.taxfree.model.node.expression.BinaryExpressionNode;
import org.uva.taxfree.model.node.expression.ExpressionNode;
import org.uva.taxfree.model.node.expression.ParenthesizedExpressionNode;
import org.uva.taxfree.model.node.literal.BooleanLiteralNode;
import org.uva.taxfree.model.node.literal.IntegerLiteralNode;
import org.uva.taxfree.model.node.literal.StringLiteralNode;
import org.uva.taxfree.model.node.literal.VariableLiteralNode;
import org.uva.taxfree.model.node.operators.*;
import org.uva.taxfree.model.types.BooleanType;
import org.uva.taxfree.model.types.IntegerType;
import org.uva.taxfree.model.types.StringType;
import org.uva.taxfree.model.types.Type;

import java.util.*;

import static org.uva.taxfree.gen.QLGrammarParser.*;

public class GrammarListener extends QLGrammarBaseListener {
    private FormNode mRootNode;

    private final List<ExpressionNode> mCachedConditions = new ArrayList<>();
    private final Stack<List<Node>> mChildsStack = new Stack<>();

    public GrammarListener() {

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
        String questionText = ctx.LABEL().getText();
        String questionId = ctx.VARIABLE_LITERAL().getText();
        Type questionType;
        String parsedVarType = ctx.varType().getText();
        if ("boolean".equals(parsedVarType)) {
            questionType = new BooleanType();
        } else if ("string".equals(parsedVarType)) {
            questionType = new StringType();
        } else if ("integer".equals(parsedVarType)) {
            questionType = new IntegerType();
        } else {
            // TODO: Bail out!
            throw new RuntimeException("Found unexpected variable type: " + ctx.varType().getText());
        }
        DeclarationNode questionNode = new DeclarationNode(questionText, questionId, questionType);
        addDeclaration(questionNode);
    }

    private void addDeclaration(DeclarationNode node) {
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
        ExpressionNode varNameLiteral = new VariableLiteralNode(ctx.getText(), new SymbolTable()); // TODO: VAR has no symboltable anymore!
        addToStack(varNameLiteral);
    }

    @Override
    public void enterIfStatement(QLGrammarParser.IfStatementContext ctx) {
        super.enterIfStatement(ctx);
        createStack();
    }

    @Override
    public void enterIfElseStatement(QLGrammarParser.IfElseStatementContext ctx) {
        super.enterIfElseStatement(ctx);
        createStack();
    }

    private void createStack() {
        mChildsStack.push(new ArrayList<>());
    }

    // Exits
    @Override
    public void exitCalculation(QLGrammarParser.CalculationContext ctx) {
        super.exitCalculation(ctx);
        String fieldDescription = ctx.LABEL().getText();
        String fieldId = ctx.VARIABLE_LITERAL().getText();
        Type fieldType;

        if ("boolean".equals(ctx.varType().getText())) {
            fieldType = new BooleanType();
        } else if ("integer".equals(ctx.varType().getText())) {
            fieldType = new IntegerType();
        } else {
            // TODO: Bail out!
            throw new RuntimeException("Found unexpected variable type: " + ctx.varType().getText());
        }
        DeclarationNode calculatedNode = new CalculationNode(fieldDescription, fieldId, fieldType, popCachedCondition());
        addDeclaration(calculatedNode);
    }

    @Override
    public void exitBinaryExpression(BinaryExpressionContext ctx) {
        super.exitBinaryExpression(ctx);
        Operator operator = createOperator(ctx.op.getType(), ctx.op.getText());
        ExpressionNode booleanExpressionNode = new BinaryExpressionNode(popCachedCondition(), operator, popCachedCondition());
        addToStack(booleanExpressionNode);
    }

    private Operator createOperator(int type, String operator) {
        switch (type) {
            case OP_MULTIPLY:
            case OP_DIVIDE:
            case OP_PLUS:
            case OP_MINUS:
                return new NumericOperator(operator);
            case OP_SMALLER:
            case OP_SMALLEROREQUAL:
            case OP_BIGGER:
            case OP_BIGGEROREQUAL:
                return new CompareOperator(operator);
            case OP_EQUALS:
            case OP_NOTEQUALS:
                return new UniformOperator(operator);
            case OP_LOGICAL_AND:
            case OP_LOGICAL_OR:
                return new BooleanOperator(operator);
            default:
                // TODO: bail out!
                throw new RuntimeException("Unexpected operator");
        }
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
        BlockNode ifStatementNode = new IfStatementNode(popCachedCondition(), popChildStack());
        addToStack(ifStatementNode);
    }

    private Set<Node> popChildStack() {
        return new LinkedHashSet<>(mChildsStack.pop());
    }

    @Override
    public void exitIfElseStatement(QLGrammarParser.IfElseStatementContext ctx) {
        super.exitIfElseStatement(ctx);
        Set<Node> content = popChildStack();
        // TODO
        BlockNode ifStatementNode = null;

        BlockNode ifElseStatementNode = new IfElseStatementNode(ifStatementNode, content);
        addToStack(ifElseStatementNode);
    }

    @Override
    public void exitForm(QLGrammarParser.FormContext ctx) {
        super.exitForm(ctx);
        mRootNode = new FormNode(ctx.VARIABLE_LITERAL().toString(), popChildStack());
        if (!mChildsStack.isEmpty()) {
            throw new AssertionError("Stack should be empty when we finished creating our AST.");
        }
    }
}
