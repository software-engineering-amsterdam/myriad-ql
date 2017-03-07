package org.ql.ast.statement;

import org.ql.ast.Expression;
import org.ql.ast.Identifier;
import org.ql.ast.Statement;
import org.ql.ast.statement.question.QuestionLabel;
import org.ql.ast.type.Type;

public class Question extends Statement {
    private final Identifier id;
    private final QuestionLabel questionLabel;
    private final Type type;
    private final Expression defaultValue;

    public Question(Identifier id, QuestionLabel questionLabel, Type type, Expression defaultValue) {
        this.id = id;
        this.questionLabel = questionLabel;
        this.type = type;
        this.defaultValue = defaultValue;
    }

    public Identifier getId() {
        return id;
    }

    public QuestionLabel getQuestionLabel() {
        return questionLabel;
    }

    public Type getType() {
        return type;
    }

    public Expression getValue() {
        return defaultValue;
    }

    @Override
    public <T, C> T accept(StatementVisitor<T, C> visitor, C context) {
        return visitor.visitQuestion(this, context);
    }
}
