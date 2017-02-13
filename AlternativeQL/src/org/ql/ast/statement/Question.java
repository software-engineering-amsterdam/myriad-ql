package org.ql.ast.statement;

import org.ql.ast.Identifier;
import org.ql.ast.Statement;
import org.ql.ast.literal.StringLiteral;
import org.ql.ast.type.Type;

public class Question implements Statement {
    private final Identifier id;
    private final StringLiteral question;
    private final Type type;

    public Question(Identifier id, StringLiteral question, Type type) {
        this.id = id;
        this.question = question;
        this.type = type;
    }

    public Identifier getId() {
        return id;
    }

    public StringLiteral getQuestion() {
        return question;
    }

    public Type getType() {
        return type;
    }
}
