package ql.ast;

import ql.ast.literals.QLIdent;
import ql.ast.literals.QLString;
import ql.ast.visistor.ASTVisitor;

/**
 * Created by Erik on 6-2-2017.
 */
public class Question implements Statement {
    private QLIdent id;
    private QLString question;
    private Type type;
    private Expr expr;

    public Question(QLIdent id, QLString question, Type type, Expr expr) {
        this.id = id;
        this.question = question;
        this.type = type;
        this.expr = expr;
        System.out.println(question);
    }

    public Question(QLIdent id, QLString question, Type type) {
        this(id, question, type, null);
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

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
