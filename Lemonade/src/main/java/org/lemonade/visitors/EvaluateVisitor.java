package org.lemonade.visitors;

import java.util.HashMap;
import java.util.Map;

import org.lemonade.gui.GuiBody;
import org.lemonade.gui.GuiConditional;
import org.lemonade.gui.GuiExpression;
import org.lemonade.gui.GuiForm;
import org.lemonade.gui.GuiQuestion;
import org.lemonade.gui.expressions.binary.GuiAndBinary;
import org.lemonade.gui.expressions.binary.GuiDivideBinary;
import org.lemonade.gui.expressions.binary.GuiEqBinary;
import org.lemonade.gui.expressions.binary.GuiGTBinary;
import org.lemonade.gui.expressions.binary.GuiGTEBinary;
import org.lemonade.gui.expressions.binary.GuiLTBinary;
import org.lemonade.gui.expressions.binary.GuiLTEBinary;
import org.lemonade.gui.expressions.binary.GuiMinusBinary;
import org.lemonade.gui.expressions.binary.GuiNEqBinary;
import org.lemonade.gui.expressions.binary.GuiOrBinary;
import org.lemonade.gui.expressions.binary.GuiPlusBinary;
import org.lemonade.gui.expressions.binary.GuiProductBinary;
import org.lemonade.gui.expressions.unary.GuiBangUnary;
import org.lemonade.gui.expressions.unary.GuiNegUnary;
import org.lemonade.gui.values.GuiBooleanValue;
import org.lemonade.gui.values.GuiDateValue;
import org.lemonade.gui.values.GuiDecimalValue;
import org.lemonade.gui.values.GuiIdentifierValue;
import org.lemonade.gui.values.GuiIntegerValue;
import org.lemonade.gui.values.GuiLabelValue;
import org.lemonade.gui.values.GuiMoneyValue;
import org.lemonade.gui.values.GuiNumericalValue;
import org.lemonade.gui.values.GuiStringValue;
import org.lemonade.gui.values.GuiUndefinedValue;
import org.lemonade.gui.values.GuiValue;
import org.lemonade.visitors.interfaces.GuiExpressionVisitor;

/**
 *
 */
public class EvaluateVisitor implements GuiExpressionVisitor<GuiExpression>, UpdateVisitor {

    public Map<String, GuiValue<?>> guiEnvironment;

    public EvaluateVisitor() {
        this.guiEnvironment = new HashMap<>();
    }

    @Override
    public void visit(final GuiForm form) {
        System.err.println("Evaluate starts");
        for (GuiBody body : form.getBodies()) {
            body.accept(this);
        }
    }

    @Override
    public void visit(final GuiQuestion question) {
        System.err.println(question.getElement().getValue());
        guiEnvironment.put(question.getIdentifier().getValue(), question.getElement().getValue());
    }

    @Override
    public void visit(final GuiConditional conditional) {
        System.err.println("entering condition");
        GuiValue<?> condition = (GuiValue<?>) conditional.getCondition().accept(this);
        if (condition.isDefined() && ((GuiBooleanValue) condition).getValue()) {
            conditional.isVisible(true);
            conditional.getBodies().forEach(body -> body.accept(this));
        } else {
            conditional.getBodies().forEach(body -> body.isVisible(false));
        }
    }

    @Override
    public GuiExpression visit(final GuiAndBinary guiAndBinary) {
        GuiValue<?> left = (GuiValue<?>) guiAndBinary.getLeft().accept(this);
        GuiValue<?> right = (GuiValue<?>) guiAndBinary.getRight().accept(this);
        return left.and(right);
    }

    @Override
    public GuiExpression visit(final GuiOrBinary guiOrBinary) {
        GuiValue<?> left = (GuiValue<?>) guiOrBinary.getLeft().accept(this);
        GuiValue<?> right = (GuiValue<?>) guiOrBinary.getRight().accept(this);
        return left.or(right);
    }

    @Override
    public GuiExpression visit(final GuiEqBinary guiEqBinary) {
        System.err.println("entering equal");
        GuiValue<?> left = (GuiValue<?>) guiEqBinary.getLeft().accept(this);
        GuiValue<?> right = (GuiValue<?>) guiEqBinary.getRight().accept(this);
        return left.eq(right);
    }

    @Override
    public GuiExpression visit(final GuiGTBinary guiGtBinary) {
        GuiValue<?> left = (GuiValue<?>) guiGtBinary.getLeft().accept(this);
        GuiValue<?> right = (GuiValue<?>) guiGtBinary.getRight().accept(this);
        return left.gT(right);
    }

