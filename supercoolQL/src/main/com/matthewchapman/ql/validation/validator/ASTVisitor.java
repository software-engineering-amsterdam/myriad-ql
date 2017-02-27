package com.matthewchapman.ql.validation.validator;

import com.matthewchapman.ql.ast.QLStatement;
import com.matthewchapman.ql.ast.statement.IfStatement;
import com.matthewchapman.ql.ast.statement.Question;

/**
 * Created by matt on 27/02/2017.
 */
public class ASTVisitor implements Visitor<Void> {


    @Override
    public Void visit(Question question) {
        System.out.println(question.getName());
        return null;
    }

    @Override
    public Void visit(IfStatement ifStatement) {

        for (QLStatement statement:ifStatement.getStatements()) {
            statement.accept(this);
        }

        return null;
    }
}
