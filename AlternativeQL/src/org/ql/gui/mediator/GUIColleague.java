package org.ql.gui.mediator;

import org.ql.ast.Identifier;
import org.ql.evaluator.value.Value;

public abstract class GUIColleague {
    private final GUIMediator mediator;

    public GUIColleague(GUIMediator mediator) {
        this.mediator = mediator;
    }

    protected void notifyForNewValue(Identifier identifier, Value newValue) {
        mediator.actualizeValue(identifier, newValue);
    }
}
