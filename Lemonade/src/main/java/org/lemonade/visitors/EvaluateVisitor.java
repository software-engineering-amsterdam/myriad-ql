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
import org.lemonade.gui.values.GuiComparableValue;
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
    public ExpressionDefinedCheck definedCheckVisitor;

    public EvaluateVisitor() {
        this.guiEnvironment = new HashMap<>();
        this.definedCheckVisitor = new ExpressionDefinedCheck(guiEnvironment);
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
            for (GuiBody body : conditional.getBodies()) {
                body.accept(this);
            }
        }
    }

    @Override
    public GuiExpression visit(final GuiAndBinary guiAndBinary) {
        if (!guiAndBinary.accept(definedCheckVisitor)) {
            return new GuiUndefinedValue();
        }
        GuiBooleanValue left = (GuiBooleanValue) guiAndBinary.getLeft().accept(this);
        GuiBooleanValue right = (GuiBooleanValue) guiAndBinary.getRight().accept(this);
        return left.and(right);
    }

    @Override
    public GuiExpression visit(final GuiDivideBinary guiDivideBinary) {
        if (!guiDivideBinary.accept(definedCheckVisitor)) {
            return new GuiUndefinedValue();
        }
        GuiNumericalValue<?> left = (GuiNumericalValue<?>) guiDivideBinary.getLeft().accept(this);
        GuiNumericalValue<?> right = (GuiNumericalValue<?>) guiDivideBinary.getRight().accept(this);
        return left.divide(right);
    }

    @Override
    public GuiExpression visit(final GuiEqBinary guiEqBinary) {
        System.err.println("entering equal");
        if (!guiEqBinary.accept(definedCheckVisitor)) {
            return new GuiUndefinedValue();
        }
        GuiValue<?> left = (GuiValue<?>) guiEqBinary.getLeft().accept(this);
        GuiValue<?> right = (GuiValue<?>) guiEqBinary.getRight().accept(this);
        return new GuiBooleanValue(left.equals(right));//TODO Wrap this
    }

    @Override
    public GuiExpression visit(final GuiGTBinary guiGtBinary) {
        if (!guiGtBinary.accept(definedCheckVisitor)) {
            return new GuiUndefinedValue();
        }
        GuiComparableValue<?> left = (GuiComparableValue<?>) guiGtBinary.getLeft().accept(this);
        GuiComparableValue<?> right = (GuiComparableValue<?>) guiGtBinary.getRight().accept(this);
        return left.gT(right);
    }

    @Override
    public GuiExpression visit(final GuiGTEBinary guiGteBinary) {
        if (!guiGteBinary.accept(definedCheckVisitor)) {
            return new GuiUndefinedValue();
        }
        GuiComparableValue<?> left = (GuiComparableValue<?>) guiGteBinary.getLeft().accept(this);
        GuiComparableValue<?> right = (GuiComparableValue<?>) guiGteBinary.getRight().accept(this);
        return left.gTEq(right);
    }

    @Override
    public GuiExpression visit(final GuiLTBinary guiLtBinary) {
        if (!guiLtBinary.accept(definedCheckVisitor)) {
            return new GuiUndefinedValue();
        }
        GuiComparableValue<?> left = (GuiComparableValue<?>) guiLtBinary.getLeft().accept(this);
        GuiComparableValue<?> right = (GuiComparableValue<?>) guiLtBinary.getRight().accept(this);
        return left.lT(right);
    }

    @Override
    public GuiExpression visit(final GuiLTEBinary guiLteBinary) {
        if (!guiLteBinary.accept(definedCheckVisitor)) {
            return new GuiUndefinedValue();
        }
        GuiComparableValue<?> left = (GuiComparableValue<?>) guiLteBinary.getLeft().accept(this);
        GuiComparableValue<?> right = (GuiComparableValue<?>) guiLteBinary.getRight().accept(this);
        return left.lTEq(right);
    }

    @Override
    public GuiExpression visit(final GuiMinusBinary guiMinusBinary) {
        if (!guiMinusBinary.accept(definedCheckVisitor)) {
            return new GuiUndefinedValue();
        }
        GuiNumericalValue<?> left = (GuiNumericalValue<?>) guiMinusBinary.getLeft().accept(this);
        GuiNumericalValue<?> right = (GuiNumericalValue<?>) guiMinusBinary.getRight().accept(this);
        return left.minus(right);
    }

    @Override
    public GuiExpression visit(final GuiNEqBinary guiNEqBinary) {
        if (!guiNEqBinary.accept(definedCheckVisitor)) {
            return new GuiUndefinedValue();
        }
        GuiValue<?> left = (GuiValue<?>) guiNEqBinary.getLeft().accept(this);
        GuiValue<?> right = (GuiValue<?>) guiNEqBinary.getRight().accept(this);
        return new GuiBooleanValue(left.equals(right)); //TODO Wrap this!
    }

    @Override
    public GuiExpression visit(final GuiOrBinary guiOrBinary) {
        if (!guiOrBinary.accept(definedCheckVisitor)) {
            return new GuiUndefinedValue();
        }
        GuiBooleanValue left = (GuiBooleanValue) guiOrBinary.getLeft().accept(this);
        GuiBooleanValue right = (GuiBooleanValue) guiOrBinary.getRight().accept(this);
        return left.or(right);
    }

    @Override
    public GuiExpression visit(final GuiPlusBinary guiPlusBinary) {
        if (!guiPlusBinary.accept(definedCheckVisitor)) {
            return new GuiUndefinedValue();
        }
        GuiNumericalValue<?> left = (GuiNumericalValue<?>) guiPlusBinary.getLeft().accept(this);
        GuiNumericalValue<?> right = (GuiNumericalValue<?>) guiPlusBinary.getRight().accept(this);
        return left.plus(right);
    }

    @Override
    public GuiExpression visit(final GuiProductBinary guiProductBinary) {
        System.err.println("product");
        if (!guiProductBinary.accept(definedCheckVisitor)) {
            return new GuiUndefinedValue();
        }
        GuiNumericalValue<?> left = (GuiNumericalValue<?>) guiProductBinary.getLeft().accept(this);
        GuiNumericalValue<?> right = (GuiNumericalValue<?>) guiProductBinary.getRight().accept(this);

        return left.product(right);
    }

    @Override
    public GuiExpression visit(final GuiBangUnary guiBangUnary) {
        if (!guiBangUnary.accept(definedCheckVisitor)) {
            return new GuiUndefinedValue();
        }
        GuiBooleanValue expression = (GuiBooleanValue) guiBangUnary.getExpression().accept(this);
        return expression.bang();
    }

    @Override
    public GuiExpression visit(final GuiNegUnary guiNegUnary) {
        if (!guiNegUnary.accept(definedCheckVisitor)) {
            return new GuiUndefinedValue();
        }
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
