package org.lemonade.visitors;

import org.lemonade.Conditional;
import org.lemonade.Question;

/**
 *
 */
public interface BlockVisitor<T>{
    T visitQuestionNode(Question question);
    T visitConditionalNode(Conditional conditional);
}
