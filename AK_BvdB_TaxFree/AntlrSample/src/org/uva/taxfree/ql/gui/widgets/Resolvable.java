package org.uva.taxfree.ql.gui.widgets;

import org.uva.taxfree.ql.gui.FormListener;
import org.uva.taxfree.ql.model.values.Value;

public interface Resolvable {
    Value resolve();
    void callOnUpdate(FormListener listener);
}
