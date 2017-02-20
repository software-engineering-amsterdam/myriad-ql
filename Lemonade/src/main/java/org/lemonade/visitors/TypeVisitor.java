package org.lemonade.visitors;

import org.lemonade.nodes.expressions.Type;

/**
 *
 */
public interface TypeVisitor<T>  {
    T visit(Type type);
}
