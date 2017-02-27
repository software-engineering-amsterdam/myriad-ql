package QL.GUI.GUIComponents.GUIVisitors;

import QL.ASTnodes.Form;
import QL.ASTnodes.statements.ComputedQuestion;
import QL.ASTnodes.statements.IfStatement;
import QL.ASTnodes.statements.SimpleQuestion;
import QL.ASTnodes.statements.Statement;
import QL.ASTnodes.visitors.FormAndStatementVisitor;
import QL.GUI.GUIEvaluation.QuestionEvaluator;
import QL.GUI.GUIInterface;
import QL.GUI.GUIComponents.GUIFields.ComputerQuestionField;
import QL.GUI.GUIComponents.GUIFields.Field;
import QL.GUI.GUIComponents.GUIWidgets.Widget;
import QL.GUI.GUIComponents.GUIWidgets.WidgetFactory;
import QL.semanticChecker.formDataStorage.valueData.ValueData;
import QL.semanticChecker.formDataStorage.valueData.values.Value;

/**
 * Created by LGGX on 24-Feb-17.
 */
public class GUIFieldFactory implements FormAndStatementVisitor<Field>{

    private final GUIInterface updates;
    private final QuestionEvaluator evaluator;
    private final WidgetFactory widgetFactory;

    public GUIFieldFactory(
            GUIInterface updates, ValueData valueData, WidgetFactory widgetFactory)
    {
        this.updates = updates;
        this.evaluator = new QuestionEvaluator(valueData);
        this.widgetFactory = widgetFactory;
    }

    @Override
    public Field visit(Form form) {
        for (Statement statement : form.getStatements()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public Field visit(SimpleQuestion statement) {
        Widget widget = this.widgetFactory.getWidgetForQuestion(statement);
        FieldTypeVisitor typeVisitor =
                new FieldTypeVisitor(this.updates, statement, widget);
        return  statement.getType().accept(typeVisitor);
    }

    @Override
    public Field visit(ComputedQuestion statement) {
        Value result = evaluator.expressionEvaluation(statement);
        Widget widget = this.widgetFactory.getWidgetForQuestion(statement, result);
        return new ComputerQuestionField(updates, statement, widget, result);
    }

    @Override
    public Field visit(IfStatement statement) {
        throw new AssertionError();
    }
}
