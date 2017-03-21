package org.lemonade.visitors;

import org.json.simple.JSONObject;
import org.lemonade.gui.GuiComputedQuestion;
import org.lemonade.gui.GuiConditional;
import org.lemonade.gui.GuiForm;
import org.lemonade.gui.GuiQuestion;
import org.lemonade.visitors.interfaces.GuiBaseElementsVisitor;

public class WriteToJsonVisitor implements GuiBaseElementsVisitor {

    private JSONObject formContents;

    public WriteToJsonVisitor() {
        formContents = new JSONObject();
    }

    @Override
    public void visit(final GuiForm form) {
        JSONObject json = new JSONObject();
        form.getBodies().forEach(body -> body.accept(this));
        json.put(form.getIdentifier(), formContents);
    }


    @Override
    public void visit(GuiComputedQuestion question) {

    }

    @Override
    public void visit(final GuiQuestion question) {
        formContents.put(question.getIdentifier(), question.getElement());
    }

    @Override
    public void visit(final GuiConditional conditional) {

    }
}
