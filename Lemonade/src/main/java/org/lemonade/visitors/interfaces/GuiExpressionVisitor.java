package org.lemonade.visitors.interfaces;

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

public interface GuiExpressionVisitor<T> {
    T visit(GuiAndBinary guiAndBinary);

    T visit(GuiDivideBinary guiDivideBinary);

    T visit(GuiEqBinary guiEqBinary);

    T visit(GuiGTBinary guiGtBinary);

    T visit(GuiGTEBinary guiGteBinary);

    T visit(GuiLTBinary guiLtBinary);

    T visit(GuiLTEBinary guiLteBinary);

    T visit(GuiMinusBinary guiMinusBinary);

    T visit(GuiNEqBinary guiNEqBinary);

    T visit(GuiOrBinary guiOrBinary);

    T visit(GuiPlusBinary guiPlusBinary);

    T visit(GuiProductBinary guiProductBinary);

    T visit(GuiBangUnary guiBangUnary);

    T visit(GuiNegUnary guiNegUnary);

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
