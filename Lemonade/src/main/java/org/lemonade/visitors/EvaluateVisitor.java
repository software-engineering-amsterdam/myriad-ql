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
import org.lemonade.gui.values.GuiStringValue;
import org.lemonade.gui.values.GuiUndefinedValue;
import org.lemonade.gui.values.GuiValue;
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
    public void visit(final GuiBody body) {
        body.accept(this);
    }

    @Override
    public void visit(final GuiQuestion question) {
        guiEnvironment.put(question.getIdentifier().getValue(), question.getElement().getValue());
    }

    @Override
    public void visit(final GuiConditional conditional) {

    }

    @Override
    public GuiExpression visit(final GuiAndBinary guiAndBinary) {
        return null;
    }

    @Override
    public GuiExpression visit(final GuiDivideBinary guiDivideBinary) {
        return null;
    }

    @Override
    public GuiExpression visit(final GuiEqBinary guiEqBinary) {
        return null;
    }

    @Override
    public GuiExpression visit(final GuiGTBinary guiGtBinary) {
        return null;
    }

    @Override
    public GuiExpression visit(final GuiGTEBinary guiGteBinary) {
        return null;
    }

    @Override
    public GuiExpression visit(final GuiLTBinary guiLtBinary) {
        return null;
    }

    @Override
    public GuiExpression visit(final GuiLTEBinary guiLteBinary) {
        return null;
    }

    @Override
    public GuiExpression visit(final GuiMinusBinary guiMinusBinary) {
        return null;
    }

    @Override
    public GuiExpression visit(final GuiNEqBinary guiNEqBinary) {
        return null;
    }

    @Override
    public GuiExpression visit(final GuiOrBinary guiOrBinary) {
        return null;
    }

    @Override
    public GuiExpression visit(final GuiPlusBinary guiPlusBinary) {
        return null;
    }

    @Override
    public GuiExpression visit(final GuiProductBinary guiProductBinary) {
        return null;
    }

    @Override
    public GuiExpression visit(final GuiBangUnary guiBangUnary) {
        return null;
    }

    @Override
    public GuiExpression visit(final GuiNegUnary guiNegUnary) {
        return null;
    }

    @Override
    public GuiExpression visit(final GuiBooleanValue booleanValue) {
        return null;
    }

    @Override
    public GuiExpression visit(final GuiDateValue guiDateValue) {
        return null;
    }

    @Override
    public GuiExpression visit(final GuiMoneyValue guiMoneyValue) {
        return null;
    }

    @Override
    public GuiExpression visit(final GuiIntegerValue guiIntegerValue) {
        return null;
    }

    @Override
    public GuiExpression visit(final GuiDecimalValue guiDecimalValue) {
        return null;
    }

    @Override
    public GuiExpression visit(final GuiStringValue guiStringValue) {
        return null;
    }

    @Override
    public GuiExpression visit(final GuiIdentifierValue guiIdentifierValue) {
        return null;
    }

    @Override
    public GuiExpression visit(final GuiUndefinedValue guiUndefinedValue) {
        return null;
    }

    @Override
    public GuiExpression visit(final GuiLabelValue guiLabelValue) {
        return null;
    }
}
