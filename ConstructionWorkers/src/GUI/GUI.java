package GUI;

import ASTnodes.Form;
import ASTnodes.statements.ComputedQuestion;
import ASTnodes.statements.IfStatement;
import GUI.GUIEvaluation.QuestionEvaluator;
import GUI.GUIComponents.GUIFields.ComputerQuestionField;
import GUI.GUIComponents.GUIFields.Field;
import GUI.GUIComponents.GUIManager;
import GUI.GUIComponents.GUIVisitors.GUIFieldFactory;
import GUI.widgets.WidgetFactory;
import semanticChecker.formDataStorage.QuestionData;
import semanticChecker.formDataStorage.valueData.ValueData;
import semanticChecker.formDataStorage.valueData.values.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by LGGX on 22-Feb-17.
 */
public class GUI implements GUIInterface{

    private final Map<Field, List<IfStatement>> questionsWithConditions;

    private final ValueData valueData;
    private final QuestionEvaluator evaluator;
    private final GUIFieldFactory fieldFactory;

    protected final GUIManager manager;

    private List<ComputedQuestion> computedQuestions = new ArrayList<>();

    public GUI(Form form, WidgetFactory widgetFactory, GUIManager manager, ValueData valueData) {
        this.valueData = valueData;
        this.evaluator = new QuestionEvaluator(valueData);

        this.manager = manager;
        this.fieldFactory = new GUIFieldFactory(this, valueData, widgetFactory);

        QuestionData formDataStorage = new QuestionData(form);

        this.questionsWithConditions = new OptionalQuestions(formDataStorage, valueData, this.fieldFactory).getMap();

        this.computedQuestions = formDataStorage.getComputedQuestions();
    }


    @Override
    public void getGUIChanges(GUIAbstractComponent mediator) {
        this.saveValue(mediator.getId(), mediator.getState());
        this.evaluateComputedQuestion(this.computedQuestions);
        this.showUI();
    }

    public void showUI() {
        this.updateGUIData(this.questionsWithConditions);
        this.manager.render();
    }

    protected void updateGUIData(Map<Field, List<IfStatement>> conditionsOfQuestions) {
        for (Field field : conditionsOfQuestions.keySet()) {
            if (this.isConditionTrue(conditionsOfQuestions, field)) {
                this.manager.addQuestion(field);
            } else {
                this.manager.removeQuestion(field);
            }
        }
    }

    // Helper functions etc...

    private void saveValue(String id, Value value) {
        this.valueData.addValue(id, value);
    }

    private void evaluateComputedQuestion(List<ComputedQuestion> questions) {
        for (ComputedQuestion question : questions) {
            Value result = this.evaluator.expressionEvaluation(question);
            ComputerQuestionField cqField = (ComputerQuestionField) this.getFieldByID(
                    question.getIdentifier().getName(),
                    this.questionsWithConditions);
            cqField.setComputedValue(result);
        }
    }

    private Field getFieldByID( String idName,  Map<Field, List<IfStatement>> conditionsOfQuestions)
    {
        for (Field field : conditionsOfQuestions.keySet()) {
            if (idName.equals(field.getId())) {
                return field;
            }
        }
        return null;
    }

    protected boolean isConditionTrue(
            Map<Field, List<IfStatement>> conditionsOfQuestions, Field field)
    {
        boolean condition = true;
        for (IfStatement ifStatement : conditionsOfQuestions.get(field)) {
            if (!this.evaluator.IfStatementEvaluation(ifStatement)) {
                condition = false;
            }
        }
        return condition;
    }
}
