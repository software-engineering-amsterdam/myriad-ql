package com.matthewchapman.ql.validation.typechecking;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.ast.Statement;
import com.matthewchapman.ql.ast.expression.ParameterGroup;
import com.matthewchapman.ql.ast.statement.IfElseStatement;
import com.matthewchapman.ql.ast.statement.IfStatement;
import com.matthewchapman.ql.ast.statement.Question;
import com.matthewchapman.ql.validation.AbstractQLVisitor;

/**
 * Created by matt on 03/03/2017.
 */
public class StatementVisitor extends AbstractQLVisitor<Void> {

    private QuestionStore store = new QuestionStore();
    private ExpressionVisitor expressionVisitor = new ExpressionVisitor();

    @Override
    public Void visit(Question question) {
        if( question.isCalculated() )
        {
            ParameterGroup calculation = question.getCalculation();

            for(Expression expression : calculation.getParameters()) {
                expression.accept(expressionVisitor);
            }
        }

        store.add(question);

        return null;
    }

    @Override
    public Void visit(IfStatement ifStatement) {
        for(Statement statement : ifStatement.getStatements()) {
            statement.accept(this);
        }

        return null;
    }

    @Override
    public Void visit(IfElseStatement ifElseStatement) {
        for(Statement statement : ifElseStatement.getIfCaseStatements()) {
            statement.accept(this);
        }

        for(Statement statement : ifElseStatement.getElseCaseStatements()) {
            statement.accept(this);
        }

        return null;
    }


}
