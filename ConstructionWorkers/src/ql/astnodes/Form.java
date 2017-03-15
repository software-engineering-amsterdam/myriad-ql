/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/astnodes/Form.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.astnodes;

import ql.astnodes.expressions.literals.Identifier;
import ql.astnodes.statements.Statement;
import ql.visitorinterfaces.FormAndStatementVisitor;

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

    public <T> void accept(FormAndStatementVisitor<T> visitor) {
        visitor.visit(this);
    }
}
