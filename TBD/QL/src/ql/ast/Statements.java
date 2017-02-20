package ql.ast;

import ql.ast.visistor.ASTVisitor;

/**
 * Created by Erik on 7-2-2017.
 */
public class Statements implements ASTNode {
    private final Statement current;
    private final Statements next;

    public Statements(Statement current, Statements next) {
        this.current = current;
        this.next = next;
    }

    public Statements(Statement current) {
        this(current, null);
    }

    public boolean hasCurrent() {
        return this.current != null;
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

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
