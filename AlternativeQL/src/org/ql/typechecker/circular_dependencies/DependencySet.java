package org.ql.typechecker.circular_dependencies;

import java.util.HashSet;

public class DependencySet extends HashSet<DependencyPair> {
    public DependencySet edges() {
        DependencySet pairs = new DependencySet();

        for (DependencyPair pair : this) {
            for (DependencyPair supposed : this) {
                if (pair.isTransitiveWith(supposed)) {
                    pairs.add(new DependencyPair(pair.getLeft(), supposed.getRight()));
                }
            }
        }

        return pairs;
    }
}
