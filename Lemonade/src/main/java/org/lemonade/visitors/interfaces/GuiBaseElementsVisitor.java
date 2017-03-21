package org.lemonade.visitors.interfaces;

import org.lemonade.gui.GuiConditional;
import org.lemonade.gui.GuiForm;
import org.lemonade.gui.GuiQuestion;

public interface GuiBaseElementsVisitor {

    void visit(GuiForm form);

    void visit(GuiQuestion question);

    void visit(GuiConditional conditional);

}
