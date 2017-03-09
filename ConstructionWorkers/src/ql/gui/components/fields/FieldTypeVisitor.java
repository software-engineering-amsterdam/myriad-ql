/**
 * FieldTypeVisitor.java.
 */

package ql.gui.components.fields;

import ql.astnodes.statements.SimpleQuestion;
import ql.astnodes.types.*;
import ql.astnodes.visitors.TypeVisitor;
import ql.gui.GUIInterface;
import ql.gui.components.widgets.QLWidget;

public class FieldTypeVisitor implements TypeVisitor<Field>{

    private final GUIInterface guiInterface;
    private final SimpleQuestion question;
    private final QLWidget widget;

    public FieldTypeVisitor(GUIInterface guiInterface, SimpleQuestion question, QLWidget widget) {
        this.guiInterface = guiInterface;
        this.question = question;
        this.widget = widget;
    }

    public Field visit(BooleanType type) {
        return new BooleanField(guiInterface, question, widget);
    }

    public Field visit(IntegerType type) {
        return new IntegerField(guiInterface, question, widget);
    }

    public Field visit(MoneyType type) {
        return new MoneyField(guiInterface, question, widget);
    }

    public Field visit(StringType type) {
        return new StringField(guiInterface, question, widget);
    }
}
