package QL.GUI.GUIComponents.GUIVisitors;

import QL.ASTnodes.statements.SimpleQuestion;
import QL.ASTnodes.types.*;
import QL.ASTnodes.visitors.TypeVisitor;
import QL.GUI.GUIInterface;
import QL.GUI.GUIComponents.GUIFields.*;
import QL.GUI.GUIComponents.GUIWidgets.Widget;

/**
 * Created by LGGX on 24-Feb-17.
 */
public class FieldTypeVisitor implements TypeVisitor<Field>{

    private final GUIInterface updates;
    private final SimpleQuestion question;
    private final Widget widget;

    public FieldTypeVisitor(GUIInterface updates, SimpleQuestion question, Widget widget) {
        this.updates = updates;
        this.question = question;
        this.widget = widget;
    }

    public Field visit(BooleanType type) {
        return new BooleanField(this.updates, this.question, this.widget);
    }

    public Field visit(IntegerType type) {
        return new IntegerField(this.updates, this.question, this.widget);
    }

    public Field visit(MoneyType type) {
        return new MoneyField(this.updates, this.question, this.widget);
    }

    public Field visit(StringType type) {
        return new StringField(this.updates, this.question, this.widget);
    }

    public Field visit(UndefinedType type) {
        throw new RuntimeException();
    }

}
