package org.ql.typechecker.circular_dependencies;

import org.ql.ast.identifier.Identifier;

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = left != null ? left.hashCode() : 0;
        return prime * result + (right != null ? right.hashCode() : 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DependencyPair pair = (DependencyPair) o;

        if (left != null ? !left.equals(pair.left) : pair.left != null) return false;
        return right != null ? right.equals(pair.right) : pair.right == null;

    }

    public boolean isTransitiveWith(DependencyPair supposedPair) {
        return right.equals(supposedPair.left);
    }

    public boolean isReflexive() {
        return right.equals(left);
    }
}
