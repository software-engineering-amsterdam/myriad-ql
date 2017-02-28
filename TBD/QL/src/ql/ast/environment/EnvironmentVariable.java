package ql.ast.environment;

import ql.ast.Expr;
import ql.ast.types.Type;
import ql.ast.values.UndefinedValue;
import ql.ast.values.Value;

/**
 * Created by Erik on 27-2-2017.
 */
public class EnvironmentVariable {
    private final Type type;
    private Value value;
    private Expr expr = null;

    protected EnvironmentVariable(Type type) {
        this.type = type;
        this.value = new UndefinedValue();
    }

    protected EnvironmentVariable(Type type, Expr expr) {
        this(type);
        this.expr = expr;
    }


    protected Type getType() {
        return type;
    }

    protected void setValue(Value value) {
        this.value = value;
    }

    protected Value getValue() {
        return value;
    }

    protected boolean hasExpr() {
        return expr != null;
    }

    protected Expr getExpr() {
        return expr;
    }
}
