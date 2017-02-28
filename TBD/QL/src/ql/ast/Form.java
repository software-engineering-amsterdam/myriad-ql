package ql.ast;

import ql.ast.literals.QLIdent;
import ql.ast.visistor.ASTVisitor;

/**
 * Created by Erik on 6-2-2017.
 */
public class Form extends ASTNode {
    private final String name;
    private final Statements statements;

    public Form(QLIdent name, Statements statements) {
        super(name.getRowNumber());
        this.name = name.getValue();
        this.statements = statements;
    }

    public String getName() {
        return name;
    }

    public Statements getStatements() {
        return statements;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
