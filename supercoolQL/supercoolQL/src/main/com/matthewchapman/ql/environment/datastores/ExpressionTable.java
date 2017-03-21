package com.matthewchapman.ql.environment.datastores;

import com.matthewchapman.ql.ast.Expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by matt on 20/03/2017.
 */
public class ExpressionTable {

    private final Map<String, Expression> expressions;

    public ExpressionTable() {
        expressions = new HashMap<>();
    }

    public void addExpression(String name, Expression expression) {
        expressions.put(name, expression);
    }

    public Expression getExpressionByID(String name) {
        return expressions.get(name);
    }

    public List<Expression> getExpressionsAsList() { return new ArrayList<>(expressions.values()); }

}
