package ql.ast;

/**
 * Created by Erik on 6-2-2017.
 */
public class Form implements ASTNode {
    private Statements statements;

    public Form(Statements statements){
        this.statements = statements;
    }
}
