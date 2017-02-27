/**
 * SimpleQuestion.java.
 */

package QL.ASTnodes.statements;

import QL.ASTnodes.LineNumber;
import QL.ASTnodes.expressions.literals.Identifier;
import QL.ASTnodes.visitors.FormAndStatementVisitor;

import QL.ASTnodes.types.Type;

public class SimpleQuestion extends Statement {

    private final Identifier identifier;
    private final String text;
    private final Type type;

    public SimpleQuestion(Identifier identifier, String text, Type type, LineNumber location) {
        super(location);
        this.identifier = identifier;
        this.text = text;
        this.type = type;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public String getText() {
        return text;
    }

    public Type getType() {
        return type;
    }

    @Override
    public <T> T accept(FormAndStatementVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
