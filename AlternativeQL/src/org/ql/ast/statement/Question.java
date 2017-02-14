package org.ql.ast.statement;

import org.ql.ast.Identifier;
import org.ql.ast.Statement;
import org.ql.ast.statement.question.QuestionText;
import org.ql.ast.type.Type;

public class Question implements Statement {
    private final Identifier id;
    private final QuestionText questionText;
    private final Type type;

    public Question(Identifier id, QuestionText questionText, Type type) {
        this.id = id;
        this.questionText = questionText;
        this.type = type;
    }

    public Identifier getId() {
        return id;
    }

    public QuestionText getQuestionText() {
        return questionText;
    }

    public Type getType() {
        return type;
    }
}
