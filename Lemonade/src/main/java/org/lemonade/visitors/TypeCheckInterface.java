package org.lemonade.visitors;

import org.lemonade.nodes.Block;
import org.lemonade.nodes.expressions.Type;


/**
 *
 */
public interface TypeCheckInterface extends FormVisitor<Boolean>, BlockVisitor<Block>, ExpressionVisitor<Type>  {

}
