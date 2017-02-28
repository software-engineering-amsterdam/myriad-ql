package org.ql.collection.collector;

import org.ql.ast.Node;
import org.ql.collection.Questions;

public interface QuestionCollector<T extends Node> {
    Questions collect(T node);
}
