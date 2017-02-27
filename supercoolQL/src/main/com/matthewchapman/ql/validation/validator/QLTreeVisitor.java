package com.matthewchapman.ql.validation.validator;

import com.matthewchapman.ql.ast.Statement;
import com.matthewchapman.ql.ast.statement.IfStatement;
import com.matthewchapman.ql.ast.statement.Question;

/**
 * Created by matt on 27/02/2017.
 */
public class QLTreeVisitor implements QLVisitor<Void> {


    @Override
    public Void visit(Question question) {
        System.out.println(question.getName());
        return null;
    }

    @Override
    public Void visit(IfStatement ifStatement) {

        for (Statement statement:ifStatement.getStatements()) {
            statement.accept(this);
        }

        return null;
    }
}
