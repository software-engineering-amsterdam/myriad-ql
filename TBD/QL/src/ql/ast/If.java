package ql.ast;

/**
 * Created by Erik on 6-2-2017.
 */
public class If implements ASTNode, Statement {
    private Expr expression;
    private Statements ifBlock;
    private Statements elseBlock;

    public If(Expr expression, Statements ifBlock, Statements elseBlock) {
        this.expression = expression;
        this.ifBlock = ifBlock;
        this.elseBlock = elseBlock;
    }

    public If(Expr expression, Statements ifBlock) {
        this(expression, ifBlock, null);
    }
}
