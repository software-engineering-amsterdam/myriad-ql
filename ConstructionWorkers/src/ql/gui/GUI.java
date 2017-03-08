/**
 * GUI.java.
 */

package ql.gui;

import ql.astnodes.Form;
import ql.astnodes.statements.ComputedQuestion;
import ql.astnodes.statements.IfStatement;
import ql.astnodes.statements.SimpleQuestion;
import ql.gui.evaluation.Evaluator;
import ql.gui.components.fields.ComputedQuestionField;
import ql.gui.components.fields.Field;
import ql.gui.components.fields.FieldFactory;
import ql.gui.formenvironment.Context;
import ql.gui.formenvironment.QuestionConditions;
import ql.gui.formenvironment.QuestionData;
import ql.gui.formenvironment.values.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
        questionsWithConditions = new QuestionConditions(questionData, context, fieldFactory).getMap();
    }

    @Override
    public void getGUIChanges(Field updateField) {
        this.context.addValue(updateField.getId(), updateField.getState());
        this.showUI();
    }

    public void showUI() {
        this.evaluateComputedQuestion(this.computedQuestions);
        this.updateGUIData(this.questionsWithConditions);
        this.manager.render();
    }

    private void updateGUIData(Map<Field, List<IfStatement>> conditionsOfQuestions) {
        for (SimpleQuestion question : questionData.getAllQuestions()) {
            for (Field field : conditionsOfQuestions.keySet()) {
                if (Objects.equals(question.getIdentifier().getName(), field.getId())) {
                    if (this.checkIfConditionIsTrue(conditionsOfQuestions, field)) {
                        this.manager.addField(field);
                    } else {
                        this.manager.removeField(field);
                    }
                }
            }
        }
    }

    private void evaluateComputedQuestion(List<ComputedQuestion> questions) {
        for (ComputedQuestion question : questions) {
            Value result = this.evaluator.getValueComputedQuestion(question);

            ComputedQuestionField cqField = null;
            for (Field field : this.questionsWithConditions.keySet()) {
                if (question.getIdentifier().getName().equals(field.getId())) {
                    cqField = (ComputedQuestionField) field;
                }
            }

            if (cqField != null) {
                cqField.setComputedValue(result);
            }
        }
    }

    private boolean checkIfConditionIsTrue(Map<Field, List<IfStatement>> conditionsOfQuestions, Field field) {
        boolean condition = true;

        for (IfStatement ifStatement : conditionsOfQuestions.get(field)) {
            if (!(Boolean) this.evaluator.getValueIfStatement(ifStatement).getValue()) {
                condition = false;
            }
        }

        return condition;
    }
}
