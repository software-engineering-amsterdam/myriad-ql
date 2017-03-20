package org.ql.ast.statement;

import org.ql.ast.identifier.Identifier;
import org.ql.ast.statement.question.QuestionLabel;
import org.ql.ast.type.Type;

public class Question extends Statement {
    private final Identifier id;
    private final QuestionLabel questionLabel;
    private final Type type;

    public Question(Identifier id, QuestionLabel questionLabel, Type type) {
        this.id = id;
        this.questionLabel = questionLabel;
        this.type = type;
    }

    public Identifier getId() {
        return id;
    }

    public QuestionLabel getLabel() {
        return questionLabel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        return id != null ? id.equals(question.id) : question.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public Type getType() {
        return type;
    }

    @Override
    public <T, C> T accept(StatementVisitor<T, C> visitor, C context) {
        return visitor.visitQuestion(this, context);
    }
}
