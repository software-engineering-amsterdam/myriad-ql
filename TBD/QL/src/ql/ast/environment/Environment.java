package ql.ast.environment;


import ql.ast.Expr;
import ql.ast.Statement;
import ql.ast.Statements;
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
    private final HashMap<Statements, Scope> scopes = new HashMap<>();
    public Scope currentScope = null;
    private HashMap<String, EnvironmentVariable> variables = new HashMap<>();

    private final EvalASTVisitor evalASTVisitor = new EvalASTVisitor(this);
    private final List<EnvironmentEventListener> eventListeners = new ArrayList<>();


    public void addScope(Statements node) {
        scopes.put(node, new Scope(currentScope));
    }

    public void setScope(Statements node) {
        currentScope = scopes.get(node);
        variables = currentScope.getVariables();
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
        currentScope.addVariable(key, new EnvironmentVariable(type));
    }

    public void addVariable(String key, Type type, Expr expr){
        currentScope.addVariable(key, new EnvironmentVariable(type, expr));
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

    public Value evalExpr(Expr expr) {
        return evalASTVisitor.startVisitor(expr);
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
