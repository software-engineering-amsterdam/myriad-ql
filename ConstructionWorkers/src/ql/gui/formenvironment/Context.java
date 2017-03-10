/**
 * Context.java.
 */

package ql.gui.formenvironment;

import ql.gui.formenvironment.values.Value;

import java.util.HashMap;
import java.util.Map;

public class Context {

    private Map<String, Value> environment;

    public Context() {
        this.environment = new HashMap<>();
    }

    public void addValue(String key, Value value) {
        environment.put(key, value);
    }

    public Value getValue(String key) {
        return environment.get(key);
    }
}
