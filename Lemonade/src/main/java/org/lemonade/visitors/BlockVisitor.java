package org.lemonade.visitors;

import org.lemonade.nodes.Block;

/**
 *
 */
public interface BlockVisitor<T> {
    T visit(Block block);
}
