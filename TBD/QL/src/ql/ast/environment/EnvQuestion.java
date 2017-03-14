package ql.ast.environment;

import ql.ast.Expr;
import ql.ast.types.Type;
import ql.ast.values.UndefinedValue;
import ql.ast.values.Value;

/**
 * Created by Erik on 14-3-2017.
 */
public class EnvQuestion {
    private final Scope scope;
    private final String id;
    private final String question;
    private final Type type;
    private Value value;
    private Expr expr;

    public EnvQuestion(Scope scope, String id, String question, Type type) {
        this.scope = scope;
        this.id = id;
        this.question = question;
        this.type = type;
        this.value = new UndefinedValue();
    }

    public EnvQuestion(Scope scope, String id, String question, Type type, Expr expr) {
        this.scope = scope;
        this.id = id;
        this.question = question;
        this.type = type;
        this.expr = expr;
        this.value = new UndefinedValue();
    }

    public Scope getScope() {
        return scope;
    }

    public String getQuestion() {
        return question;
    }

    public Type getType() {
        return type;
    }

    public Value getValue() {
        return value;
    }

    public Expr getExpr() {
        return expr;
    }

    public boolean hasExpr() {
        return expr != null;
    }

    public void setValue(Value value) {
        this.value = value;
    }
}
