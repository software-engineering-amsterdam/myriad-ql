package com.matthewchapman.ql.environment.datastores;

import com.matthewchapman.ql.ast.Expression;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * Created by matt on 20/03/2017.
 */
public class ValueTable extends Observable {

    private final Map<String, Expression> values;

    public ValueTable() {
        values = new HashMap<>();
    }

    public void addValue(String name, Expression expression) {
        values.put(name, expression);
    }

    public Expression getValueByID(String name) {
        return values.get(name);
    }

}
