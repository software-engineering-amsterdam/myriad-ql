package ql.ast;


import ql.visistor.interfaces.BaseVisitor;

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

    public <T> T accept(BaseVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
