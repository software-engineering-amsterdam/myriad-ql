/**
 * Form.java.
 */

package ASTnodes;

import ASTnodes.expressions.literals.Identifier;
import ASTnodes.statements.Statement;
import ASTnodes.visitors.FormAndStatementVisitor;

import java.util.List;

public class Form extends Node {

    private final Identifier identifier;
    private final List<Statement> statements;

    public Form(Identifier identifier, List<Statement> statements, LineNumber location) {
        super(location);
        this.identifier = identifier;
        this.statements = statements;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    public <T> T accept(FormAndStatementVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
