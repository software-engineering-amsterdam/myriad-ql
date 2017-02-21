package org.lemonade.nodes;

import org.lemonade.visitors.ASTVisitor;

public interface ASTNode {

    <T> T accept(ASTVisitor<T> visitor);
}

