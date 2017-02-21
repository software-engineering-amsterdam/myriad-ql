package ql.ast;

import ql.ast.literals.QLIdent;
import ql.ast.literals.QLString;
import ql.ast.visistor.ASTVisitor;

/**
 * Created by Erik on 6-2-2017.
 */
public class Question extends Statement {
    private final QLIdent id;
    private final QLString question;
    private final Type type;
    private final Expr expr;

    public Question(QLIdent id, QLString question, Type type, Expr expr, int rowNumber) {
        super(rowNumber);
        this.id = id;
        this.question = question;
        this.type = type;
        this.expr = expr;
    }

    public Question(QLIdent id, QLString question, Type type, int rowNumber) {
        this(id, question, type, null, rowNumber);
    }

    public QLIdent getId() {
        return id;
    }

    public QLString getQuestion() {
        return question;
    }

    public Type getType() {
        return type;
    }

    public Expr getExpr() {
        return expr;
    }

    public boolean hasExpr() {
        return expr != null;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
