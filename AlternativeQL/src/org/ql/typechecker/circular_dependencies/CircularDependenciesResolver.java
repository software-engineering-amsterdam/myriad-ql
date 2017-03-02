package org.ql.typechecker.circular_dependencies;


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
            DependencySet newRelations = closure.edges();
            newRelations.addAll(closure);

            if (newRelations.hashCode() == closure.hashCode())
                break;

            closure = newRelations;
        }

        return closure;
    }
}