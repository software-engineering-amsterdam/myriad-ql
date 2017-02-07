package ql.ast;

import ql.ast.expression.Expr;
import ql.ast.type.QLString;

/**
 * Created by Erik on 6-2-2017.
 */
public class Question implements ASTNode, Statement {
    private QLString id;
    private QLString question;
    private Type type;
    private Expr expr;

    public Question(QLString id, QLString question, Type type, Expr expr) {
        this.id = id;
        this.question = question;
        this.type = type;
        this.expr = expr;
    }

    public Question(QLString id, QLString question, Type type) {
        this(id, question, type, null);
    }
}
