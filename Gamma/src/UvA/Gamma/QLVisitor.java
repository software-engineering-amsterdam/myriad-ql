package UvA.Gamma;

import UvA.Gamma.AST.*;
import UvA.Gamma.AST.Expressions.BooleanExpression;
import UvA.Gamma.AST.Expressions.Expression;
import UvA.Gamma.AST.Expressions.MoneyExpression;
import UvA.Gamma.AST.Expressions.NumberExpression;
import UvA.Gamma.AST.Values.Boolean;
import UvA.Gamma.AST.Values.*;
import UvA.Gamma.AST.Values.Number;
import UvA.Gamma.Antlr.QL.QLBaseVisitor;
import UvA.Gamma.Antlr.QL.QLParser;


/**
 * Created by Tjarco, 08-02-17.
 */

public class QLVisitor extends QLBaseVisitor<ASTNode> {

    @Override
    public Form visitForm(QLParser.FormContext ctx) {
        String id = ctx.ID().getText();
        Form form = new Form(id);
        for (QLParser.FormItemContext formItemContext : ctx.formItem()) {
            form.addFormItem((FormItem) visit(formItemContext));
        }
        return form;
    }

    @Override
    public Question visitQuestion(QLParser.QuestionContext ctx) {
        String questionString = ctx.STRING_LITERAL().getText();
        String id = ctx.ID().getText();
        Value value = (Value) visit(ctx.type());
        return new Question(questionString, id, value);
    }

    @Override
    public Computed visitComputed(QLParser.ComputedContext ctx) {
        String label = ctx.STRING_LITERAL().getText();
        String id = ctx.ID().getText();
        Expression expression = (Expression) visit(ctx.expression());
        return new Computed(label, id, expression);
    }

    @Override
    public Condition visitCondition(QLParser.ConditionContext ctx) {
        BooleanExpression expression = new BooleanExpression(ctx.boolExpr().getText());
        Condition condition = new Condition(expression);
        for (QLParser.FormItemContext formItemContext : ctx.formItem()) {
            condition.addThenBlockItem((FormItem) visit(formItemContext));
        }

        if (ctx.elseblock() != null) {
            for (QLParser.FormItemContext elseItemContext : ctx.elseblock().formItem()) {
                condition.addElseBlockItem((FormItem) visit(elseItemContext));
            }
        }

        return condition;
    }

    @Override
    public BooleanExpression visitBooleanExpression(QLParser.BooleanExpressionContext ctx) {
        return new BooleanExpression(ctx.boolExpr().getText());
    }


    @Override
    public NumberExpression visitNumberExpression(QLParser.NumberExpressionContext ctx) {
        return new NumberExpression(ctx.numExpr().getText());
    }

    @Override
    public MoneyExpression visitMoneyExpression(QLParser.MoneyExpressionContext ctx) {
        return new MoneyExpression(ctx.numExpr().getText());
    }

    @Override
    public Boolean visitBooleanType(QLParser.BooleanTypeContext ctx) {
        return new Boolean(false);
    }

    @Override
    public Number visitIntType(QLParser.IntTypeContext ctx) {
        return new Number(0);
    }

    @Override
    public Number visitDecimalType(QLParser.DecimalTypeContext ctx) {
        return new Number(0);
    }

    @Override
    public Money visitMoneyType(QLParser.MoneyTypeContext ctx) {
        return new Money(0);
    }

    @Override
    public StringValue visitStringType(QLParser.StringTypeContext ctx) {
        return new StringValue();
    }

    @Override
    public ASTNode visitDateType(QLParser.DateTypeContext ctx) {
        return new DateValue("");
    }

}
