package org.lemonade.visitors;

import org.lemonade.QLBaseVisitor;
import org.lemonade.QLParser;
import org.lemonade.nodes.*;
import org.lemonade.nodes.expressions.Expression;
import org.lemonade.nodes.expressions.Value;
import org.lemonade.nodes.expressions.binary.*;
import org.lemonade.nodes.expressions.unary.BangUnary;
import org.lemonade.nodes.expressions.unary.NegUnary;
import org.lemonade.nodes.expressions.value.*;
import org.lemonade.nodes.types.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class FormVisitor extends QLBaseVisitor<ASTNode> {

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

    @Override
    public ASTNode visitUnaryMinusExpr(QLParser.UnaryMinusExprContext ctx) {
        Expression expr = (Expression) ctx.expr().accept(this);
        return new NegUnary(expr);
    }

    @Override
    public ASTNode visitProductDivideExpr(QLParser.ProductDivideExprContext ctx) {
        Expression left = (Expression) ctx.expr().get(0).accept(this);
        Expression right = (Expression) ctx.expr().get(1).accept(this);

        switch (ctx.op.getType()) {
            case QLParser.PRODUCT:
                return new ProductBinary(left, right);
            case QLParser.DIVIDE:
                return new DivideBinary(left, right);
            default:
                throw new IllegalArgumentException();//TODO change type of error
        }
    }

    @Override
    public ASTNode visitAtomExpr(QLParser.AtomExprContext ctx) {
        Value<?> atom = (Value<?>) ctx.atom().accept(this);
        return atom;
    }

    @Override
    public ASTNode visitOrExpr(QLParser.OrExprContext ctx) {
        Expression left = (Expression) ctx.expr().get(0).accept(this);
        Expression right = (Expression) ctx.expr().get(1).accept(this);

        return new OrBinary(left, right);
    }

    @Override
    public ASTNode visitPlusMinExpr(QLParser.PlusMinExprContext ctx) {
        Expression left = (Expression) ctx.expr().get(0).accept(this);
        Expression right = (Expression) ctx.expr().get(1).accept(this);

        switch (ctx.op.getType()) {
            case QLParser.PLUS:
                return new PlusBinary(left, right);
            case QLParser.MINUS:
                return new MinusBinary(left, right);
            default:
                throw new IllegalArgumentException();//TODO change type of error
        }
    }

    @Override
    public ASTNode visitUnaryBangExpr(QLParser.UnaryBangExprContext ctx) {
        Expression expr = (Expression) ctx.expr().accept(this);
        return new BangUnary(expr);
    }

    @Override
    public ASTNode visitRelationalExpr(QLParser.RelationalExprContext ctx) {
        Expression left = (Expression) ctx.expr().get(0).accept(this);
        Expression right = (Expression) ctx.expr().get(1).accept(this);

        switch (ctx.op.getType()) {
            case QLParser.LT:
                return new LTBinary(left, right);
            case QLParser.GT:
                return new GTBinary(left, right);
            case QLParser.LTEQ:
                return new LTEBinary(left, right);
            case QLParser.GTEQ:
                return new GTEBinary(left, right);
            default:
                throw new IllegalArgumentException();//TODO change type of error
        }
    }

    @Override
    public ASTNode visitEqualityExpr(QLParser.EqualityExprContext ctx) {
        Expression left = (Expression) ctx.expr().get(0).accept(this);
        Expression right = (Expression) ctx.expr().get(1).accept(this);

        switch (ctx.op.getType()) {
            case QLParser.EQ:
                return new EqBinary(left, right);
            case QLParser.NEQ:
                return new NEqBinary(left, right);
            default:
                throw new IllegalArgumentException();//TODO change type of error
        }
    }

    @Override
    public ASTNode visitAndExpr(QLParser.AndExprContext ctx) {
        Expression left = (Expression) ctx.expr().get(0).accept(this);
        Expression right = (Expression) ctx.expr().get(1).accept(this);

        return new AndBinary(left, right);
    }

    @Override
    public ASTNode visitBooleanAtom(QLParser.BooleanAtomContext ctx) {
        return new BooleanValue(ctx.BOOLEAN().getText());
    }

    @Override
    public ASTNode visitStringAtom(QLParser.StringAtomContext ctx) {
        return new StringValue(ctx.STR().getText());
    }

    @Override
    public ASTNode visitIdentifierAtom(QLParser.IdentifierAtomContext ctx) {
        return new IdentifierValue(ctx.IDENT().getText());
    }

    @Override
    public ASTNode visitIntegerAtom(QLParser.IntegerAtomContext ctx) {
        return new IntegerValue(ctx.INT().getText());
    }

    @Override
    public ASTNode visitDecimalAtom(QLParser.DecimalAtomContext ctx) {
        return new DecimalValue(ctx.DECIMAL().getText());
    }
}
