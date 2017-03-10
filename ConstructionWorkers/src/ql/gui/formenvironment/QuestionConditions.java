package ql.gui.formenvironment;

import ql.astnodes.Form;
import ql.astnodes.statements.ComputedQuestion;
import ql.astnodes.statements.IfStatement;
import ql.astnodes.statements.SimpleQuestion;
import ql.astnodes.statements.Statement;
import ql.astnodes.visitors.FormAndStatementVisitor;
import ql.gui.components.fields.Field;
import ql.gui.components.fields.FieldFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LGGX on 25-Feb-17.
 */
public class QuestionConditions implements FormAndStatementVisitor <Void>{

    private final Map<Field, List<IfStatement>> conditionQuestionMap;
    private final Context context;
    private final FieldFactory fieldFactory;

    private List<SimpleQuestion> questionsInIfStatement = new ArrayList<>();

    public QuestionConditions(QuestionData questionData, Context context, FieldFactory fieldFactory) {
        this.context = context;
        this.fieldFactory = fieldFactory;
        this.conditionQuestionMap = getQuestionsWithConditions(questionData, new HashMap<>());
    }

    public Map<Field, List<IfStatement>> getMap() {
        return this.conditionQuestionMap;
    }

    private Map<Field, List<IfStatement>> getQuestionsWithConditions(QuestionData questionData,
    Map<Field, List<IfStatement>> fieldMap) {

        for (SimpleQuestion question : questionData.getAllQuestions()) {
            Field field = question.accept(this.fieldFactory);
            this.context.addValue(field.getId(), field.getState());
            fieldMap.put(field, new ArrayList<>());
            this.addIfStatementsToQuestion(questionData.getIfStatements(), question, fieldMap);
        }

        return fieldMap;
    }

    private void addIfStatementsToQuestion(List<IfStatement> ifStatementsList, SimpleQuestion question,
            Map<Field, List<IfStatement>> conditionsOfQuestions) {

        for (IfStatement ifStatement : ifStatementsList) {

            this.questionsInIfStatement.clear();
            ifStatement.accept(this);

            if (this.questionsInIfStatement.contains(question)) {
                for (Field field : conditionsOfQuestions.keySet()) {
                    if (question.getIdentifier().getName().equals(field.getId())) {
                        conditionsOfQuestions.get(field).add(ifStatement);
                    }
                }
            }
        }
    }

    @Override
    public Void visit(Form form) {
        return null;
    }

    @Override
    public Void visit(SimpleQuestion statement) {
        this.questionsInIfStatement.add(statement);
        return null;
    }

    @Override
    public Void visit(ComputedQuestion statement) {
        this.questionsInIfStatement.add(statement);
        return null;
    }

    @Override
    public Void visit(IfStatement statement) {
        for (Statement subStatement : statement.getStatements()) {
            subStatement.accept(this);
        }
        return null;
    }
}
