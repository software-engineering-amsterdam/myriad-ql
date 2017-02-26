package GUI.GUIComponents.GUIFields;

import ASTnodes.statements.SimpleQuestion;
import GUI.GUIInterface;
import GUI.GUIAbstractComponent;
import GUI.GUIComponents.FormFrame;
import GUI.widgets.Widget;
import semanticChecker.formDataStorage.valueData.values.Value;

/**
 * Created by LGGX on 23-Feb-17.
 */
public abstract class Field extends GUIAbstractComponent {

    protected Widget widget;
    protected final SimpleQuestion question;

    Field(GUIInterface updates, SimpleQuestion question, Widget widget) {
        super(updates);
        this.question = question;
        this.widget = widget;
    }

    public abstract void setState(Value value);
    public abstract Value getState();

    public abstract void resetState();

    public void addToForm(FormFrame form) {
        this.widget.render(form);
    }

    public void removeFromForm(FormFrame form) {
        this.widget.suppress(form);
        this.resetState();
    }

    @Override
    public String getId() {
        return question.getIdentifier().getName();
    }

    public Widget getWidget() {
        return this.widget;
    }
}
