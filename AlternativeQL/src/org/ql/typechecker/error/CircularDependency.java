package org.ql.typechecker.error;

import org.ql.ast.Identifier;
import org.ql.typechecker.circular_dependencies.DependencyPair;

public class CircularDependency implements TypeError {
    private final DependencyPair pair;

    public CircularDependency(DependencyPair pair) {
        this.pair = pair;
    }

    @Override
    public String getMessage() {
        return "Circular dependency detected for question '" + pair.getLeft() + "'";
    }

    @Override
    public Identifier getNode() {
        return pair.getLeft();
    }
}
