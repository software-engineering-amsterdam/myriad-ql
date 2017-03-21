package UvA.Gamma;

import UvA.Gamma.AST.ASTNode;
import UvA.Gamma.AST.Expression.Expression;
import UvA.Gamma.AST.Expression.Operands.BooleanOperands.*;
import UvA.Gamma.AST.Expression.Operands.NumberOperands.*;
import UvA.Gamma.AST.Expression.Values.BooleanValue;
import UvA.Gamma.AST.Expression.Values.NumberValue;
import UvA.Gamma.AST.Form;
import UvA.Gamma.AST.Types.*;
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
//            form.addFormItem((FormItem) visit(formItemContext));
            visit(formItemContext);
        }
        return form;
    }

//    @Override
//    public Question visitQuestion(QLParser.QuestionContext ctx) {
//        String questionString = ctx.STRING_LITERAL().getText().replaceAll("\"", "");
//        String id = ctx.ID().getText();
//        Value value = (Value) visit(ctx.type());
//        return new Question(questionString, id, value);
//    }

    @Override
    public ASTNode visitComputed(QLParser.ComputedContext ctx) {
        String label = ctx.STRING_LITERAL().getText().replaceAll("\"", "");
        String id = ctx.ID().getText();
        Expression expression = (Expression) visit(ctx.expression());
        System.out.println(expression.value());
        return super.visitComputed(ctx);
    }

    @Override
    public Expression visitNumberExpression(QLParser.NumberExpressionContext ctx) {
        return (Expression) visit(ctx.numExpression());
    }

    @Override
    public NumberOperand visitAddExpression(QLParser.AddExpressionContext ctx) {
        Expression left = (Expression) visit(ctx.numExpression(0));
        Expression right = (Expression) visit(ctx.numExpression(1));
        if (ctx.op.getText().equals("+")) {
            return new Add(left, right);
        } else {
            return new Subtract(left, right);
        }
    }

    @Override
    public NumberOperand visitMultiExpression(QLParser.MultiExpressionContext ctx) {
        Expression left = (Expression) visit(ctx.numExpression(0));
        Expression right = (Expression) visit(ctx.numExpression(1));
        if (ctx.op.getText().equals("*")) {
            return new Multiply(left, right);
        } else {
            return new Divide(left, right);
        }
    }

    @Override
    public NumberValue visitNumberValueExpression(QLParser.NumberValueExpressionContext ctx) {
        return new NumberValue(ctx.getText());
    }

    @Override
    public Expression visitBooleanExpression(QLParser.BooleanExpressionContext ctx) {
        return (Expression) visit(ctx.boolExpression());
    }

    @Override
    public ASTNode visitLogicalBooleanExpression(QLParser.LogicalBooleanExpressionContext ctx) {
        Expression left = (Expression) visit(ctx.boolExpression(0));
        Expression right = (Expression) visit(ctx.boolExpression(1));
        switch (ctx.op.getText()) {
            case "&&":
                return new And(left, right);
            case "||":
                return new Or(left, right);
            case "==":
                return new Equals(left, right);
            case "!=":
                return new NotEquals(left, right);
            default:
                System.err.println("Unimplemented operand");
                System.exit(1);
                return null;
        }
    }

    @Override
    public ASTNode visitLogicalIntegerExpression(QLParser.LogicalIntegerExpressionContext ctx) {
        Expression left = (Expression) visit(ctx.numExpression(0));
        Expression right = (Expression) visit(ctx.numExpression(1));
        switch (ctx.op.getText()) {
            case "<":
                return new SmallerThan(left, right);
            case "<=":
                return new SmallerOrEqual(left, right);
            case "==":
                return new Equals(left, right);
            case "!=":
                return new NotEquals(left, right);
            case ">":
                return new LargerThan(left, right);
            case ">=":
                return new LargerOrEqual(left, right);
            default:
                System.err.println("Unimplemented operand");
                System.exit(1);
                return null;
        }
    }

    @Override
    public ASTNode visitBooleanValueExpression(QLParser.BooleanValueExpressionContext ctx) {
        return new BooleanValue(ctx.getText());
    }

    //
//    @Override
//    public Condition visitCondition(QLParser.ConditionContext ctx) {
//        BooleanExpression expression = new BooleanExpression(ctx.boolExpr().getText());
//        Condition condition = new Condition(expression);
//        for (QLParser.FormItemContext formItemContext : ctx.formItem()) {
//            condition.addThenBlockItem((FormItem) visit(formItemContext));
//        }
//
//        if (ctx.elseblock() != null) {
//            for (QLParser.FormItemContext elseItemContext : ctx.elseblock().formItem()) {
//                condition.addElseBlockItem((FormItem) visit(elseItemContext));
//            }
//        }
//
//        return condition;
//    }

//    @Override
//    public BooleanExpression visitBooleanExpression(QLParser.BooleanExpressionContext ctx) {
//        return new BooleanExpression(ctx.boolExpr().getText());
//    }
//
//
//    @Override
//    public NumberExpression visitNumberExpression(QLParser.NumberExpressionContext ctx) {
//        return new NumberExpression(ctx.numExpr().getText());
//    }
//
//    @Override
//    public MoneyExpression visitMoneyExpression(QLParser.MoneyExpressionContext ctx) {
//        return new MoneyExpression(ctx.numExpr().getText());
//    }


    @Override
    public BooleanType visitBooleanType(QLParser.BooleanTypeContext ctx) {
        return new BooleanType();
    }

    @Override
    public IntegerType visitIntType(QLParser.IntTypeContext ctx) {
        return new IntegerType();
    }

    @Override
    public DecimalType visitDecimalType(QLParser.DecimalTypeContext ctx) {
        return new DecimalType();
    }

    @Override
    public MoneyType visitMoneyType(QLParser.MoneyTypeContext ctx) {
        return new MoneyType();
    }

    @Override
    public ASTNode visitStringType(QLParser.StringTypeContext ctx) {
        return super.visitStringType(ctx);
    }

    @Override
    public DateType visitDateType(QLParser.DateTypeContext ctx) {
        return new DateType();
    }

}
