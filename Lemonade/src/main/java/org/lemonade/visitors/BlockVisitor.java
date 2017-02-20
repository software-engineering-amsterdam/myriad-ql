package org.lemonade.visitors;

import org.lemonade.nodes.Block;
import org.lemonade.nodes.Conditional;
import org.lemonade.nodes.Question;

/**
 *
 */
public interface BlockVisitor<T> {

    T visit(Block block);

    T visit(Question question);
    T visit(Conditional conditional);
}
