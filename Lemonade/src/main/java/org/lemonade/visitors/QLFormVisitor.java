package org.lemonade.visitors;

import org.lemonade.QLBaseVisitor;
import org.lemonade.QLParser;
import org.lemonade.nodes.*;
import org.lemonade.nodes.expressions.BinaryExpression;
import org.lemonade.nodes.expressions.Expression;
import org.lemonade.nodes.expressions.Literal;
import org.lemonade.nodes.expressions.UnaryExpression;
import org.lemonade.nodes.expressions.binary.*;
import org.lemonade.nodes.expressions.literal.*;
import org.lemonade.nodes.expressions.unary.BangUnary;
import org.lemonade.nodes.expressions.unary.NegUnary;
import org.lemonade.nodes.types.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class QLFormVisitor extends QLBaseVisitor<ASTNode> {

    @Override
    public Form visitForm(QLParser.FormContext ctx) {
        System.err.println("Entering form");
        String identifier = ctx.identifier().getText();
        List<Body> bodies = new ArrayList<Body>();

        for (QLParser.BodyContext body : ctx.body()) {
            bodies.add((Body) body.accept(this));
        }

        return new Form(identifier, bodies);
    }

    @Override
    public ASTNode visitQuestion(QLParser.QuestionContext ctx) {
        System.err.println("entering question");
        String identifier = ctx.identifier().getText();
        String label = ctx.label().getText();
        QLType type = (QLType) ctx.type_specifier().accept(this);

        return new Question(identifier, label, type);
    }

    @Override
    public ASTNode visitConditional(QLParser.ConditionalContext ctx) {
        System.err.println("entering conditional");
        Expression expression = (Expression) ctx.expr().accept(this);

        List<Body> bodies = new ArrayList<Body>();
        for (QLParser.BodyContext body: ctx.body()) {
            bodies.add((Body) body.accept(this));
        }

        return new Conditional(expression, bodies);
    }

    @Override
    public ASTNode visitBinaryExpression(QLParser.BinaryExpressionContext ctx) {
        BinaryExpression binary = (BinaryExpression) ctx.binaryoperator().accept(this);

        Expression left = (Expression) ctx.expr().get(0).accept(this);
        Expression right = (Expression) ctx.expr().get(1).accept(this);

        binary.setLeft(left);
        binary.setRight(right);

        return binary;
    }

    @Override
    public ASTNode visitUnaryExpression(QLParser.UnaryExpressionContext ctx) {
        UnaryExpression unary = (UnaryExpression) ctx.unaryoperator().accept(this);
        Expression child = (Expression) ctx.expr().accept(this);
        unary.setExpression(child);

        return unary;
    }

    @Override
    public ASTNode visitLiteralExpression(QLParser.LiteralExpressionContext ctx) {
        Literal literal = (Literal) ctx.literal().accept(this);
        return literal;
    }

    @Override
    public ASTNode visitBooleanLiteral(QLParser.BooleanLiteralContext ctx) {
        return new BooleanLit(new QLBooleanType(), ctx.BOOLEAN().getText());
    }

    @Override
    public ASTNode visitStringLiteral(QLParser.StringLiteralContext ctx) {
        return new StringLit(new QLStringType(), ctx.STR().getText());
    }

    @Override
    public ASTNode visitIdentifierLiteral(QLParser.IdentifierLiteralContext ctx) {
        return new IdentifierLit(new QLStringType(), ctx.IDENT().getText());
    }

    @Override
    public ASTNode visitIntegerLiteral(QLParser.IntegerLiteralContext ctx) {
        System.err.println("integer");
        return new IntegerLit(new QLIntegerType(), ctx.INT().getText());
    }

    @Override
    public ASTNode visitDecimalLiteral(QLParser.DecimalLiteralContext ctx) {
        System.err.println("decimal");
        return new DecimalLit(new QLDecimalType(), ctx.DECIMAL().getText());
    }

    @Override
    public ASTNode visitNegateUnary(QLParser.NegateUnaryContext ctx) {
        return new NegUnary();
    }

    @Override
    public ASTNode visitBangUnary(QLParser.BangUnaryContext ctx) {
        return new BangUnary();
    }

    @Override
    public ASTNode visitProductBinary(QLParser.ProductBinaryContext ctx) {
        return new ProductBinary();
    }

    @Override
    public ASTNode visitDivideBinary(QLParser.DivideBinaryContext ctx) {
        return new DivideBinary();
    }

    @Override
    public ASTNode visitPlusBinary(QLParser.PlusBinaryContext ctx) {
        return new PlusBinary();
    }

    @Override
    public ASTNode visitMinusBinary(QLParser.MinusBinaryContext ctx) {
        return new MinusBinary();
    }

    @Override
    public ASTNode visitLessThanBinary(QLParser.LessThanBinaryContext ctx) {
        return new LTBinary();
    }

    @Override
    public ASTNode visitLessThanEqualBinary(QLParser.LessThanEqualBinaryContext ctx) {
        return new LTEBinary();
    }

    @Override
    public ASTNode visitGreaterThanBinary(QLParser.GreaterThanBinaryContext ctx) {
        return new GTBinary();
    }

    @Override
    public ASTNode visitGreaterThanEqualBinary(QLParser.GreaterThanEqualBinaryContext ctx) {
        return new GTEBinary();
    }

    @Override
    public ASTNode visitEqualBinary(QLParser.EqualBinaryContext ctx) {
        return new EqBinary();
    }

    @Override
    public ASTNode visitNotEqualBinary(QLParser.NotEqualBinaryContext ctx) {
        return new NEqBinary();
    }

    @Override
    public ASTNode visitAndBinary(QLParser.AndBinaryContext ctx) {
        return new AndBinary();
    }

    @Override
    public ASTNode visitOrBinary(QLParser.OrBinaryContext ctx) {
        return new OrBinary();
    }

    @Override
    public ASTNode visitBooleanType(QLParser.BooleanTypeContext ctx) {
        return new QLBooleanType();
    }

    @Override
    public ASTNode visitStringType(QLParser.StringTypeContext ctx) {
        return new QLStringType();
    }

    @Override
    public ASTNode visitIntegerType(QLParser.IntegerTypeContext ctx) {
        return new QLIntegerType();
    }

    @Override
    public ASTNode visitDateType(QLParser.DateTypeContext ctx) {
        return new QLDateType();
    }

    @Override
    public ASTNode visitDecimalType(QLParser.DecimalTypeContext ctx) {
        return new QLDecimalType();
    }

    @Override
    public ASTNode visitMoneyType(QLParser.MoneyTypeContext ctx) {
        return new QLMoneyType();
    }
}
