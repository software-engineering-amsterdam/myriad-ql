package org.ql.typechecker;

import org.ql.ast.Expression;
import org.ql.ast.Form;
import org.ql.ast.Identifier;
import org.ql.ast.Statement;
import org.ql.ast.statement.IfThen;
import org.ql.ast.statement.IfThenElse;
import org.ql.ast.statement.Question;
import org.ql.collection.Questions;
import org.ql.typechecker.messages.MessageBag;

import java.util.List;

public interface ITypeChecker {
    MessageBag checkForm(Form form);

    MessageBag checkQuestion(Question question);

    MessageBag checkIfThenElse(IfThenElse ifThenElse);

    MessageBag checkIfThen(IfThen ifThen);

    MessageBag checkQuestionText(Question question);

    MessageBag checkDefaultValue(Question question);

    MessageBag checkStatements(List<Statement> statements);

    MessageBag checkQuestionDuplicates(Questions questions);

    MessageBag checkQuestionLabelsDuplicates(Questions questions);

    MessageBag checkIfCondition(Expression condition);

    MessageBag checkIdentifier(Identifier identifier);
}
