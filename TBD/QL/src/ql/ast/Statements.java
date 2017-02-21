package ql.ast;

import ql.ast.visistor.ASTVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erik on 7-2-2017.
 */
public class Statements extends ASTNode {
    private final Statement current;
    private final Statements next;

    public Statements(Statement current, Statements next, int rowNumber) {
        super(rowNumber);
        this.current = current;
        this.next = next;
    }

    public Statements(Statement current, int rowNumber) {
        this(current, null, rowNumber);
    }

    public List<Statement> getStatements(){
        List<Statement> statements = new ArrayList<>();
        if(current == null){
            return statements;
        }

        Statements currentEntry = this;
        statements.add(currentEntry.getCurrentStatement());

        while (currentEntry.hasNext()){
            currentEntry = currentEntry.next();
            statements.add(currentEntry.getCurrentStatement());
        }

        return statements;
    }

    private boolean hasNext(){
        return next != null;
    }

    private Statements next(){
        return next;
    }

    private Statement getCurrentStatement(){
        return current;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
