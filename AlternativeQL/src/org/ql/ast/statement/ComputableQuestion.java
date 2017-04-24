package org.ql.ast.statement;

import org.ql.ast.expression.Expression;
import org.ql.ast.identifier.Identifier;
import org.ql.ast.statement.question.QuestionLabel;
import org.ql.ast.type.Type;

public class ComputableQuestion extends Question {
    private final Expression computableValue;

    public ComputableQuestion(Identifier id, QuestionLabel questionLabel, Type type, Expression computableValue) {
        super(id, questionLabel, type);
        this.computableValue = computableValue;
    }

    public Expression getComputableValue() {
        return computableValue;
    }

    @Override
    public <T, C> T accept(StatementVisitor<T, C> visitor, C context) {
        return visitor.visitComputableQuestion(this, context);
    }
}