    @Override
    public GuiExpression visit(final GuiGTEBinary guiGteBinary) {
        GuiValue<?> left = (GuiValue<?>) guiGteBinary.getLeft().accept(this);
        GuiValue<?> right = (GuiValue<?>) guiGteBinary.getRight().accept(this);
        return left.gTEq(right);
    }

    @Override
    public GuiExpression visit(final GuiLTBinary guiLtBinary) {
        GuiValue<?> left = (GuiValue<?>) guiLtBinary.getLeft().accept(this);
        GuiValue<?> right = (GuiValue<?>) guiLtBinary.getRight().accept(this);
        return left.lT(right);
    }

    @Override
    public GuiExpression visit(final GuiLTEBinary guiLteBinary) {
        GuiValue<?> left = (GuiValue<?>) guiLteBinary.getLeft().accept(this);
        GuiValue<?> right = (GuiValue<?>) guiLteBinary.getRight().accept(this);
        return left.lTEq(right);
    }

    @Override
    public GuiExpression visit(final GuiNEqBinary guiNEqBinary) {
        GuiValue<?> left = (GuiValue<?>) guiNEqBinary.getLeft().accept(this);
        GuiValue<?> right = (GuiValue<?>) guiNEqBinary.getRight().accept(this);
        return left.nEq(right);
    }

    @Override
    public GuiExpression visit(final GuiDivideBinary guiDivideBinary) {
        GuiValue<?> left = (GuiValue<?>) guiDivideBinary.getLeft().accept(this);
        GuiValue<?> right = (GuiValue<?>) guiDivideBinary.getRight().accept(this);
        return left.div(right);
    }

    @Override
    public GuiExpression visit(final GuiMinusBinary guiMinusBinary) {
        GuiValue<?> left = (GuiValue<?>) guiMinusBinary.getLeft().accept(this);
        GuiValue<?> right = (GuiValue<?>) guiMinusBinary.getRight().accept(this);
        return left.min(right);
    }

    @Override
    public GuiExpression visit(final GuiPlusBinary guiPlusBinary) {
        GuiValue<?> left = (GuiValue<?>) guiPlusBinary.getLeft().accept(this);
        GuiValue<?> right = (GuiValue<?>) guiPlusBinary.getRight().accept(this);
        return left.plus(right);
    }

    @Override
    public GuiExpression visit(final GuiProductBinary guiProductBinary) {
        System.err.println("product");
        GuiValue<?> left = (GuiValue<?>) guiProductBinary.getLeft().accept(this);
        GuiValue<?> right = (GuiValue<?>) guiProductBinary.getRight().accept(this);
        return left.prod(right);
    }

    @Override
    public GuiExpression visit(final GuiBangUnary guiBangUnary) {
        GuiBooleanValue expression = (GuiBooleanValue) guiBangUnary.getExpression().accept(this);
        return expression.bang();
    }

    @Override
    public GuiExpression visit(final GuiNegUnary guiNegUnary) {
        GuiNumericalValue<?> expression = (GuiNumericalValue<?>) guiNegUnary.getExpression().accept(this);
        return expression.neg();
    }

    @Override
    public GuiExpression visit(final GuiBooleanValue booleanValue) {
        return booleanValue;
    }

    @Override
    public GuiExpression visit(final GuiDateValue guiDateValue) {
        return guiDateValue;
    }

    @Override
    public GuiExpression visit(final GuiMoneyValue guiMoneyValue) {
        return guiMoneyValue;
    }

    @Override
    public GuiExpression visit(final GuiIntegerValue guiIntegerValue) {
        return guiIntegerValue;
    }

    @Override
    public GuiExpression visit(final GuiDecimalValue guiDecimalValue) {
        return guiDecimalValue;
    }

    @Override
    public GuiExpression visit(final GuiStringValue guiStringValue) {
        return guiStringValue;
    }

    @Override
    public GuiExpression visit(final GuiIdentifierValue guiIdentifierValue) {
        return guiEnvironment.containsKey(guiIdentifierValue.getValue()) ? guiEnvironment.get(guiIdentifierValue.getValue()) : new GuiUndefinedValue();
    }

    @Override
    public GuiExpression visit(final GuiUndefinedValue guiUndefinedValue) {
        return guiUndefinedValue;
    }

    @Override
    public GuiExpression visit(final GuiLabelValue guiLabelValue) {
        return guiLabelValue;
    }
}
