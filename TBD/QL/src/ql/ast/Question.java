package ql.ast;

import ql.ast.types.Type;
import ql.ast.literals.QLIdent;
import ql.ast.literals.QLString;
import ql.ast.visistor.ASTVisitor;

/**
 * Created by Erik on 6-2-2017.
 */
public class Question extends Statement {
    private final String id;
    private final String question;
    private final Type type;

    public Question(QLIdent id, QLString question, Type type) {
        super(id.getRowNumber());
        this.id = id.getValue();
        this.question = question.getValue();
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public Type getType() {
        return type;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
