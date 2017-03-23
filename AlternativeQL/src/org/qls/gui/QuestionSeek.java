package org.qls.gui;

import org.ql.ast.form.Form;
import org.ql.ast.form.FormVisitor;
import org.ql.ast.identifier.Identifier;
import org.ql.ast.statement.*;

import java.util.List;

public class QuestionSeek implements FormVisitor<Question, Identifier>, StatementVisitor<Question, Identifier> {

    public Question find(Identifier id, Form form) {
        return visitForm(form, id);
    }

    @Override
    public Question visitForm(Form form, Identifier searchedId) {
        return seekInStatements(searchedId, form.getStatements());
    }

    @Override
    public Question visitIfThen(IfThen ifThen, Identifier searchedId) {
        return seekInStatements(searchedId, ifThen.getThenStatements());
    }

    @Override
    public Question visitIfThenElse(IfThenElse ifThenElse, Identifier searchedId) {
        Question question = seekInStatements(searchedId, ifThenElse.getThenStatements());

        if (question == null) {
            question = seekInStatements(searchedId, ifThenElse.getElseStatements());
        }

        return question;
    }

    @Override
    public Question visitQuestion(Question question, Identifier searchedId) {
        return question.getId().equals(searchedId) ? question : null;
    }

    @Override
    public Question visitComputableQuestion(ComputableQuestion question, Identifier searchedId) {
        return visitQuestion(question, searchedId);
    }

    private Question seekInStatements(Identifier searchedId, List<Statement> statements) {
        for (Statement statement : statements) {
            Question question = statement.accept(this, searchedId);

            if (question != null) {
                return question;
            }
        }
        return null;
    }
}
