package ql.ast.visistor.environment;

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

    public EnvironmentVariable(Type type) {
        this.type = type;
        this.value = new UndefinedValue();
    }

    public EnvironmentVariable(Type type, Expr expr) {
        this(type);
        this.expr = expr;
    }


    public Type getType() {
        return type;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public Value getValue() {
        return value;
    }
}
