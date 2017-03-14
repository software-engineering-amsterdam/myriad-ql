package org.lemonade.visitors.interfaces;

import org.lemonade.gui.expressions.binary.AndBinary;
import org.lemonade.gui.expressions.binary.DivideBinary;
import org.lemonade.gui.expressions.binary.EqBinary;
import org.lemonade.gui.expressions.binary.GTBinary;
import org.lemonade.gui.expressions.binary.GTEBinary;
import org.lemonade.gui.expressions.binary.LTBinary;
import org.lemonade.gui.expressions.binary.MinusBinary;
import org.lemonade.gui.expressions.binary.NEqBinary;
import org.lemonade.gui.expressions.binary.OrBinary;
import org.lemonade.gui.expressions.binary.PlusBinary;
import org.lemonade.gui.expressions.binary.ProductBinary;
import org.lemonade.gui.expressions.unary.BangUnary;
import org.lemonade.gui.expressions.unary.NegUnary;
import org.lemonade.gui.values.GuiBooleanValue;
import org.lemonade.gui.values.GuiDateValue;
import org.lemonade.gui.values.GuiDecimalValue;
import org.lemonade.gui.values.GuiIdentifierValue;
import org.lemonade.gui.values.GuiIntegerValue;
import org.lemonade.gui.values.GuiLabelValue;
import org.lemonade.gui.values.GuiMoneyValue;
import org.lemonade.gui.values.GuiStringValue;
import org.lemonade.gui.values.GuiUndefinedValue;

/**
 *
 */
public interface GuiExpressionVisitor<T> {
    T visit(AndBinary andBinary);

    T visit(DivideBinary divideBinary);

    T visit(EqBinary eqBinary);

    T visit(GTBinary gtBinary);

    T visit(GTEBinary gteBinary);

    T visit(LTBinary ltBinary);

    T visit(MinusBinary minusBinary);

    T visit(NEqBinary nEqBinary);

    T visit(OrBinary orBinary);

    T visit(PlusBinary plusBinary);

    T visit(ProductBinary productBinary);

    T visit(BangUnary bangUnary);

    T visit(NegUnary negUnary);

    T visit(GuiBooleanValue booleanValue);

    T visit(GuiDateValue guiDateValue);

    T visit(GuiMoneyValue guiMoneyValue);

    T visit(GuiIntegerValue guiIntegerValue);

    T visit(GuiDecimalValue guiDecimalValue);

    T visit(GuiStringValue guiStringValue);

    T visit(GuiIdentifierValue guiIdentifierValue);

    T visit(GuiUndefinedValue guiUndefinedValue);

    T visit(GuiLabelValue guiLabelValue);

}
