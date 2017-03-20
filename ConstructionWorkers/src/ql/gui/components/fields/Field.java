/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/gui/components/fields/Field.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.gui.components.fields;

import ql.astnodes.statements.SimpleQuestion;
import ql.gui.GUIInterface;
import ql.gui.components.widgets.QLWidget;
import ql.gui.formenvironment.values.Value;

public abstract class Field  {

    private final GUIInterface guiInterface;

    protected final SimpleQuestion question;

    protected QLWidget widget;

    Field(GUIInterface guiInterface, SimpleQuestion question, QLWidget widget) {
        this.guiInterface = guiInterface;
        this.question = question;
        this.widget = widget;
    }

    void getNewChanges() {
        guiInterface.getGUIChanges(this);
    }

    public String getIdentifierName() {
        return question.getIdentifier().getName();
    }

    public QLWidget getWidget() {
        return widget;
    }

    public abstract Value getValue();

    public abstract void setValue(Value value);

    public abstract void resetValue();
}
