package org.ql.typechecker.circular_dependencies;

import org.ql.ast.Identifier;

public class DependencyPair {
    private final Identifier left;
    private final Identifier right;

    public DependencyPair(Identifier left, Identifier right) {
        this.left = left;
        this.right = right;
    }

    public Identifier getLeft() {
        return left;
    }

    public Identifier getRight() {
        return right;
    }

    public boolean isReflexive() {
        return left.equals(right);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = left != null ? left.hashCode() : 0;
        return prime * result + (right != null ? right.hashCode() : 0);
    }

    public boolean equals(DependencyPair pair) {
        if (this == pair) return true;
        if (pair == null) return false;

        return left.equals(pair.left) && right.equals(pair.right);
    }

    public boolean isTransitiveWith(DependencyPair supposedPair) {
        return right.equals(supposedPair.getLeft());
    }
}
