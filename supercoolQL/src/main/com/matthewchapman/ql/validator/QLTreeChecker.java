package com.matthewchapman.ql.validator;

import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.ast.Statement;
import com.matthewchapman.ql.ast.expression.Parameter;
import com.matthewchapman.ql.ast.expression.ParameterGroup;
import com.matthewchapman.ql.ast.expression.binary.*;
import com.matthewchapman.ql.ast.expression.unary.Negation;
import com.matthewchapman.ql.ast.statement.IfElseStatement;
import com.matthewchapman.ql.ast.statement.IfStatement;
import com.matthewchapman.ql.ast.statement.Question;

/**
 * Created by matt on 27/02/2017.
 */
public class QLTreeChecker implements QLVisitor<Void> {

    // to store all questions for testing
    private final QuestionStore questionStore = new QuestionStore();

    public QuestionStore getQuestionStore() {
        return this.questionStore;
    }

    public void doCheck(Form form) {
        for (Statement statement : form.getStatements()) {
            statement.accept(this);
        }

        questionStore.testForDuplicateIDs();
    }

    @Override
    public Void visit(Question question) {
        questionStore.add(question);

        return null;
    }

    @Override
    public Void visit(IfStatement ifStatement) {

        for (Statement statement : ifStatement.getStatements()) {
            statement.accept(this);
        }

        return null;
    }

    @Override
    public Void visit(IfElseStatement ifElseStatement) {

        for (Statement statement : ifElseStatement.getIfCaseStatements()) {
            statement.accept(this);
        }

        for (Statement statement : ifElseStatement.getElseCaseStatements()) {
            statement.accept(this);
        }

        return null;
    }

    @Override
    public Void visit(Parameter parameter) {
        return null;
    }

    @Override
    public Void visit(ParameterGroup parameterGroup) {
        return null;
    }

    @Override
    public Void visit(Addition addition) {
        return null;
    }

    @Override
    public Void visit(Division division) {
        return null;
    }

    @Override
    public Void visit(Equal equal) {
        return null;
    }

    @Override
    public Void visit(GreaterThan greaterThan) {
        return null;
    }

    @Override
    public Void visit(GreaterThanEqualTo greaterThanEqualTo) {
        return null;
    }

    @Override
    public Void visit(LessThan lessThan) {
        return null;
    }

    @Override
    public Void visit(LessThanEqualTo lessThanEqualTo) {
        return null;
    }

    @Override
    public Void visit(LogicalAnd logicalAnd) {
        return null;
    }

    @Override
    public Void visit(LogicalOr logicalOr) {
        return null;
    }

    @Override
    public Void visit(Multiplication multiplication) {
        return null;
    }

    @Override
    public Void visit(NotEqual notEqual) {
        return null;
    }

    @Override
    public Void visit(Subtraction subtraction) {
        return null;
    }

    @Override
    public Void visit(Negation negation) {
        return null;
    }
}
