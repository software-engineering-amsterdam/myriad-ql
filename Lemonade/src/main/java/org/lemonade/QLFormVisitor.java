package org.lemonade;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.lemonade.expression.Type;

/**
 *
 */
public class QLFormVisitor extends QLBaseVisitor<ASTNode> {

    @Override
    public Form visitForm(QLParser.FormContext ctx) {
        System.err.println("Entering form");
        System.err.println("FORMLIDENT:" + ctx.identifier().getText());
        String label = ctx.identifier().getText();
        for (QLParser.BlockContext block : ctx.block()) {
            block.accept(this);
        }
        return new Form(ctx.identifier().getText());

    }

    @Override
    public ASTNode visitBlock(QLParser.BlockContext ctx) {
        System.err.println("Entering block");
        return super.visitChildren(ctx);
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
        return super.visitConditional(ctx);
    }

    @Override
    public ASTNode visitExpr(QLParser.ExprContext ctx) {
        return super.visitExpr(ctx);
    }

    @Override
    public ASTNode visitUnaryoperator(QLParser.UnaryoperatorContext ctx) {
        return super.visitUnaryoperator(ctx);
    }

    @Override
    public ASTNode visitBinaryoperator(QLParser.BinaryoperatorContext ctx) {
        return super.visitBinaryoperator(ctx);
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
