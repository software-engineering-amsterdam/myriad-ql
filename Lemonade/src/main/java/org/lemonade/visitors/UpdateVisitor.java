package org.lemonade.visitors;

import org.lemonade.gui.GuiBody;
import org.lemonade.gui.GuiConditional;
import org.lemonade.gui.GuiForm;
import org.lemonade.gui.GuiQuestion;

public interface UpdateVisitor {

    void visit(GuiForm form);

    void visit(GuiQuestion question);

    void visit(GuiConditional conditional);

}
