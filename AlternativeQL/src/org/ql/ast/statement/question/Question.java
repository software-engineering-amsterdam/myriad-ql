package org.ql.ast.statement.question;

import org.ql.ast.Identifier;
import org.ql.ast.statement.Statement;
import org.ql.ast.statement.question.Text;
import org.ql.ast.type.Type;

public class Question implements Statement {
    private final Identifier id;
    private final Text questionText;
    private final Type type;

    public Question(Identifier id, Text questionText, Type type) {
        this.id = id;
        this.questionText = questionText;
        this.type = type;
    }

    public Identifier getId() {
        return id;
    }

    public Text getQuestionText() {
        return questionText;
    }

    public Type getType() {
        return type;
    }
}
