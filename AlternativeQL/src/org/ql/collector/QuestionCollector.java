package org.ql.collector;

import org.ql.ast.Node;
import org.ql.ast.statement.Question;

public interface QuestionCollector<T extends Node> extends NodeCollector<Question, T> {
}
