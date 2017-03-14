package org.lemonade.visitors;

import org.lemonade.gui.GuiBody;
import org.lemonade.gui.GuiConditional;
import org.lemonade.gui.GuiForm;
import org.lemonade.gui.GuiQuestion;
import org.lemonade.gui.values.GuiIdentifierValue;
import org.lemonade.gui.values.GuiValue;
import org.lemonade.nodes.Body;
import org.lemonade.nodes.Conditional;
import org.lemonade.nodes.Form;
import org.lemonade.nodes.Question;
import org.lemonade.nodes.expressions.Expression;
import org.lemonade.nodes.expressions.Literal;
import org.lemonade.nodes.expressions.binary.*;
import org.lemonade.nodes.expressions.literal.*;
import org.lemonade.nodes.expressions.unary.BangUnary;
import org.lemonade.nodes.expressions.unary.NegUnary;
import org.lemonade.visitors.interfaces.BaseVisitor;
import org.lemonade.visitors.interfaces.ExpressionVisitor;
import org.lemonade.visitors.interfaces.LiteralVisitor;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class EvaluateVisitor implements
        BaseVisitor<Expression>, ExpressionVisitor<Expression>, LiteralVisitor<Expression>, UpdateVisitor {

    private Map<GuiIdentifierValue, GuiValue<?>> guiEnvironment;
    private Map<String, Literal<?>> literalEnvironment;

    public EvaluateVisitor() {
        this.guiEnvironment = new HashMap<>();
        this.literalEnvironment = new HashMap<>();
    }

    @Override
    public Expression visit(Form form) {
        for (Body body : form.getBodies()) {
            body.accept(this);
        }
        return form.accept((BaseVisitor<Expression>) this);
    }

    @Override
    public Expression visit(Question question) {
        IdentifierLiteral identifier = question.getIdentifier();
        //        Literal<?> literal = question.getValue();
        //
        //        assert !literalEnvironment.containsKey(identifier);
        //        literalEnvironment.put(identifier, new UndefinedValue(question.getType()));
        return null;
    }

    @Override
    public Expression visit(Conditional conditional) {
        conditional.getCondition().accept(this);
        return null;
    }

    @Override
    public Expression visit(AndBinary andBinary) {
        BooleanLiteral left = (BooleanLiteral) andBinary.getLeft().accept(this);
        BooleanLiteral right = (BooleanLiteral) andBinary.getRight().accept(this);
        return left.and(right);
    }

    @Override
    public Expression visit(OrBinary orBinary) {
        BooleanLiteral left = (BooleanLiteral) orBinary.getLeft().accept(this);
        BooleanLiteral right = (BooleanLiteral) orBinary.getRight().accept(this);
        return left.or(right);
    }

    @Override
    public Expression visit(PlusBinary plusBinary) {
        NumericLiteral<?> left = (NumericLiteral<?>) plusBinary.getLeft().accept(this);
        NumericLiteral<?> right = (NumericLiteral<?>) plusBinary.getRight().accept(this);
        System.err.println(left.plus(right));
        return left.plus(right);
    }

    @Override
    public Expression visit(ProductBinary productBinary) {
        NumericLiteral<?> left = (NumericLiteral<?>) productBinary.getLeft().accept(this);
        NumericLiteral<?> right = (NumericLiteral<?>) productBinary.getRight().accept(this);
        System.err.println(left.product(right));
        return left.product(right);
    }

    @Override
    public Expression visit(MinusBinary minusBinary) {
        NumericLiteral<?> left = (NumericLiteral<?>) minusBinary.getLeft().accept(this);
        NumericLiteral<?> right = (NumericLiteral<?>) minusBinary.getRight().accept(this);
        System.err.println(left.minus(right));
        return left.minus(right);
    }

    @Override
    public Expression visit(DivideBinary divideBinary) {
        NumericLiteral<?> left = (NumericLiteral<?>) divideBinary.getLeft().accept(this);
        NumericLiteral<?> right = (NumericLiteral<?>) divideBinary.getRight().accept(this);
        System.err.println(left.divide(right));
        return left.divide(right);
    }

    @Override
    public Expression visit(EqBinary eqBinary) {
        Literal<?> left = (Literal<?>) eqBinary.getLeft().accept(this);
        Literal<?> right = (Literal<?>) eqBinary.getRight().accept(this);
        System.err.println(left.equals(right));
        return left.eq(right);
    }

    @Override
    public Expression visit(NEqBinary nEqBinary) {
        Literal<?> left = (Literal<?>) nEqBinary.getLeft().accept(this);
        Literal<?> right = (Literal<?>) nEqBinary.getRight().accept(this);
        System.err.println(left.nEq(right));
        return left.nEq(right);
    }

    @Override
    public Expression visit(GTBinary gtBinary) {
        ComparableLiteral<?> left = (ComparableLiteral<?>) gtBinary.getLeft().accept(this);
        ComparableLiteral<?> right = (ComparableLiteral<?>) gtBinary.getRight().accept(this);
        System.err.println(left.gT(right));
        return left.gT(right);
    }

    @Override
    public Expression visit(GTEBinary gteBinary) {
        ComparableLiteral<?> left = (ComparableLiteral<?>) gteBinary.getLeft().accept(this);
        ComparableLiteral<?> right = (ComparableLiteral<?>) gteBinary.getRight().accept(this);
        System.err.println(left.gTEq(right));
        return left.gTEq(right);
    }

    @Override
    public Expression visit(LTBinary ltBinary) {
        ComparableLiteral<?> left = (ComparableLiteral<?>) ltBinary.getLeft().accept(this);
        ComparableLiteral<?> right = (ComparableLiteral<?>) ltBinary.getRight().accept(this);
        System.err.println(left.lT(right));
        return left.lT(right);
    }

    @Override
    public Expression visit(LTEBinary lteBinary) {
        ComparableLiteral<?> left = (ComparableLiteral<?>) lteBinary.getLeft().accept(this);
        ComparableLiteral<?> right = (ComparableLiteral<?>) lteBinary.getRight().accept(this);
        System.err.println(left.lTEq(right));
        return left.lTEq(right);
    }

    @Override
    public Expression visit(BangUnary bangUnary) {
        BooleanLiteral expression = (BooleanLiteral) bangUnary.getExpression().accept(this);
        return expression.bang();

    }

    @Override
    public Expression visit(NegUnary negUnary) {
        NumericLiteral<?> expression = (NumericLiteral<?>) negUnary.getExpression().accept(this);
        return expression.neg();
    }

    @Override
    public Expression visit(BooleanLiteral booleanValue) {
        return booleanValue;
    }

    @Override
    public Expression visit(DecimalLiteral decimalValue) {
        return decimalValue;
    }

    @Override
    public Expression visit(MoneyLiteral moneyValue) {
        return moneyValue;
    }

    @Override
    public Expression visit(IntegerLiteral integerValue) {
        return integerValue;
    }

    @Override
    public Expression visit(StringLiteral stringValue) {
        return stringValue;
    }

    @Override
    public Expression visit(DateLiteral dateLiteral) {
        return null;
    }

    @Override
    public Expression visit(IdentifierLiteral identifierValue) {
        if (!literalEnvironment.containsKey(identifierValue.getValue())) {
            throw new RuntimeException("Symbol not found!");
        }
        return literalEnvironment.get(identifierValue.getValue());
    }

    @Override
    public GuiForm visit(final GuiForm form) {
        for (GuiBody body : form.getBodies()) {
            body.accept(this);
        }
        return null;
    }

    @Override
    public GuiBody visit(final GuiBody body) {
        return body.accept(this);
    }

<<<<<<< Updated upstream
    @Override public GuiQuestion visit(final GuiQuestion question) {
//        guiEnvironment.put(question.getIdentifier(), question.getValue());
=======
    @Override
    public GuiQuestion visit(final GuiQuestion question) {
        guiEnvironment.put(question.getIdentifier(), question.getValue());
>>>>>>> Stashed changes
        return question;
    }

    @Override
    public GuiConditional visit(final GuiConditional conditional) {
        //        if ()
        return conditional;
    }
}
