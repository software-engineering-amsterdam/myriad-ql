package ql.ast;

import ql.ast.visistor.ASTVisitor;

/**
 * Created by Erik on 20-2-2017.
 */
public class IfElse extends Statement {
    private final Expr condition;
    private final Statements ifBlock;
    private final Statements elseBlock;

    public IfElse(Expr condition, Statements ifBlock, Statements elseBlock, int rowNumber) {
        super(rowNumber);
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


    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
