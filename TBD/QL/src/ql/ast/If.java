package ql.ast;

import ql.ast.visistor.ASTVisitor;

/**
 * Created by Erik on 6-2-2017.
 */
public class If implements Statement {
    private final Expr expression;
    private final Statements ifBlock;
    private final Statements elseBlock;

    public If(Expr expression, Statements ifBlock, Statements elseBlock) {
        this.expression = expression;
        this.ifBlock = ifBlock;
        this.elseBlock = elseBlock;
    }

    public If(Expr expression, Statements ifBlock) {
        this(expression, ifBlock, null);
    }

    public Boolean hasElseBlock() {
        return elseBlock != null;
    }

    public Expr getExpression() {
        return expression;
    }

    public Statements getIfBlock() {
        return ifBlock;
    }

    public Statements getElseBlock() {
        return elseBlock;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
