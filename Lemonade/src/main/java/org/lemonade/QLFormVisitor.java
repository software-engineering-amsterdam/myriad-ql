package org.lemonade;

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
        return new Form(ctx.identifier().getText(), 42);

    }

    @Override
    public ASTNode visitBlock(QLParser.BlockContext ctx) {
        System.err.println("Entering block");
        return super.visitChildren(ctx);
    }

    @Override
    public ASTNode visitQuestion(QLParser.QuestionContext ctx) {
        System.err.println("entering question");
        return super.visitQuestion(ctx);
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
}
