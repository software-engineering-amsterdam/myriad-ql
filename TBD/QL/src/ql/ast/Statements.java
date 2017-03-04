package ql.ast;

import ql.ast.visistor.ASTVisitor;


/**
 * Created by Erik on 7-2-2017.
 */
public class Statements extends Array<Statement> {

    public Statements(Statement current, Array<Statement> next, int rowNumber) {
        super(current, next, rowNumber);
    }

    public Statements(Statement current, int rowNumber) {
        super(current, rowNumber);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
