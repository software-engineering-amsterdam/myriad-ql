package org.ql.typechecker.circular_dependencies;


import java.util.LinkedList;
import java.util.Queue;

public class CircularDependenciesResolver {
    private final DependencySet dependencies;

    public CircularDependenciesResolver(DependencySet dependencies) {
        this.dependencies = dependencies;
    }

    public CircularDependenciesResolver() {
        this.dependencies = new DependencySet();
    }

    public void register(DependencyPair pair) {
        dependencies.add(pair);
    }

    public DependencySet findTransitiveClosure() {
        Queue<DependencyPair> pairsQueue = new LinkedList<>();
        pairsQueue.addAll(dependencies);

        DependencySet transitiveClosure = dependencies.clone();

        while (!pairsQueue.isEmpty()) {
            DependencyPair pair = pairsQueue.remove();
            if (pair.isReflexive()) {
                continue;
            }
            DependencySet allTransitiveOf = transitiveClosure.allTransitiveOf(pair);
            transitiveClosure.addAll(allTransitiveOf);
            for (DependencyPair queuePair : allTransitiveOf) {
                if (!pairsQueue.contains(queuePair)) {
                    pairsQueue.add(queuePair);
                }
            }
        }

        return transitiveClosure;
    }
}