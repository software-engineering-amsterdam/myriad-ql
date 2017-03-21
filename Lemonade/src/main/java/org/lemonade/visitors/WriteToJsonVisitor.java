package org.lemonade.visitors;

import com.cedarsoftware.util.io.JsonWriter;

import org.json.simple.JSONObject;
import org.lemonade.gui.GuiConditional;
import org.lemonade.gui.GuiForm;
import org.lemonade.gui.GuiQuestion;
import org.lemonade.visitors.interfaces.GuiBaseElementsVisitor;

public class WriteToJsonVisitor implements GuiBaseElementsVisitor {

    private JSONObject rootJSON;
    private JSONObject formContents;

    public WriteToJsonVisitor() {
        rootJSON = new JSONObject();
        formContents = new JSONObject();
    }

    @Override
    public void visit(final GuiForm form) {
        form.getBodies().forEach(body -> body.accept(this));
        rootJSON.put(form.getIdentifier(), formContents);
    }

    @Override
    public void visit(final GuiQuestion question) {
        formContents.put(question.getIdentifier(), question.getElement());
    }

    @Override
    public void visit(final GuiConditional conditional) {

    }

    public String getJSONString() {
        return JsonWriter.formatJson(rootJSON.toString());
    }

}
