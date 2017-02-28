/**
 * Form.java.
 */

package ql.astnodes;

import ql.astnodes.expressions.literals.Identifier;
import ql.astnodes.statements.Statement;
import ql.astnodes.visitors.FormAndStatementVisitor;

import java.util.List;

public class Form extends Node {

    private final Identifier identifier;
    private final List<Statement> statements;

    public Form(Identifier identifier, List<Statement> statements, LineNumber lineNumber) {
        super(lineNumber);
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
