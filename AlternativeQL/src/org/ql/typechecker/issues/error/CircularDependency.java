package org.ql.typechecker.issues.error;

import org.ql.ast.identifier.Identifier;
import org.ql.typechecker.circular_dependencies.DependencyPair;
import org.ql.typechecker.issues.Issue;

public class CircularDependency extends Issue {
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
