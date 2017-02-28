package com.matthewchapman.ql.validator;

import com.matthewchapman.ql.ast.Statement;
import com.matthewchapman.ql.ast.expression.Parameter;
import com.matthewchapman.ql.ast.expression.ParameterGroup;
import com.matthewchapman.ql.ast.statement.IfElseStatement;
import com.matthewchapman.ql.ast.statement.IfStatement;
import com.matthewchapman.ql.ast.statement.Question;

/**
 * Created by matt on 27/02/2017.
 */
public class QLTreeVisitor implements QLVisitor<Void> {

    // to store all questions for testing
    private final QuestionStore questionStore = new QuestionStore();

    public QuestionStore getQuestionStore() {return this.questionStore;}

    @Override
    public Void visit(Question question) {
        questionStore.add(question);

        if(question.isCalculated()){
        }

        return null;
    }

    @Override
    public Void visit(IfStatement ifStatement) {

        for (Statement statement:ifStatement.getStatements()) {
            statement.accept(this);
        }

        return null;
    }

    @Override
    public Void visit(IfElseStatement ifElseStatement) {

        for (Statement statement:ifElseStatement.getIfCaseStatements()) {
            statement.accept(this);
        }

        for (Statement statement:ifElseStatement.getElseCaseStatements()) {
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
}
