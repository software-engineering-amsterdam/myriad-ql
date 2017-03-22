package com.matthewchapman.ql.environment.datastores;

import com.matthewchapman.ql.ast.Expression;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by matt on 20/03/2017.
 */
public class ConditionTable {

    private final Map<String, Expression> conditions;

    public ConditionTable() {
        conditions = new HashMap<>();
    }

    public void addCondition(String name, Expression expression) {
        conditions.put(name, expression);
    }

}
