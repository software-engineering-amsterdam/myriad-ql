/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/gui/formenvironment/QuestionConditions.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.gui.formenvironment;

import ql.astnodes.Form;
import ql.astnodes.statements.ComputedQuestion;
import ql.astnodes.statements.IfStatement;
import ql.astnodes.statements.SimpleQuestion;
import ql.astnodes.statements.Statement;
import ql.visitorinterfaces.FormAndStatementVisitor;
import ql.gui.components.fields.Field;
import ql.gui.components.fields.FieldFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionConditions implements FormAndStatementVisitor <Void>{

    private final Map<Field, List<IfStatement>> fieldToIfStatementsMap;
    private final Context context;
    private final FieldFactory fieldFactory;

    private List<SimpleQuestion> questionsInIfStatement = new ArrayList<>();

    public QuestionConditions(QuestionData questionData, Context context, FieldFactory fieldFactory) {
        this.context = context;
        this.fieldFactory = fieldFactory;
        fieldToIfStatementsMap = getFieldsWithIfStatements(questionData);
    }

    private Map<Field, List<IfStatement>> getFieldsWithIfStatements(QuestionData questionData) {
        Map<Field, List<IfStatement>> fieldMap = new HashMap<>();

        for (SimpleQuestion question : questionData.getAllQuestions()) {
            Field field = question.accept(fieldFactory);
            context.addValue(field.getId(), field.getValue());
            fieldMap.put(field, new ArrayList<>());
            addIfStatements(questionData, question, fieldMap);
        }
        return fieldMap;
    }

    private void addIfStatements(QuestionData questionData, SimpleQuestion question,
            Map<Field, List<IfStatement>> fieldMap) {
        for (IfStatement ifStatement : questionData.getIfStatements()) {
            questionsInIfStatement.clear();
            ifStatement.accept(this);

            if (questionsInIfStatement.contains(question)) {
                for (Field field : fieldMap.keySet()) {

                    if (question.getIdentifier().getName().equals(field.getId())) {
                        fieldMap.get(field).add(ifStatement);
                    }
                }
            }
        }
    }

    public Map<Field, List<IfStatement>> getFieldToIfStatementsMap() {
        return fieldToIfStatementsMap;
    }

    @Override
    public Void visit(Form form) {
        return null;
    }

    @Override
    public Void visit(SimpleQuestion simpleQuestion) {
        questionsInIfStatement.add(simpleQuestion);
        return null;
    }

    @Override
    public Void visit(ComputedQuestion computedQuestion) {
        questionsInIfStatement.add(computedQuestion);
        return null;
    }

    @Override
    public Void visit(IfStatement ifStatement) {
        for (Statement bodyStatement : ifStatement.getStatements()) {
            bodyStatement.accept(this);
        }
        return null;
    }
}
