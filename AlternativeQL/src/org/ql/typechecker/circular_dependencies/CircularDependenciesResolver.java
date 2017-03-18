package org.ql.typechecker.circular_dependencies;

/**
 * Keeps a set of identifier pairs and can produce transitive closure and
 * with its help - circular dependency detection
 */
public class CircularDependenciesResolver {
    private final DependencySet dependencies = new DependencySet();

    public void register(DependencyPair pair) {
        dependencies.add(pair);
    }

    public DependencySet circularDependencies() {
        DependencySet closure = new DependencySet();

        for (DependencyPair pair : transitiveClosure()) {
            if (pair.isReflexive())
                closure.add(pair);
        }

        return closure;
    }

    public DependencySet transitiveClosure() {
        DependencySet closure = new DependencySet();
        closure.addAll(dependencies);

        while (true) {
            DependencySet newEdges = closure.edges();
            newEdges.addAll(closure);

            if (newEdges.equals(closure))
                break;

            closure = newEdges;
        }

        return closure;
    }
}