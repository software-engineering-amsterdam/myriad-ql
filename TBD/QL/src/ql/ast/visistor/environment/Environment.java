package ql.ast.visistor.environment;


import ql.ast.types.Type;
import ql.ast.values.Value;
import ql.ast.visistor.EvalASTVisitor;

import java.util.HashMap;

/**
 * Created by Erik on 21-2-2017.
 */
public class Environment {
    private HashMap<String, EnvironmentVariable> variables = new HashMap<>();
    private final EvalASTVisitor evalASTVisitor = new EvalASTVisitor(this);

    public Environment() {
        variables.clear();
    }

    public void addVariable(String key, EnvironmentVariable value){
        variables.put(key, value);
    }

    public Value getVariableValue(String key) {
        if (variables.containsKey(key)) {
            EnvironmentVariable variable = variables.get(key);
            if(variable.hasExpr()) {
                return evalASTVisitor.startVisitor(variable.getExpr());
            }
            return variables.get(key).getValue();
        }
        return null;
    }

    public Type getVariableType(String key) {
        if (variables.containsKey(key)) {
            return variables.get(key).getType();
        }
        return null;
    }

    public EnvironmentVariable getVariable(String key) {
        if (variables.containsKey(key)) {
            return variables.get(key);
        }
        return null;
    }

    public void setVariableValue(String key, Value value) {
        if (variables.containsKey(key)) {
            variables.get(key).setValue(value);
        }
    }

    public boolean contains(String key) {
        return variables.containsKey(key);
    }

}
