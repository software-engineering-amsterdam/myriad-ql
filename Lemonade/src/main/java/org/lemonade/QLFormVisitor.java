package org.lemonade;

import org.lemonade.expression.*;
import org.lemonade.expression.binary.*;
import org.lemonade.expression.literal.*;
import org.lemonade.expression.unary.BangUnary;
import org.lemonade.expression.unary.NegUnary;

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
        List<Block> blocks = new ArrayList<Block>();

        for (QLParser.BlockContext block : ctx.block()) {
            blocks.add((Block) block.accept(this));
        }

        return new Form(identifier, blocks);
    }

    @Override
    public ASTNode visitQuestion(QLParser.QuestionContext ctx) {
        System.err.println("entering question");
        String identifier = ctx.identifier().getText();
        String label = ctx.label().getText();
        Type type = (Type) ctx.type_specifier().accept(this);

        return new Question(identifier, label, type);
    }

    @Override
    public ASTNode visitConditional(QLParser.ConditionalContext ctx) {
        System.err.println("entering conditional");
        Expression expression = (Expression) ctx.expr().accept(this);

        List<Block> blocks = new ArrayList<Block>();
        for (QLParser.BlockContext block : ctx.block()){
            blocks.add((Block) block.accept(this));
        }

        return new Conditional(expression, blocks);
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
        return new BooleanLit(ctx.BOOLEAN().getText());
    }

    @Override
    public ASTNode visitStringLiteral(QLParser.StringLiteralContext ctx) {
        return new StringLit(ctx.STR().getText());
    }

    @Override
    public ASTNode visitIdentifierLiteral(QLParser.IdentifierLiteralContext ctx) {
        return new IdentifierLit(ctx.IDENT().getText());
    }

    @Override
    public ASTNode visitIntegerLiteral(QLParser.IntegerLiteralContext ctx) {
        System.err.println("integer");
        return new IntegerLit(ctx.INT().getText());
    }

    @Override
    public ASTNode visitDecimalLiteral(QLParser.DecimalLiteralContext ctx) {
        System.err.println("decimal");
        return new DecimalLit(ctx.DECIMAL().getText());
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
    public ASTNode visitType_specifier(QLParser.Type_specifierContext ctx) {
        switch (ctx.getText()) {
            case "boolean":
                return new Type(QLType.BOOLEAN);
            case "string":
                return new Type(QLType.STRING);
            case "integer":
                return new Type(QLType.INTEGER);
            case "date":
                return new Type(QLType.DATE);
            case "decimal":
                return new Type(QLType.DECIMAL);
            case "currency":
                return new Type(QLType.CURRENCY);
            default:
                return null;//TODO find better way to handle this?
        }
    }
}
