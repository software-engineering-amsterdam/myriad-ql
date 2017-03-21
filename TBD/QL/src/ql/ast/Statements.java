package ql.ast;

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
}
