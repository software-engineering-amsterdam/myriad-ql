package com.matthewchapman.ql.environment.datastores;

import com.matthewchapman.ql.ast.Expression;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by matt on 20/03/2017.
 * <p>
 * Stores question ID's with conditional expressions
 */
public class ConditionTable {

    private final Map<String, Expression> conditions;

    public ConditionTable() {
        conditions = new HashMap<>();
    }

    public void addCondition(String name, Expression expression) {
        conditions.put(name, expression);
    }

    public boolean questionHasCondition(String name) {
        return conditions.containsKey(name);
    }

    public Expression getConditionByID(String name) {
        return conditions.get(name);
    }

}
