package org.lemonade.visitors;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.lemonade.QLBaseVisitor;
import org.lemonade.QLParser;
import org.lemonade.exceptions.QLOperatorException;
import org.lemonade.nodes.*;
import org.lemonade.nodes.expressions.BinaryExpression;
import org.lemonade.nodes.expressions.Expression;
import org.lemonade.nodes.expressions.binary.*;
import org.lemonade.nodes.expressions.literal.*;
import org.lemonade.nodes.expressions.unary.BangUnary;
import org.lemonade.nodes.expressions.unary.NegUnary;
import org.lemonade.nodes.types.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class FormVisitor extends QLBaseVisitor<ASTNode> {

    @Override
    public Form visitForm(QLParser.FormContext ctx) {
        IdentifierLiteral identifier = (IdentifierLiteral) ctx.identifier().accept(this);
        List<Body> bodies = new ArrayList<>();

        for (QLParser.BodyContext body : ctx.body()) {
            bodies.add((Body) body.accept(this));
        }

        return new Form(identifier, bodies);
    }

    @Override
    public ASTNode visitQuestion(QLParser.QuestionContext ctx) {
        IdentifierLiteral identifier = (IdentifierLiteral) ctx.identifier().accept(this);
        String label = ctx.label().getText();
        QLType type = (QLType) ctx.type_specifier().accept(this);
        Position position = constructPosition(ctx);

        Question question = new Question(identifier, label, type);
        question.setPosition(position);

        return question;
    }

    @Override
    public ASTNode visitConditional(QLParser.ConditionalContext ctx) {
        Expression expression = (Expression) ctx.expr().accept(this);
        Position position = constructPosition(ctx);

        List<Body> bodies = new ArrayList<Body>();
        for (QLParser.BodyContext body : ctx.body()) {
            bodies.add((Body) body.accept(this));
        }
        Conditional conditional = new Conditional(expression, bodies);
        conditional.setPosition(position);

        return conditional;
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
    public ASTNode visitParenExpr(QLParser.ParenExprContext ctx) {
        return ctx.expr().accept(this);
    }

    @Override
    public ASTNode visitUnaryMinusExpr(QLParser.UnaryMinusExprContext ctx) {
        Expression expr = (Expression) ctx.expr().accept(this);
        Position position = constructPosition(ctx);

        NegUnary neg = new NegUnary(expr);
        neg.setPosition(position);
        return neg;
    }

    @Override
    public ASTNode visitProductDivideExpr(QLParser.ProductDivideExprContext ctx) {
        Expression left = (Expression) ctx.expr().get(0).accept(this);
        Expression right = (Expression) ctx.expr().get(1).accept(this);
        Position position = constructPosition(ctx);
        BinaryExpression expr;
        try {
            switch (ctx.op.getType()) {
                case QLParser.PRODUCT:
                    expr = new ProductBinary(left, right);
                    expr.setPosition(position);
                    return expr;
                case QLParser.DIVIDE:
                    expr = new DivideBinary(left, right);
                    expr.setPosition(position);
                    return expr;
                default:
                    throw new QLOperatorException("");//TODO change type of error
            }
        } catch (QLOperatorException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public ASTNode visitAtomExpr(QLParser.AtomExprContext ctx) {
        return ctx.atom().accept(this);
    }

    @Override
    public ASTNode visitOrExpr(QLParser.OrExprContext ctx) {
        Expression left = (Expression) ctx.expr().get(0).accept(this);
        Expression right = (Expression) ctx.expr().get(1).accept(this);
        Position position = constructPosition(ctx);
        OrBinary or = new OrBinary(left, right);
        or.setPosition(position);

        return or;
    }

    @Override
    public ASTNode visitPlusMinExpr(QLParser.PlusMinExprContext ctx) {
        Expression left = (Expression) ctx.expr().get(0).accept(this);
        Expression right = (Expression) ctx.expr().get(1).accept(this);
        Position position = constructPosition(ctx);
        Expression expr;

        switch (ctx.op.getType()) {
            case QLParser.PLUS:
                expr = new PlusBinary(left, right);
                expr.setPosition(position);
                return expr;
            case QLParser.MINUS:
                expr = new MinusBinary(left, right);
                expr.setPosition(position);
                return expr;
            default:
                throw new IllegalArgumentException();//TODO change type of error
        }
    }

    @Override
    public ASTNode visitUnaryBangExpr(QLParser.UnaryBangExprContext ctx) {
        Expression expr = (Expression) ctx.expr().accept(this);
        Position position = constructPosition(ctx);
        BangUnary bang = new BangUnary(expr);
        bang.setPosition(position);
        return bang;
    }

    @Override
    public ASTNode visitRelationalExpr(QLParser.RelationalExprContext ctx) {
        Expression left = (Expression) ctx.expr().get(0).accept(this);
        Expression right = (Expression) ctx.expr().get(1).accept(this);
        Position position = constructPosition(ctx);

        Expression expr;
        switch (ctx.op.getType()) {
            case QLParser.LT:
                expr = new LTBinary(left, right);
                expr.setPosition(position);
                return expr;
            case QLParser.GT:
                expr = new GTBinary(left, right);
                expr.setPosition(position);
                return expr;
            case QLParser.LTEQ:
                expr = new LTEBinary(left, right);
                expr.setPosition(position);
                return expr;
            case QLParser.GTEQ:
                expr = new GTEBinary(left, right);
                expr.setPosition(position);
                return expr;
            default:
                throw new IllegalArgumentException();//TODO change type of error
        }
    }

    @Override
    public ASTNode visitEqualityExpr(QLParser.EqualityExprContext ctx) {
        Expression left = (Expression) ctx.expr().get(0).accept(this);
        Expression right = (Expression) ctx.expr().get(1).accept(this);
        Position position = constructPosition(ctx);
        Expression expr;

        switch (ctx.op.getType()) {
            case QLParser.EQ:
                expr = new EqBinary(left, right);
                expr.setPosition(position);
                return expr;
            case QLParser.NEQ:
                expr = new NEqBinary(left, right);
                expr.setPosition(position);
                return expr;
            default:
                throw new IllegalArgumentException();//TODO change type of error
        }
    }

    @Override
    public ASTNode visitAndExpr(QLParser.AndExprContext ctx) {
        Expression left = (Expression) ctx.expr().get(0).accept(this);
        Expression right = (Expression) ctx.expr().get(1).accept(this);
        Position position = constructPosition(ctx);
        AndBinary and = new AndBinary(left, right);
        and.setPosition(position);

        return and;
    }

    @Override
    public ASTNode visitBooleanAtom(QLParser.BooleanAtomContext ctx) {
        return new BooleanLiteral(ctx.BOOLEAN().getText());
    }

    @Override
    public ASTNode visitStringAtom(QLParser.StringAtomContext ctx) {
        return new StringLiteral(ctx.STR().getText());
    }

    @Override
    public ASTNode visitIdentifierAtom(QLParser.IdentifierAtomContext ctx) {
        return new IdentifierLiteral(ctx.IDENT().getText());
    }

    @Override
    public ASTNode visitIdentifierValue(QLParser.IdentifierValueContext ctx) {
        return new IdentifierLiteral(ctx.IDENT().getText());
    }

    @Override
    public ASTNode visitIntegerAtom(QLParser.IntegerAtomContext ctx) {
        return new IntegerLiteral(ctx.INT().getText());
    }

    @Override
    public ASTNode visitDecimalAtom(QLParser.DecimalAtomContext ctx) {
        return new DecimalLiteral(ctx.DECIMAL().getText());
    }

    @Override
    public ASTNode visitDateAtom(QLParser.DateAtomContext ctx) {
        int day = Integer.parseInt(ctx.date().DOUBLEDIGIT(0).getText());
        int month = Integer.parseInt(ctx.date().DOUBLEDIGIT(1).getText());
        int year = Integer.parseInt(ctx.date().QUADDIGIT().getText());
        return new DateLiteral(LocalDate.of(year, month, day));
    }

    private Position constructPosition(ParserRuleContext ctx) {
        Token startTok = ctx.getStart();
        int line = startTok.getLine();
        int column = startTok.getCharPositionInLine();

        return new Position(line, column);
    }
}
