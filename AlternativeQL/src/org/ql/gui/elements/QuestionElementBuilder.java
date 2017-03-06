package org.ql.gui.elements;

import org.ql.ast.statement.IfThen;
import org.ql.ast.statement.IfThenElse;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.StatementVisitor;
import org.ql.evaluator.ValueTable;
import org.ql.evaluator.value.BooleanValue;
import org.ql.evaluator.value.Value;
import org.ql.gui.GUIEval;
import org.ql.gui.widgets.Widget;
import org.ql.gui.widgets.WidgetBuilder;

public class QuestionElementBuilder implements StatementVisitor<QuestionElement, Void> {

    private GUIEval guiEval;
    private WidgetBuilder widgetBuilder;

    public QuestionElementBuilder(ValueTable valueTable) {
        this.guiEval = new GUIEval(valueTable);
        widgetBuilder = new WidgetBuilder();
    }

    @Override
    public QuestionElement visitIfThen(IfThen ifThen, Void context) {
        return null;
    }

    @Override
    public QuestionElement visitIfThenElse(IfThenElse ifThenElse, Void context) {
        return null;
    }

    @Override
    public QuestionElement visitQuestion(Question question, Void context) {
        Value value = guiEval.evaluateExpression(question);
        Widget widget = widgetBuilder.getWidget(question);

        // TODO: Visitor
        QuestionElement questionElement = null;
        if(question.getType().isBoolean()) {
            questionElement = new BooleanQuestionElement(question, widget);
            questionElement.setValue(value);
        }

        return questionElement;
    }
}
