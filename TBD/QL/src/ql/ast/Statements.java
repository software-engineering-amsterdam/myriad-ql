package ql.ast;

import ql.ast.visistor.ASTVisitor;

/**
 * Created by Erik on 7-2-2017.
 */
public class Statements implements ASTNode {
    private Statement current;
    private Statements next;

    public Statements(Statement current, Statements next) {
        this.current = current;
        this.next = next;
    }

    public Statements(Statement current) {
        this(current, null);
    }

    public Statements next() {
        return this.next;
    }

    public boolean hasNext() {
        return this.next != null;
    }

    public Statement getCurrent() {
        return current;
    }

    public Statements getNext() {
        return next;
    }

    public <T> T visitThis(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
