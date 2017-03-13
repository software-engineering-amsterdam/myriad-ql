package org.lemonade.visitors.interfaces;

import org.lemonade.gui.expressions.binary.*;
import org.lemonade.gui.expressions.unary.BangUnary;
import org.lemonade.gui.expressions.unary.NegUnary;
import org.lemonade.gui.values.*;

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
    T visit(GuiStringValue guiStringValue);
    T visit(GuiIdentifierValue guiIdentifierValue);
    T visit(GuiUndefinedValue guiUndefinedValue);
}
