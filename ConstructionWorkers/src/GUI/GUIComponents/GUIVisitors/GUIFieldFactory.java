package GUI.GUIComponents.GUIVisitors;

import ASTnodes.Form;
import ASTnodes.statements.ComputedQuestion;
import ASTnodes.statements.IfStatement;
import ASTnodes.statements.SimpleQuestion;
import ASTnodes.statements.Statement;
import ASTnodes.visitors.FormAndStatementVisitor;
import GUI.GUIEvaluation.QuestionEvaluator;
import GUI.GUIInterface;
import GUI.GUIComponents.GUIFields.ComputerQuestionField;
import GUI.GUIComponents.GUIFields.Field;
import GUI.widgets.Widget;
import GUI.widgets.WidgetFactory;
import semanticChecker.formDataStorage.valueData.ValueData;
import semanticChecker.formDataStorage.valueData.values.Value;

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
