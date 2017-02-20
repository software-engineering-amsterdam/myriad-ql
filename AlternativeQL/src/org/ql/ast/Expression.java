package org.ql.ast;

import org.ql.ast.expression.Visitor;

public interface Expression extends Node {
    <T> T accept(Visitor<T> visitor);
}
