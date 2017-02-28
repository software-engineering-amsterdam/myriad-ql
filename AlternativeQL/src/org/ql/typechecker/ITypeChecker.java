package org.ql.typechecker;

import org.ql.ast.Expression;
import org.ql.ast.Form;
import org.ql.ast.Identifier;
import org.ql.ast.Statement;
import org.ql.ast.statement.IfThen;
import org.ql.ast.statement.IfThenElse;
import org.ql.ast.statement.Question;
import org.ql.collection.Questions;

import java.util.List;

public interface ITypeChecker {
    Messages checkForm(Form form);

    Messages checkQuestion(Question question);

    Messages checkIfThenElse(IfThenElse ifThenElse);

    Messages checkIfThen(IfThen ifThen);

    Messages checkQuestionText(Question question);

    Messages checkDefaultValue(Question question);

    Messages checkStatements(List<Statement> statements);

    Messages checkQuestionDuplicates(Questions questions);

    Messages checkQuestionLabelsDuplicates(Questions questions);

    Messages checkIfCondition(Expression condition);

    Messages checkIdentifier(Identifier identifier);
}
