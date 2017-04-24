package org.ql.evaluator;

import org.ql.ast.form.Form;
import org.ql.ast.identifier.IdentifierSet;
import org.ql.ast.statement.Statement;
import org.ql.ast.form.FormVisitor;
import org.ql.ast.statement.*;
import org.ql.evaluator.value.UnknownValue;
import org.ql.evaluator.value.Value;

public class QuestionEvaluator implements FormVisitor<Void, ValueTable>, StatementVisitor<Void, ValueTable> {
    private final ExpressionEvaluator expressionEvaluator;
    private final IdentifierSet modifiedQuestions;

    public QuestionEvaluator(IdentifierSet modifiedQuestions) {
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
        if (!modifiedQuestions.isDeclared(question.getId())) {
            valueTable.declare(question.getId(), new UnknownValue());
        }

        return null;
    }

    @Override
    public Void visitComputableQuestion(ComputableQuestion question, ValueTable valueTable) {
        if (!modifiedQuestions.isDeclared(question.getId())) {
            Value value = expressionEvaluator.evaluate(question.getComputableValue(), valueTable);
            valueTable.declare(question.getId(), value);
        }

        return null;
    }
}
