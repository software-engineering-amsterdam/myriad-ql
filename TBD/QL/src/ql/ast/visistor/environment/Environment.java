package ql.ast.visistor.environment;


import java.util.HashMap;

/**
 * Created by Erik on 21-2-2017.
 */
public class Environment {
    private HashMap<String, EnvironmentVariable> variables = new HashMap<>();

    public Environment() {
        variables.clear();
    }

    public void addVariable(String key, EnvironmentVariable value){
        variables.put(key, value);
    }

    public EnvironmentVariable getVariable(String key) {
        if (variables.containsKey(key)) {
            return variables.get(key);
        }
        return null;
    }

    public boolean contains(String key) {
        return variables.containsKey(key);
    }

}
