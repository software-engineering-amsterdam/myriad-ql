/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/gui/formenvironment/Context.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.gui.formenvironment;

import ql.gui.formenvironment.values.Value;

import java.util.HashMap;
import java.util.Map;

public class Context {

    private Map<String, Value> environment;

    public Context() {
        environment = new HashMap<>();
    }

    public void addValue(String key, Value value) {
        environment.put(key, value);
    }

    public Value getValue(String key) {
        return environment.get(key);
    }
}
