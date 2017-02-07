package ql.ast;

/**
 * Created by Erik on 6-2-2017.
 */
public class Question implements ASTNode, Statement {
    private String id;
    private String question;
    private Type type;
    private Expr expr;

    public Question(String id, String question, Type type, Expr expr) {
        this.id = id;
        this.question = question;
        this.type = type;
        this.expr = expr;
    }

    public Question(String id, String question, Type type) {
        this(id, question, type, null);
    }
}
