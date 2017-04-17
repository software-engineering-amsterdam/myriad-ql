package UvA.Gamma;

import UvA.Gamma.AST.*;
import UvA.Gamma.AST.Expression.Expression;
import UvA.Gamma.AST.Expression.Identifier;
import UvA.Gamma.AST.Expression.Operands.BooleanOperands.*;
import UvA.Gamma.AST.Expression.Operands.NumberOperands.*;
import UvA.Gamma.AST.Expression.Values.BooleanValue;
import UvA.Gamma.AST.Expression.Values.IdentifierValue;
import UvA.Gamma.AST.Expression.Values.NumberValue;
import UvA.Gamma.AST.Types.*;
import UvA.Gamma.Antlr.QL.QLBaseVisitor;
import UvA.Gamma.Antlr.QL.QLParser;
import UvA.Gamma.Visitors.IdentifierInitVisitor;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Tjarco, 08-02-17.
 */

public class ASTBuilder extends QLBaseVisitor<ASTNode> {
    Map<Identifier, Type> identifierTypes;

    @Override
    public Form visitForm(QLParser.FormContext ctx) {
        String id = ctx.ID().getText();
        Form form = new Form(id);
        identifierTypes = new HashMap<>();
        for (QLParser.FormItemContext formItemContext : ctx.formItem()) {
            form.addFormItem((FormItem) visit(formItemContext));
            visit(formItemContext);
        }
        initializeIdentifiers(form);
        return form;
    }

    private void initializeIdentifiers(Form form) {
        identifierTypes.forEach((identifier, type) -> {
            IdentifierInitVisitor identifierInitVisitor = new IdentifierInitVisitor(identifier, type);
            form.forEach(formItem -> formItem.accept(identifierInitVisitor));
        });
    }

    @Override
    public Question visitQuestion(QLParser.QuestionContext ctx) {
        String questionString = ctx.STRING_LITERAL().getText().replaceAll("\"", "");
        Identifier identifier = new Identifier(ctx.ID().getText());
        Type type = (Type) visit(ctx.type());
        identifierTypes.put(identifier, type);
        return new Question(questionString, identifier, type);
    }

    @Override
    public Computed visitComputed(QLParser.ComputedContext ctx) {
        String label = ctx.STRING_LITERAL().getText().replaceAll("\"", "");
        Expression expression = (Expression) visit(ctx.expression());
        Identifier identifier = new Identifier(ctx.ID().getText());
        Type type = (Type) visit(ctx.type());
        identifierTypes.put(identifier, type);
        Computed c = new Computed(label, identifier, type, expression);
        c.getStringValueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("new value for " + identifier + ": " + newValue);
        });
        return c;
    }

    @Override
    public Condition visitCondition(QLParser.ConditionContext ctx) {
        Expression expression = (Expression) visit(ctx.boolExpression());
        Condition condition = new Condition(expression);
        for (QLParser.FormItemContext thenItem : ctx.formItem()) {
            condition.addThenBlockItem((FormItem) visit(thenItem));
        }
        if (ctx.elseblock() != null) {
            for (QLParser.FormItemContext elseItem : ctx.elseblock().formItem()) {
                condition.addElseBlockItem((FormItem) visit(elseItem));
            }
        }
        return condition;
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
    public Expression visitLogicalBooleanExpression(QLParser.LogicalBooleanExpressionContext ctx) {
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
    public ASTNode visitNegatedBooleanExpression(QLParser.NegatedBooleanExpressionContext ctx) {
        return new Not((Expression) visit(ctx.boolExpression()));
    }

    @Override
    public ASTNode visitBooleanValueExpression(QLParser.BooleanValueExpressionContext ctx) {
        return new BooleanValue(ctx.getText());
    }

    @Override
    public ASTNode visitBooleanIdentifierExpression(QLParser.BooleanIdentifierExpressionContext ctx) {
        return new IdentifierValue(ctx.ID().getText());
    }

    @Override
    public ASTNode visitIdentifierExpression(QLParser.IdentifierExpressionContext ctx) {
        return new IdentifierValue(ctx.ID().getText());
    }

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

//    @Override
//    public DateType visitDateType(QLParser.DateTypeContext ctx) {
//        return new DateType();
//    }

}
