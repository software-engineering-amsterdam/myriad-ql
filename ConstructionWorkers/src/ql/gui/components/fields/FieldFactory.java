/**
 * FieldFactory.java.
 */

package ql.gui.components.fields;

import ql.astnodes.Form;
import ql.astnodes.statements.ComputedQuestion;
import ql.astnodes.statements.IfStatement;
import ql.astnodes.statements.SimpleQuestion;
import ql.astnodes.statements.Statement;
import ql.astnodes.visitors.FormAndStatementVisitor;
import ql.gui.components.widgets.WidgetInterface;
import ql.gui.evaluation.Evaluator;
import ql.gui.GUIInterface;
import ql.gui.components.widgets.WidgetFactory;
import ql.gui.formenvironment.Context;
import ql.gui.formenvironment.values.Value;

public class FieldFactory implements FormAndStatementVisitor<Field>{

    private final GUIInterface updates;
    private final Evaluator evaluator;
    private final WidgetFactory widgetFactory;

    public FieldFactory(GUIInterface updates, Context context) {
        this.updates = updates;
        evaluator = new Evaluator(context);
        widgetFactory = new WidgetFactory();
    }

    @Override
    public Field visit(Form form) {
        for (Statement statement : form.getStatements()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public Field visit(SimpleQuestion question) {
        WidgetInterface widget = widgetFactory.getWidgetForSimpleQuestion(question);
        FieldTypeVisitor typeVisitor = new FieldTypeVisitor(updates, question, widget);
        return question.getType().accept(typeVisitor);
    }

    @Override
    public Field visit(ComputedQuestion statement) {
        Value result = evaluator.getValueComputedQuestion(statement);
        WidgetInterface widget = this.widgetFactory.getWidgetForComputedQuestion(statement, result);
        return new ComputerQuestionField(updates, statement, widget, result);
    }

    @Override
    public Field visit(IfStatement statement) {
        throw new AssertionError();
    }
}
