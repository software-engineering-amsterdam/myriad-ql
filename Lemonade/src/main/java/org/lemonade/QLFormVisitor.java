package org.lemonade;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.lemonade.expression.Expression;
import org.lemonade.expression.Type;

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
        return super.visitBinaryExpression(ctx);
    }

    @Override
    public ASTNode visitUnaryExpression(QLParser.UnaryExpressionContext ctx) {
        return super.visitUnaryExpression(ctx);
    }

    @Override
    public ASTNode visitLiteralExpression(QLParser.LiteralExpressionContext ctx) {
        Literal literal = ctx.literal().accept();
        return super.visitLiteralExpression(ctx);
    }

    @Override
    public ASTNode visitBooleanLiteral(QLParser.BooleanLiteralContext ctx) {
        return super.visitBooleanLiteral(ctx);
    }

    @Override
    public ASTNode visitStringLiteral(QLParser.StringLiteralContext ctx) {
        return super.visitStringLiteral(ctx);
    }

    @Override
    public ASTNode visitIdentifierLiteral(QLParser.IdentifierLiteralContext ctx) {
        return super.visitIdentifierLiteral(ctx);
    }

    @Override
    public ASTNode visitIntegerLiteral(QLParser.IntegerLiteralContext ctx) {
        return super.visitIntegerLiteral(ctx);
    }

    @Override
    public ASTNode visitDecimalLiteral(QLParser.DecimalLiteralContext ctx) {
        return super.visitDecimalLiteral(ctx);
    }

    @Override
    public ASTNode visitNegateUnary(QLParser.NegateUnaryContext ctx) {
        return super.visitNegateUnary(ctx);
    }

    @Override
    public ASTNode visitBangUnary(QLParser.BangUnaryContext ctx) {
        return super.visitBangUnary(ctx);
    }

    @Override
    public ASTNode visitProductBinary(QLParser.ProductBinaryContext ctx) {
        return super.visitProductBinary(ctx);
    }

    @Override
    public ASTNode visitDivideBinary(QLParser.DivideBinaryContext ctx) {
        return super.visitDivideBinary(ctx);
    }

    @Override
    public ASTNode visitPlusBinary(QLParser.PlusBinaryContext ctx) {
        return super.visitPlusBinary(ctx);
    }

    @Override
    public ASTNode visitMinusBinary(QLParser.MinusBinaryContext ctx) {
        return super.visitMinusBinary(ctx);
    }

    @Override
    public ASTNode visitLessThanBinary(QLParser.LessThanBinaryContext ctx) {
        return super.visitLessThanBinary(ctx);
    }

    @Override
    public ASTNode visitLessThanEqualBinary(QLParser.LessThanEqualBinaryContext ctx) {
        return super.visitLessThanEqualBinary(ctx);
    }

    @Override
    public ASTNode visitGreaterThanBinary(QLParser.GreaterThanBinaryContext ctx) {
        return super.visitGreaterThanBinary(ctx);
    }

    @Override
    public ASTNode visitGreaterThanEqualBinary(QLParser.GreaterThanEqualBinaryContext ctx) {
        return super.visitGreaterThanEqualBinary(ctx);
    }

    @Override
    public ASTNode visitEqualBinary(QLParser.EqualBinaryContext ctx) {
        return super.visitEqualBinary(ctx);
    }

    @Override
    public ASTNode visitNotEqualBinary(QLParser.NotEqualBinaryContext ctx) {
        return super.visitNotEqualBinary(ctx);
    }

    @Override
    public ASTNode visitAndBinary(QLParser.AndBinaryContext ctx) {
        return super.visitAndBinary(ctx);
    }

    @Override
    public ASTNode visitOrBinary(QLParser.OrBinaryContext ctx) {
        return super.visitOrBinary(ctx);
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
