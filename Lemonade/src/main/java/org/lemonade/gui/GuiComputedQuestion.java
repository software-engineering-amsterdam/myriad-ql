package org.lemonade.gui;

import org.lemonade.gui.elements.GuiLabelElement;
import org.lemonade.gui.values.GuiIdentifierValue;
import org.lemonade.visitors.EvaluateVisitor;

/**
 *
 */
public class GuiComputedQuestion extends GuiBody {

    private GuiIdentifierValue identifier;
    private GuiLabelElement labelElement;
    private GuiExpression expression;

    @Override
    public void accept(EvaluateVisitor visitor) {

    }

    @Override
    public void isVisible(boolean flag) {

    }
}
