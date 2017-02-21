package ql.ast;

import ql.ast.visistor.ASTVisitor;

/**
 * Created by Erik on 6-2-2017.
 */
public class If extends Statement {
    private final Expr condition;
    private final Statements ifBlock;

    public If(Expr condition, Statements ifBlock, int rowNumber) {
        super(rowNumber);
        this.condition = condition;
        this.ifBlock = ifBlock;
    }

    public Expr getCondition() {
        return condition;
    }

    public Statements getIfBlock() {
        return ifBlock;
    }


    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
