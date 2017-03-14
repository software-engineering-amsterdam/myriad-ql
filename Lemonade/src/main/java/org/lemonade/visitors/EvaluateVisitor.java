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
import org.lemonade.gui.values.*;
import org.lemonade.visitors.interfaces.GuiExpressionVisitor;

/**
 *
 */
public class EvaluateVisitor implements GuiExpressionVisitor<GuiExpression>, UpdateVisitor {

    private Map<String, GuiValue<?>> guiEnvironment;

    public EvaluateVisitor() {
        this.guiEnvironment = new HashMap<>();
    }

    @Override
    public void visit(final GuiForm form) {
        for (GuiBody body : form.getBodies()) {
            body.accept(this);
        }
    }

    @Override
    public void visit(final GuiQuestion question) {
        guiEnvironment.put(question.getIdentifier().getValue(), question.getElement().getValue());
    }

    @Override
    public void visit(final GuiConditional conditional) {
        GuiValue<?> condition = (GuiValue<?>) conditional.getCondition().accept(this);
        if (condition.isDefined()) {
            for (GuiBody body : conditional.getBodies()) {
                body.accept(this);
            }
        }
    }

    @Override
    public GuiExpression visit(final GuiAndBinary guiAndBinary) {
        GuiBooleanValue left = (GuiBooleanValue) guiAndBinary.getLeft();
        GuiBooleanValue right = (GuiBooleanValue) guiAndBinary.getRight();
        return left.and(right);
    }

    @Override
    public GuiExpression visit(final GuiDivideBinary guiDivideBinary) {
        GuiNumericalValue<?> left = (GuiNumericalValue<?>) guiDivideBinary.getLeft();
        GuiNumericalValue<?> right = (GuiNumericalValue<?>) guiDivideBinary.getRight();
        return left.divide(right);
    }

    @Override
    public GuiExpression visit(final GuiEqBinary guiEqBinary) {
        GuiValue<?> left = (GuiValue<?>) guiEqBinary.getLeft();
        GuiValue<?> right = (GuiValue<?>) guiEqBinary.getRight();
        return new GuiBooleanValue(left.equals(right));//TODO Wrap this
    }

    @Override
    public GuiExpression visit(final GuiGTBinary guiGtBinary) {
        GuiComparableValue<?> left = (GuiComparableValue<?>) guiGtBinary.getLeft();
        GuiComparableValue<?> right = (GuiComparableValue<?>) guiGtBinary.getRight();
        return left.gT(right);
    }

    @Override
    public GuiExpression visit(final GuiGTEBinary guiGteBinary) {
        GuiComparableValue<?> left = (GuiComparableValue<?>) guiGteBinary.getLeft();
        GuiComparableValue<?> right = (GuiComparableValue<?>) guiGteBinary.getRight();
        return left.gTEq(right);
    }

    @Override
    public GuiExpression visit(final GuiLTBinary guiLtBinary) {
        GuiComparableValue<?> left = (GuiComparableValue<?>) guiLtBinary.getLeft();
        GuiComparableValue<?> right = (GuiComparableValue<?>) guiLtBinary.getRight();
        return left.lT(right);
    }

    @Override
    public GuiExpression visit(final GuiLTEBinary guiLteBinary) {
        GuiComparableValue<?> left = (GuiComparableValue<?>) guiLteBinary.getLeft();
        GuiComparableValue<?> right = (GuiComparableValue<?>) guiLteBinary.getRight();
        return left.lTEq(right);
    }

    @Override
    public GuiExpression visit(final GuiMinusBinary guiMinusBinary) {
        GuiNumericalValue<?> left = (GuiNumericalValue<?>) guiMinusBinary.getLeft();
        GuiNumericalValue<?> right = (GuiNumericalValue<?>) guiMinusBinary.getRight();
        return left.minus(right);
    }

    @Override
    public GuiExpression visit(final GuiNEqBinary guiNEqBinary) {
        GuiValue<?> left = (GuiValue<?>) guiNEqBinary.getLeft();
        GuiValue<?> right = (GuiValue<?>) guiNEqBinary.getRight();
        return new GuiBooleanValue(left.equals(right)); //TODO Wrap this!
    }

    @Override
    public GuiExpression visit(final GuiOrBinary guiOrBinary) {
        GuiBooleanValue left = (GuiBooleanValue) guiOrBinary.getLeft();
        GuiBooleanValue right = (GuiBooleanValue) guiOrBinary.getRight();
        return left.or(right);
    }

    @Override
    public GuiExpression visit(final GuiPlusBinary guiPlusBinary) {
        GuiNumericalValue<?> left = (GuiNumericalValue<?>) guiPlusBinary.getLeft();
        GuiNumericalValue<?> right = (GuiNumericalValue<?>) guiPlusBinary.getRight();
        return left.plus(right);
    }

    @Override
    public GuiExpression visit(final GuiProductBinary guiProductBinary) {
        GuiNumericalValue<?> left = (GuiNumericalValue<?>) guiProductBinary.getLeft();
        GuiNumericalValue<?> right = (GuiNumericalValue<?>) guiProductBinary.getRight();
        return null;
    }

    @Override
    public GuiExpression visit(final GuiBangUnary guiBangUnary) {
        GuiBooleanValue expression = (GuiBooleanValue) guiBangUnary.getExpression();
        return expression.bang();
    }

    @Override
    public GuiExpression visit(final GuiNegUnary guiNegUnary) {
        GuiValue<?> expression = (GuiValue<?>) guiNegUnary.getExpression().accept(this);
        if (!expression.isDefined()) {
            return expression;
        }
        return ((GuiNumericalValue<?>) expression).neg();
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
        //TODO add in additional checks if variable is in the environment.
        GuiValue<?> value = guiEnvironment.get(guiIdentifierValue);
        return value;
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
