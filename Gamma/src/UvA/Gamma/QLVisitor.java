package UvA.Gamma;

import UvA.Gamma.AST.*;
import UvA.Gamma.AST.Expressions.BooleanExpression;
import UvA.Gamma.AST.Expressions.Expression;
import UvA.Gamma.AST.Expressions.MoneyExpression;
import UvA.Gamma.AST.Expressions.NumberExpression;
import UvA.Gamma.Antlr.QL.QLBaseVisitor;
import UvA.Gamma.Antlr.QL.QLParser;


/**
 * Created by Tjarco on 08-02-17.
 */

public class QLVisitor extends QLBaseVisitor<ASTNode> {
    private Form form;

    QLVisitor() {
        form = new Form();
    }

    public Form getForm() {
        return this.form;
    }

    @Override
    public Form visitForm(QLParser.FormContext ctx) {
        for (QLParser.FormItemContext formItemContext : ctx.formItem()) {
            form.addFormItem((FormItem) visit(formItemContext));
        }
        return form;
    }

    @Override
    public Question visitQuestion(QLParser.QuestionContext ctx) {
        Question question = new Question();
        question.setQuestion(ctx.STRING_LITERAL().getText());
        question.setId(ctx.ID().getText());
        question.setType(ctx.type().getText());
        return question;
    }

    @Override
    public Computed visitComputed(QLParser.ComputedContext ctx) {
        Computed computed = new Computed();
        computed.setLabel(ctx.STRING_LITERAL().getText());
        computed.setId(ctx.ID().getText());
        computed.setType("");
        computed.setExpression((Expression) visit(ctx.expression()));
        return computed;
    }

    @Override
    public Condition visitCondition(QLParser.ConditionContext ctx) {
        Condition condition = new Condition();
        condition.setExpression(new BooleanExpression(ctx.boolExpr().getText()));
        for (QLParser.FormItemContext formItemContext : ctx.formItem()) {
            condition.addFormItem((FormItem) visit(formItemContext));
        }

        if (ctx.elseblock() != null) {
            for (QLParser.FormItemContext elseItemContext : ctx.elseblock().formItem()) {
                condition.addElseBlockItem((FormItem) visit(elseItemContext));
            }
        }

        return condition;
    }

    @Override
    public ASTNode visitBooleanExpression(QLParser.BooleanExpressionContext ctx) {
        return new BooleanExpression(ctx.boolExpr().getText());
    }

    @Override
    public NumberExpression visitNumberExpression(QLParser.NumberExpressionContext ctx) {
        return new NumberExpression(ctx.numExpr().getText());
    }

    @Override
    public ASTNode visitMoneyExpression(QLParser.MoneyExpressionContext ctx) {
        return new MoneyExpression(ctx.numExpr().getText());
    }
}
