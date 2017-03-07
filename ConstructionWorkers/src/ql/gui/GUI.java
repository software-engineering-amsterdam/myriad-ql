/**
 * GUI.java.
 */

package ql.gui;

import ql.astnodes.Form;
import ql.astnodes.statements.ComputedQuestion;
import ql.astnodes.statements.IfStatement;
import ql.astnodes.statements.SimpleQuestion;
import ql.gui.evaluation.Evaluator;
import ql.gui.components.fields.ComputerQuestionField;
import ql.gui.components.fields.Field;
import ql.gui.components.GUIManager;
import ql.gui.components.fields.FieldFactory;
import ql.gui.components.widgets.WidgetFactory;
import ql.gui.formenvironment.Context;
import ql.gui.formenvironment.OptionalQuestions;
import ql.gui.formenvironment.QuestionData;
import ql.gui.formenvironment.values.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GUI implements GUIInterface{

    private final GUIManager manager;
    private final Context context;
    private final Evaluator evaluator;
//    private final FieldFactory fieldFactory;
    private final QuestionData questionData;

    private List<ComputedQuestion> computedQuestions = new ArrayList<>();
    private final Map<Field, List<IfStatement>> questionsWithConditions;

    public GUI(Form form, Context context) {
        manager = new GUIManager(form.getIdentifier().getName());
        this.context = context;
        evaluator = new Evaluator(context);

        FieldFactory fieldFactory = new FieldFactory(this, context);

        questionData = new QuestionData(form);
        computedQuestions = questionData.getComputedQuestions();
        questionsWithConditions = new OptionalQuestions(questionData, context, fieldFactory).getMap();
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
                        this.manager.addField(field);
                    } else {
                        this.manager.removeField(field);
                    }
                }
            }
        }

    }

    // Helper functions etc...

    private void saveValue(String id, Value value) {
        this.context.addValue(id, value);
    }

    private void evaluateComputedQuestion(List<ComputedQuestion> questions) {
        for (ComputedQuestion question : questions) {
            Value result = this.evaluator.getValueComputedQuestion(question);
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
            if (!(Boolean) this.evaluator.getValueIfStatement(ifStatement).getValue()) {
                condition = false;
            }
        }
        return condition;
    }
}
