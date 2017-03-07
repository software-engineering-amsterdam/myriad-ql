/**
 * FieldTypeVisitor.java.
 */

package ql.gui.components.fields;

import ql.astnodes.statements.SimpleQuestion;
import ql.astnodes.types.*;
import ql.astnodes.visitors.TypeVisitor;
import ql.gui.GUIInterface;
import ql.gui.components.widgets.WidgetInterface;

public class FieldTypeVisitor implements TypeVisitor<Field>{

    private final GUIInterface updates;
    private final SimpleQuestion question;
    private final WidgetInterface widget;

    public FieldTypeVisitor(GUIInterface updates, SimpleQuestion question, WidgetInterface widget) {
        this.updates = updates;
        this.question = question;
        this.widget = widget;
    }

    public Field visit(BooleanType type) {
        return new BooleanField(updates, question, widget);
    }

    public Field visit(IntegerType type) {
        return new IntegerField(updates, question, widget);
    }

    public Field visit(MoneyType type) {
        return new MoneyField(updates, question, widget);
    }

    public Field visit(StringType type) {
        return new StringField(updates, question, widget);
    }
}
