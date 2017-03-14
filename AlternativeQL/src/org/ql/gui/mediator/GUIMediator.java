package org.ql.gui.mediator;

import org.ql.ast.Identifier;
import org.ql.evaluator.value.Value;

public interface GUIMediator {
    void actualizeValue(Identifier identifier, Value newValue);
}
