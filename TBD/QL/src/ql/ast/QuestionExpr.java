package ql.ast;

import ql.ast.types.Type;
import ql.ast.literals.QLIdent;
import ql.ast.literals.QLString;
import ql.ast.visistor.ASTVisitor;

/**
 * Created by Erik on 6-2-2017.
 */
public class QuestionExpr extends Statement {
    private final String id;
    private final String question;
    private final Type type;
    private final Expr expr;

    public QuestionExpr(QLIdent id, QLString question, Type type, Expr expr) {
        super(id.getRowNumber());
        this.id = id.getValue();
        this.question = question.getValue();
        this.type = type;
        this.expr = expr;
    }

    public String getId() {
        return id;
    }

    public String getQuestion() {
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
