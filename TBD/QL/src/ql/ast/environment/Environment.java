package ql.ast.environment;


import ql.ast.Expr;
import ql.ast.types.Type;
import ql.ast.values.Value;
import ql.ast.visistor.EvalASTVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Erik on 21-2-2017.
 */
public class Environment {
    private HashMap<String, EnvironmentVariable> variables = new HashMap<>();
    private final EvalASTVisitor evalASTVisitor = new EvalASTVisitor(this);
    private final List<EnvironmentEventListener> eventListeners = new ArrayList<>();

    public Environment() {
        variables.clear();
    }

    public void addEventListener(EnvironmentEventListener listener) {
        eventListeners.add(listener);
    }

    private void updateEvent(){
        for (EnvironmentEventListener listener : eventListeners) {
            listener.onChange();
        }
    }

    public void addVariable(String key, Type type){
        variables.put(key, new EnvironmentVariable(type));
    }

    public void addVariable(String key, Type type, Expr expr){
        variables.put(key, new EnvironmentVariable(type, expr));
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

    public boolean hasExpr(String key) {
        return variables.get(key).hasExpr();
    }

    public void setVariableValue(String key, Value value) {
        if (variables.containsKey(key)) {
            variables.get(key).setValue(value);
            updateEvent();
        }
    }

    public boolean contains(String key) {
        return variables.containsKey(key);
    }

}
