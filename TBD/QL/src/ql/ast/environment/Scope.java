package ql.ast.environment;

import ql.ast.Expr;
import ql.ast.types.Type;

import java.util.HashMap;

/**
 * Created by Erik on 4-3-2017.
 */
public class Scope {
    private HashMap<String, EnvironmentVariable> variables = new HashMap<>();
    private Scope parent;

    public Scope() {
        this.parent = null;
    }

    public Scope(Scope parent) {
        this.parent = parent;
    }

    public void addVariable(String key, EnvironmentVariable variable){
        variables.put(key,variable);
    }

    public HashMap<String, EnvironmentVariable> getVariables() {
        if (parent == null) {
            return new HashMap<>(variables);
        }
        HashMap<String, EnvironmentVariable> result = parent.getVariables();
        result.putAll(variables);
        return result;
    }


}
