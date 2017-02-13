package org.ql.ast.declaration;

import org.ql.ast.Identifier;
import org.ql.ast.declaration.Declaration;
import org.ql.ast.literal.StringLiteral;
import org.ql.ast.type.Type;

public class Question extends Declaration {
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
