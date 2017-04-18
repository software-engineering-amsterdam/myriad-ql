/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/gui/GUI.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.gui;

import ql.astnodes.Form;
import ql.astnodes.statements.ComputedQuestion;
import ql.astnodes.statements.IfStatement;
import ql.astnodes.statements.SimpleQuestion;
import ql.gui.components.FormFrame;
import ql.gui.evaluation.Evaluator;
import ql.gui.components.fields.Field;
import ql.gui.components.fields.FieldFactory;
import ql.gui.formenvironment.Context;
import ql.gui.formenvironment.QuestionConditions;
import ql.gui.formenvironment.QuestionData;
import ql.gui.formenvironment.values.Value;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GUI implements GUIInterface{

    private FormFrame form;

    private final Context context;
    private final Evaluator evaluator;
    private final QuestionData questionData;
    private final List<ComputedQuestion> computedQuestions;
    private final Map<Field, List<IfStatement>> fieldToIfStatementsMap;

    public GUI(Form form) {
        this.form = new FormFrame(form.getIdentifierName());

        this.context = new Context();
        evaluator = new Evaluator(context);

        FieldFactory fieldFactory = new FieldFactory(this, context);

        questionData = new QuestionData(form);
        computedQuestions = questionData.getComputedQuestions();
        fieldToIfStatementsMap = new QuestionConditions(questionData, context, fieldFactory).getFieldToIfStatementsMap();
    }

    @Override
    public void getGUIChanges(Field updateField) {
        context.addValue(updateField.getIdentifierName(), updateField.getValue());
        showGUI();
    }

    public void showGUI() {
        evaluateComputedQuestions();
        updateGUIData();
        form.showForm();
    }

    private void evaluateComputedQuestions() {
        for (ComputedQuestion computedQuestion : computedQuestions) {
            Value result = evaluator.getValueComputedQuestion(computedQuestion);
            Field questionField = null;

            for (Field field : fieldToIfStatementsMap.keySet()) {
                if (computedQuestion.getIdentifierName().equals(field.getIdentifierName())) {
                    questionField = field;
                }
            }

            if (questionField != null) {
                questionField.getWidget().setValue(result);
            }
        }
    }

    private void updateGUIData() {
        for (SimpleQuestion question : questionData.getAllQuestions()) {

            for (Field field : fieldToIfStatementsMap.keySet()) {
                if (Objects.equals(question.getIdentifierName(), field.getIdentifierName())) {

                    if (checkIfConditionIsTrue(field)) {
                        form.addToFields(field);
                        field.getWidget().render(form);
                    } else {
                        form.removeFromFields(field);
                        field.getWidget().remove(form);
                        field.resetValue();
                    }
                }
            }
        }
    }

    private boolean checkIfConditionIsTrue(Field field) {
        boolean condition = true;

        for (IfStatement ifStatement : fieldToIfStatementsMap.get(field)) {
            if (!(Boolean) evaluator.getValueIfStatement(ifStatement).getValue()) {
                condition = false;
            }
        }
        return condition;
    }
}
