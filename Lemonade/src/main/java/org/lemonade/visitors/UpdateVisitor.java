package org.lemonade.visitors;

import org.lemonade.gui.elements.GuiBody;
import org.lemonade.gui.elements.GuiConditional;
import org.lemonade.gui.elements.GuiForm;
import org.lemonade.gui.elements.GuiQuestion;

public interface UpdateVisitor {

    GuiForm visit(GuiForm form);

    GuiBody visit(GuiBody body);

    GuiQuestion visit(GuiQuestion question);

    GuiConditional visit(GuiConditional conditional);

}
