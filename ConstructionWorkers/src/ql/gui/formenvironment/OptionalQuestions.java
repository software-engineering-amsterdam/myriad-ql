package ql.gui.formenvironment;

import ql.astnodes.statements.IfStatement;
import ql.astnodes.statements.SimpleQuestion;
import ql.gui.components.fields.Field;
import ql.gui.components.visitors.GUIFieldFactory;
import ql.gui.formenvironment.values.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LGGX on 25-Feb-17.
 */
public class OptionalQuestions {

    private final Map<Field, List<IfStatement>> conditionQuestionMap;
    private final Context context;
    private final GUIFieldFactory fieldFactory;

    public OptionalQuestions(QuestionData questionData, Context context, GUIFieldFactory fieldFactory) {

        this.context = context;
        this.fieldFactory = fieldFactory;
        this.conditionQuestionMap = getQuestionsWithConditions(questionData);
    }

    public Map<Field, List<IfStatement>> getMap() {
        return this.conditionQuestionMap;
    }

    public HashMap<Field, List<IfStatement>> getQuestionsWithConditions(QuestionData questionData) {
        HashMap<Field, List<IfStatement>> conditionsOfQuestions = new HashMap<>();

        for (SimpleQuestion question : questionData.getAllQuestions()) {

            Field field = this.generateField(question);
            this.saveValue(field.getId(), field.getState());
            conditionsOfQuestions.put(field, new ArrayList<>());

            this.addIfStatementsToQuestion(
                    questionData.getIfStatements(), question, conditionsOfQuestions);
        }
        return conditionsOfQuestions;
    }

    private Field generateField(SimpleQuestion question) {
        return question.accept(this.fieldFactory);
    }

    private void saveValue(String id, Value value) {
        this.context.addValue(id, value);
    }

    private void addIfStatementsToQuestion(
            List<IfStatement> ifStatementsList,
            SimpleQuestion question,
            HashMap<Field, List<IfStatement>> conditionsOfQuestions)
    {
        for (IfStatement ifStatement : ifStatementsList) {
            if (ifStatement.getStatements().contains(question)) {
                Field field = this.getField(question.getIdentifier().getName(), conditionsOfQuestions);
                conditionsOfQuestions.get(field).add(ifStatement);
            }
        }
    }

    private Field getField(String idName, HashMap<Field, List<IfStatement>> conditionsOfQuestions) {
        for (Field field : conditionsOfQuestions.keySet()) {
            if (idName.equals(field.getId())) {
                return field;
            }
        }
        return null;
    }
}
