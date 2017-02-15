package UvA.Gamma;

import UvA.Gamma.AST.*;
import UvA.Gamma.AST.Values.Boolean;
import UvA.Gamma.AST.Values.Number;
import UvA.Gamma.Antlr.QL.QLBaseVisitor;
import UvA.Gamma.Antlr.QL.QLParser;


/**
 * Created by Tjarco on 08-02-17.
 */

public class QLVisitor extends QLBaseVisitor<ASTNode>{
    private Form form;

    QLVisitor() {
        form = new Form();
    }

    public Form getForm(){
        return this.form;
    }

    @Override
    public Form visitForm(QLParser.FormContext ctx) {
        for (QLParser.FormItemContext formItemContext: ctx.formItem()){
            form.addFormItem((FormItem)visit(formItemContext));
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
        computed.setType(ctx.type().getText());
        computed.setExpression(visit(ctx.expression()).toString());
        return computed;
    }

    @Override
    public Condition visitCondition(QLParser.ConditionContext ctx) {
        Condition condition = new Condition();
        condition.setExpression(visit(ctx.boolExpr()).toString());
        return condition;
    }

    //Expressions
    @Override
    public Number visitDiv(QLParser.DivContext ctx) {
        Number left = (Number)visit(ctx.numExpr(0));
        Number right  = (Number)visit(ctx.numExpr(1));
        return ctx.op.getText().equals("*") ? left.multiply(right) : left.divide(right);
    }

    @Override
    public ASTNode visitExpression(QLParser.ExpressionContext ctx) {
        super.visitExpression(ctx);
        return super.visitExpression(ctx);
    }

    @Override
    public Number visitAdd(QLParser.AddContext ctx) {
        Number left = (Number)visit(ctx.numExpr(0));
        Number right  = (Number)visit(ctx.numExpr(1));
        return ctx.op.getText().equals("+") ? left.add(right) : left.subtract(right);
    }

    @Override
    public Number visitNum(QLParser.NumContext ctx) {
        return new Number(ctx.getText());
    }

    @Override
    public Boolean visitAndor(QLParser.AndorContext ctx) {
        Boolean left = (Boolean)visit(ctx.boolExpr(0));
        Boolean right = (Boolean)visit(ctx.boolExpr(1));
        switch (ctx.op.getText()){
            case "&&":
                return new Boolean(left.and(right));
            case "||":
                return new Boolean(left.or(right));
            case "==":
                return new Boolean(left.equals(right));
            case  "!=":
                return new Boolean(!left.equals(right));
            default:
                return left;
        }
    }

    @Override
    public Boolean visitBool(QLParser.BoolContext ctx) {
        return new Boolean(ctx.getText());
    }
}
