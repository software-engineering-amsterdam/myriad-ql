package org.ql.collector;

import org.ql.ast.Node;

import java.util.List;

public interface NodeCollector<R extends Node, T extends Node> {
    List<R> collect(T form);
}
