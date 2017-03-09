/**
 * FieldFactory.java.
 */

package ql.gui.components.fields;

import ql.astnodes.Form;
import ql.astnodes.statements.ComputedQuestion;
import ql.astnodes.statements.IfStatement;
import ql.astnodes.statements.SimpleQuestion;
import ql.astnodes.visitors.FormAndStatementVisitor;
import ql.gui.components.widgets.QLWidget;
import ql.gui.evaluation.Evaluator;
import ql.gui.GUIInterface;
import ql.gui.components.widgets.WidgetFactory;
import ql.gui.formenvironment.Context;
import ql.gui.formenvironment.values.Value;

public class FieldFactory implements FormAndStatementVisitor<Field>{

    private final GUIInterface guiInterface;
    private final Evaluator evaluator;
    private final WidgetFactory widgetFactory;

    public FieldFactory(GUIInterface guiInterface, Context context) {
        this.guiInterface = guiInterface;
        evaluator = new Evaluator(context);
        widgetFactory = new WidgetFactory();
    }

    @Override
    public Field visit(Form form) {
        return null;
    }

    @Override
    public Field visit(SimpleQuestion question) {
        QLWidget widget = widgetFactory.getWidgetForQuestion(question);
        FieldTypeVisitor typeVisitor = new FieldTypeVisitor(guiInterface, question, widget);
        return question.getType().accept(typeVisitor);
    }

    @Override
    public Field visit(ComputedQuestion question) {
        Value result = evaluator.getValueComputedQuestion(question);
        QLWidget widget = this.widgetFactory.getWidgetForQuestion(question);

        widget.setValue(result);
        widget.setReadOnly(true);

        FieldTypeVisitor typeVisitor = new FieldTypeVisitor(guiInterface, question, widget);
        return question.getType().accept(typeVisitor);
    }

    @Override
    public Field visit(IfStatement statement) {
        return null;
    }

}
