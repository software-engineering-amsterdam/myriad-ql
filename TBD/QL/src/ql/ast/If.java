package ql.ast;

import ql.visistor.interfaces.BaseVisitor;

/**
 * Created by Erik on 6-2-2017.
 */
public class If extends Statement {
    private final Expr condition;
    private final Statements ifBlock;

    public If(Expr condition, Statements ifBlock) {
        super(condition.getRowNumber());
        this.condition = condition;
        this.ifBlock = ifBlock;
    }

    public Expr getCondition() {
        return condition;
    }

    public Statements getIfBlock() {
        return ifBlock;
    }


    public <T> T accept(BaseVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
