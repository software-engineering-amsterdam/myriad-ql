package org.lemonade.visitors;

import java.util.LinkedHashMap;
import java.util.Map;

import com.cedarsoftware.util.io.JsonWriter;
import com.owlike.genson.Genson;

import org.lemonade.gui.GuiComputedQuestion;
import org.lemonade.gui.GuiConditional;
import org.lemonade.gui.GuiForm;
import org.lemonade.gui.GuiQuestion;
import org.lemonade.visitors.interfaces.GuiBaseElementsVisitor;

public class JsonVisitor implements GuiBaseElementsVisitor {

    private Map<String, Object> formResults;

    public JsonVisitor() {
        formResults = new LinkedHashMap<>();
    }

    @Override
    public void visit(final GuiForm form) {
        formResults.put("formName", form.getIdentifier().toString());
        form.getBodies().forEach(body -> body.accept(this));
    }

    @Override
    public void visit(GuiComputedQuestion question) {
        formResults.put(question.getIdentifier().toString(), question.getElement().toString());
    }

    @Override
    public void visit(final GuiQuestion question) {
        formResults.put(question.getIdentifier().toString(), question.getElement().toString());
    }

    @Override
    public void visit(final GuiConditional conditional) {
        conditional.getBodies().forEach(body -> body.accept(this));
    }

    public String getJSONString() {
        Genson genson = new Genson();
        return JsonWriter.formatJson(genson.serialize(formResults));
    }

}
