package org.lemonade.visitors.interfaces;

import org.lemonade.nodes.ComputedQuestion;
import org.lemonade.nodes.Conditional;
import org.lemonade.nodes.Form;
import org.lemonade.nodes.Question;

public interface BaseVisitor<T> {
    T visit(Form form);

    T visit(Question question);

    T visit(ComputedQuestion question);

    T visit(Conditional conditional);
}
