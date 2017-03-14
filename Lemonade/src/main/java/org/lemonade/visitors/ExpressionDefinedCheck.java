package org.lemonade.visitors;

import org.lemonade.gui.GuiExpression;
import org.lemonade.gui.expressions.binary.*;
import org.lemonade.gui.expressions.unary.GuiBangUnary;
import org.lemonade.gui.expressions.unary.GuiNegUnary;
import org.lemonade.gui.values.*;
import org.lemonade.visitors.interfaces.GuiExpressionVisitor;

import java.util.Map;

/**
 *
 */
public class ExpressionDefinedCheck implements GuiExpressionVisitor<Boolean>{

    public Map<String, GuiValue<?>> guiEnvironment;

    public ExpressionDefinedCheck(Map<String, GuiValue<?>> guiEnvironment) {
        this.guiEnvironment = guiEnvironment;
    }

    @Override
    public Boolean visit(GuiAndBinary guiAndBinary) {
        Boolean left = guiAndBinary.getLeft().accept(this);
        Boolean right = guiAndBinary.getRight().accept(this);
        return left && right;
    }

    @Override
    public Boolean visit(GuiDivideBinary guiDivideBinary) {
        Boolean left = guiDivideBinary.getLeft().accept(this);
        Boolean right = guiDivideBinary.getRight().accept(this);
        return left && right;
    }

    @Override
    public Boolean visit(GuiEqBinary guiEqBinary) {
        Boolean left = guiEqBinary.getLeft().accept(this);
        Boolean right = guiEqBinary.getRight().accept(this);
        return left && right;
    }

    @Override
    public Boolean visit(GuiGTBinary guiGtBinary) {
        Boolean left = guiGtBinary.getLeft().accept(this);
        Boolean right = guiGtBinary.getRight().accept(this);
        return left && right;
    }

    @Override
    public Boolean visit(GuiGTEBinary guiGteBinary) {
        Boolean left = guiGteBinary.getLeft().accept(this);
        Boolean right = guiGteBinary.getRight().accept(this);
        return left && right;
    }

    @Override
    public Boolean visit(GuiLTBinary guiLtBinary) {
        Boolean left = guiLtBinary.getLeft().accept(this);
        Boolean right = guiLtBinary.getRight().accept(this);
        return left && right;
    }

    @Override
    public Boolean visit(GuiLTEBinary guiLteBinary) {
        Boolean left = guiLteBinary.getLeft().accept(this);
        Boolean right = guiLteBinary.getRight().accept(this);
        return left && right;
    }

    @Override
    public Boolean visit(GuiMinusBinary guiMinusBinary) {
        Boolean left = guiMinusBinary.getLeft().accept(this);
        Boolean right = guiMinusBinary.getRight().accept(this);
        return left && right;
    }

    @Override
    public Boolean visit(GuiNEqBinary guiNEqBinary) {
        Boolean left = guiNEqBinary.getLeft().accept(this);
        Boolean right = guiNEqBinary.getRight().accept(this);
        return left && right;
    }

    @Override
    public Boolean visit(GuiOrBinary guiOrBinary) {
        Boolean left = guiOrBinary.getLeft().accept(this);
        Boolean right = guiOrBinary.getRight().accept(this);
        return left && right;
    }

    @Override
    public Boolean visit(GuiPlusBinary guiPlusBinary) {
        Boolean left = guiPlusBinary.getLeft().accept(this);
        Boolean right = guiPlusBinary.getRight().accept(this);
        return left && right;
    }

    @Override
    public Boolean visit(GuiProductBinary guiProductBinary) {
        Boolean left = guiProductBinary.getLeft().accept(this);
        Boolean right = guiProductBinary.getRight().accept(this);
        return left && right;
    }

    @Override
    public Boolean visit(GuiBangUnary guiBangUnary) {
        return guiBangUnary.getExpression().accept(this);
    }

    @Override
    public Boolean visit(GuiNegUnary guiNegUnary) {
        return guiNegUnary.getExpression().accept(this);
    }

    @Override
    public Boolean visit(GuiBooleanValue booleanValue) {
        return true;
    }

    @Override
    public Boolean visit(GuiDateValue guiDateValue) {
        return true;
    }

    @Override
    public Boolean visit(GuiMoneyValue guiMoneyValue) {
        return true;
    }

    @Override
    public Boolean visit(GuiIntegerValue guiIntegerValue) {
        return true;
    }

    @Override
    public Boolean visit(GuiDecimalValue guiDecimalValue) {
        return true;
    }

    @Override
    public Boolean visit(GuiStringValue guiStringValue) {
        return true;
    }

    @Override
    public Boolean visit(GuiIdentifierValue guiIdentifierValue) {
        GuiValue<?> value = guiEnvironment.get(guiIdentifierValue.getValue());
        return value.accept(this);
    }

    @Override
    public Boolean visit(GuiUndefinedValue guiUndefinedValue) {
        return false;
    }

    @Override
    public Boolean visit(GuiLabelValue guiLabelValue) {
        return true;
    }
}
