package org.ql.evaluator;

import org.ql.ast.Form;
import org.ql.ast.Identifier;
import org.ql.ast.Statement;
import org.ql.ast.form.FormVisitor;
import org.ql.ast.statement.IfThen;
import org.ql.ast.statement.IfThenElse;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.StatementVisitor;
import org.ql.evaluator.value.UnknownValue;
import org.ql.evaluator.value.Value;

import java.util.Set;

public class QuestionEvaluator implements FormVisitor<Void, ValueTable>, StatementVisitor<Void, ValueTable> {
    private final ExpressionEvaluator expressionEvaluator;
    private final Set<Identifier> modifiedQuestions;

    public QuestionEvaluator(Set<Identifier> modifiedQuestions) {
        this.modifiedQuestions = modifiedQuestions;
        expressionEvaluator = new ExpressionEvaluator();
    }

    public void updateValueTable(Form form, ValueTable valueTable) {
        while (true) {
            ValueTable oldValueTable = valueTable.copy();
            this.visitForm(form, valueTable);
            if (oldValueTable.equals(valueTable)) {
                break;
            }
        }
    }

    @Override
    public Void visitForm(Form form, ValueTable valueTable) {
        for (Statement statement : form.getStatements()) {
            statement.accept(this, valueTable);
        }
        return null;
    }

    @Override
    public Void visitIfThen(IfThen ifThen, ValueTable valueTable) {
        for (Statement statement : ifThen.getThenStatements()) {
            statement.accept(this, valueTable);
        }
        return null;
    }

    @Override
    public Void visitIfThenElse(IfThenElse ifThenElse, ValueTable valueTable) {
        for (Statement statement : ifThenElse.getThenStatements()) {
            statement.accept(this, valueTable);
        }
        for (Statement statement : ifThenElse.getElseStatements()) {
            statement.accept(this, valueTable);
        }
        return null;
    }

    @Override
    public Void visitQuestion(Question question, ValueTable valueTable) {
        if (!modifiedQuestions.contains(question.getId())) {
            valueTable.declare(question.getId(), expressionEvaluator.evaluate(question.getValue(), valueTable));
        }

        return null;
    }
}
