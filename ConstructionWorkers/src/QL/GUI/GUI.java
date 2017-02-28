package ql.gui;

import ql.astnodes.Form;
import ql.astnodes.statements.ComputedQuestion;
import ql.astnodes.statements.IfStatement;
import ql.astnodes.statements.SimpleQuestion;
import ql.gui.evaluation.QuestionEvaluator;
import ql.gui.components.fields.ComputerQuestionField;
import ql.gui.components.fields.Field;
import ql.gui.components.GUIManager;
import ql.gui.components.visitors.GUIFieldFactory;
import ql.gui.components.widgets.WidgetFactory;
import ql.gui.formenvironment.OptionalQuestions;
import ql.gui.formenvironment.QuestionData;
import ql.gui.formenvironment.ValueData;
import ql.gui.formenvironment.values.Value;

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
    private final QuestionData questionData;
    private final GUIManager manager;

    private List<ComputedQuestion> computedQuestions = new ArrayList<>();

    public GUI(Form form, WidgetFactory widgetFactory, GUIManager manager, ValueData valueData) {
        this.valueData = valueData;
        this.evaluator = new QuestionEvaluator(valueData);

        this.manager = manager;
        this.fieldFactory = new GUIFieldFactory(this, valueData, widgetFactory);

        this.questionData = new QuestionData(form);

        this.questionsWithConditions = new OptionalQuestions(this.questionData, valueData, this.fieldFactory).getMap();

        this.computedQuestions = this.questionData.getComputedQuestions();
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

    private void updateGUIData(Map<Field, List<IfStatement>> conditionsOfQuestions) {
        for (SimpleQuestion question : questionData.getAllQuestions()) {
            for (Field field : conditionsOfQuestions.keySet()) {
                if (question.getIdentifier().getName() == field.getId()) {
                    if (this.isConditionTrue(conditionsOfQuestions, field)) {
                        this.manager.addQuestion(field);
                    } else {
                        this.manager.removeQuestion(field);
                    }
                }
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

    private boolean isConditionTrue(
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
