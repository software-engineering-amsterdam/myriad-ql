package ql.ast;

import ql.visistor.interfaces.BaseVisitor;

/**
 * Created by Erik on 20-2-2017.
 */
public class IfElse extends Statement {
    private final Expr condition;
    private final Statements ifBlock;
    private final Statements elseBlock;

    public IfElse(Expr condition, Statements ifBlock, Statements elseBlock) {
        super(condition.getRowNumber());
        this.condition = condition;
        this.ifBlock = ifBlock;
        this.elseBlock = elseBlock;
    }

    public Expr getCondition() {
        return condition;
    }

    public Statements getIfBlock() {
        return ifBlock;
    }

    public Statements getElseBlock() {
        return elseBlock;
    }


    public <T> T accept(BaseVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
