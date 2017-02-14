package ql.ast;

import ql.ast.literals.QLIdent;
import ql.ast.visistor.ASTVisitor;

/**
 * Created by Erik on 6-2-2017.
 */
public class Form implements ASTNode {
    private QLIdent name;
    private Statements statements;

    public Form(QLIdent name, Statements statements){
        this.name = name;
        this.statements = statements;
    }

    public QLIdent getName(){
        return name;
    }

    public Statements getStatements() {
        return statements;
    }

    public <T> T visitThis(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
