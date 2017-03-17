package org.ql.gui;

import org.ql.ast.Identifier;
import org.ql.evaluator.value.Value;

public interface ValueReviser {
    void reviseValue(Identifier identifier, Value newValue);
}
