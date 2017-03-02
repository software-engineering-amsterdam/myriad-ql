package org.ql.typechecker.circular_dependencies;

import java.util.HashSet;

public class DependencySet extends HashSet<DependencyPair> {
    @Override
    public DependencySet clone() {
        return (DependencySet) super.clone();
    }

    public DependencySet allTransitiveOf(DependencyPair pair) {
        DependencySet result = new DependencySet();

        for (DependencyPair supposedPair : this) {
            if (pair.isTransitiveWith(supposedPair)) {
                result.add(new DependencyPair(pair.getLeft(), supposedPair.getRight()));
            }
        }

        return result;
    }

    @Override
    public String toString() {
        String output = "";

        for (DependencyPair pair : this) {
            output += "<" + pair.getLeft() + ", " + pair.getRight() + ">, \n";
        }

        return output;
    }
}
