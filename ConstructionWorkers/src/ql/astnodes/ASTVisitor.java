/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/astnodes/ASTVisitor.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.astnodes;

import ql.astnodes.expressions.Expression;
import ql.astnodes.expressions.binaries.equality.*;
import ql.astnodes.expressions.binaries.logic.AND;
import ql.astnodes.expressions.binaries.logic.Logic;
import ql.astnodes.expressions.binaries.logic.OR;
import ql.astnodes.expressions.binaries.numerical.*;
import ql.astnodes.expressions.literals.*;
import ql.astnodes.expressions.literals.MyInteger;
import ql.astnodes.expressions.unaries.*;
import ql.astnodes.statements.ComputedQuestion;
import ql.astnodes.statements.IfStatement;
import ql.astnodes.statements.SimpleQuestion;
import ql.astnodes.statements.Statement;
import ql.astnodes.types.*;
import ql.antlr.QLBaseVisitor;
import ql.antlr.QLParser;
import ql.antlr.QLVisitor;
import org.antlr.v4.runtime.ParserRuleContext;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class ASTVisitor extends QLBaseVisitor<Node> implements QLVisitor<Node> {

    private static final String GRAMMAR_ERROR = "Grammar error!";

    @Override
    public Form visitForm(QLParser.FormContext ctx) {
        Identifier identifier = new Identifier(ctx.IDENTIFIER().getText(), getLineNumber(ctx));
        List<Statement> statements = new ArrayList<>();

        for (QLParser.StatementContext currentStatement : ctx.statement()) {
            statements.add((Statement) currentStatement.accept(this));
        }

        return new Form(identifier, statements, getLineNumber(ctx));
    }

    @Override
    public SimpleQuestion visitSimpleQuestion(QLParser.SimpleQuestionContext ctx) {
        Type type = (Type) ctx.type().accept(this);
        Identifier identifier = new Identifier(ctx.IDENTIFIER().getText(), getLineNumber(ctx));
        String label = ctx.STRING().getText().substring(1, ctx.STRING().getText().length() - 1);

        return new SimpleQuestion(identifier, label, type, getLineNumber(ctx));
    }

    @Override
    public ComputedQuestion visitComputedQuestion(QLParser.ComputedQuestionContext ctx) {
        Type type = (Type) ctx.type().accept(this);
        Identifier identifier = new Identifier(ctx.IDENTIFIER().getText(), getLineNumber(ctx));
        String label = ctx.STRING().getText().substring(1, ctx.STRING().getText().length() - 1);
        Expression expression = (Expression) ctx.expression().accept(this);

        return new ComputedQuestion(identifier, label, type, expression, getLineNumber(ctx));
    }

    @Override
    public IfStatement visitIfStatement(QLParser.IfStatementContext ctx) {
        Expression expression = (Expression) ctx.expression().accept(this);

        List<Statement> statements = new ArrayList<>();

        for (QLParser.StatementContext section : ctx.statement()) {
            Statement stat = (Statement) section.accept(this);
            statements.add(stat);
        }

        return new IfStatement(expression, statements, getLineNumber(ctx));
    }

    @Override
    public IntegerType visitIntegerType(QLParser.IntegerTypeContext ctx) {
        return new IntegerType(getLineNumber(ctx));
    }

    @Override
    public StringType visitStringType(QLParser.StringTypeContext ctx) {
        return new StringType(getLineNumber(ctx));
    }

    @Override
    public BooleanType visitBooleanType(QLParser.BooleanTypeContext ctx) {
        return new BooleanType(getLineNumber(ctx));
    }

    @Override
    public MoneyType visitMoneyType(QLParser.MoneyTypeContext ctx) {
        return new MoneyType(getLineNumber(ctx));
    }

    @Override
    public Equality visitComparisonExpression(QLParser.ComparisonExpressionContext ctx) {
        Expression left = (Expression) ctx.expression().get(0).accept(this);
        Expression right = (Expression) ctx.expression().get(1).accept(this);

        switch (ctx.op.getText()) {
            case "==":
                return new EQ(left, right, getLineNumber(ctx));
            case "!=":
                return new NEQ(left, right, getLineNumber(ctx));
            case ">":
                return new GT(left, right, getLineNumber(ctx));
            case "<":
                return new LT(left, right, getLineNumber(ctx));
            case ">=":
                return new GTEQ(left, right, getLineNumber(ctx));
            case "<=":
                return new LTEQ(left, right, getLineNumber(ctx));
            default:
                throw new AssertionError(getGrammarErrorMessage("EqualityExpressions"));
        }
    }

    @Override
    public Numerical visitMultDivExpression(QLParser.MultDivExpressionContext ctx) {
        Expression left = (Expression) ctx.expression(0).accept(this);
        Expression right = (Expression) ctx.expression(1).accept(this);

        switch (ctx.op.getText()) {
            case "*":
                return new Multiplication(left, right, getLineNumber(ctx));
            case "/":
                return new Division(left, right, getLineNumber(ctx));
            default:
                throw new AssertionError(getGrammarErrorMessage("MultDivExpressions"));
        }
    }

    @Override
    public Numerical visitAddSubExpression(QLParser.AddSubExpressionContext ctx) {
        Expression left = (Expression) ctx.expression(0).accept(this);
        Expression right = (Expression) ctx.expression(1).accept(this);

        switch (ctx.op.getText()) {
            case "+":
                return new Addition(left, right, getLineNumber(ctx));
            case "-":
                return new Subtraction(left, right, getLineNumber(ctx));
            default:
                throw new AssertionError(getGrammarErrorMessage("AddSubExpressions"));
        }
    }

    @Override
    public Parentheses visitParenthesesExpression(QLParser.ParenthesesExpressionContext ctx) {
        Expression expression = (Expression) ctx.expression().accept(this);

        return new Parentheses(expression, getLineNumber(ctx));
    }

    @Override
    public Unary visitUnaryExpression(QLParser.UnaryExpressionContext ctx) {
        Expression expression = (Expression) ctx.expression().accept(this);

        switch (ctx.op.getText()) {
            case "!":
                return new Negation(expression, getLineNumber(ctx));
            case "+":
                return new Positive(expression, getLineNumber(ctx));
            case "-":
                return new Negative(expression, getLineNumber(ctx));
            default:
                throw new AssertionError(getGrammarErrorMessage("UnaryExpressions"));
        }
    }

    @Override
    public Logic visitAndExpression(QLParser.AndExpressionContext ctx) {
        Expression left = (Expression) ctx.expression(0).accept(this);
        Expression right = (Expression) ctx.expression(1).accept(this);

        return new AND(left, right, getLineNumber(ctx));
    }

    @Override
    public Logic visitOrExpression(QLParser.OrExpressionContext ctx) {
        Expression left = (Expression) ctx.expression(0).accept(this);
        Expression right = (Expression) ctx.expression(1).accept(this);

        return new OR(left, right, getLineNumber(ctx));
    }

    @Override
    public MyInteger visitIntExpression(QLParser.IntExpressionContext ctx) {
        return new MyInteger(java.lang.Integer.parseInt(ctx.getText()), getLineNumber(ctx));
    }

    @Override
    public Money visitMoneyExpression(QLParser.MoneyExpressionContext ctx) {
        return new Money(BigDecimal.valueOf(Double.parseDouble(ctx.getText())), getLineNumber(ctx));
    }

    @Override
    public Identifier visitIdentifierExpression(QLParser.IdentifierExpressionContext ctx) {
        return new Identifier(ctx.IDENTIFIER().getText(), getLineNumber(ctx));
    }

    @Override
    public MyBoolean visitBoolExpression(QLParser.BoolExpressionContext ctx) {
        return new MyBoolean(Boolean.parseBoolean(ctx.getText()), getLineNumber(ctx));
    }

    @Override
    public MyString visitStringExpression(QLParser.StringExpressionContext ctx) {
        return new MyString(ctx.getText().substring(1, ctx.getText().length() - 1), getLineNumber(ctx));
    }

    private String getGrammarErrorMessage(String expression) {
        return MessageFormat.format(ASTVisitor.GRAMMAR_ERROR, expression);
    }

    private LineNumber getLineNumber(ParserRuleContext ctx) {
        return new LineNumber(ctx.getStart().getLine());
    }
}
