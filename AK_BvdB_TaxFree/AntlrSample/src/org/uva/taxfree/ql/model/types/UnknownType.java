package org.uva.taxfree.ql.model.types;

import org.uva.taxfree.ql.gui.QuestionForm;
import org.uva.taxfree.ql.model.operators.BooleanOperator;
import org.uva.taxfree.ql.model.operators.CompareOperator;
import org.uva.taxfree.ql.model.operators.NumericOperator;
import org.uva.taxfree.ql.model.values.StringValue;
import org.uva.taxfree.ql.model.values.Value;

// The UnknownType enables a configurable output of sematics messages.
// If a variable is not declared in the symbol table, its resolveType() will return
// an UnknownType type. Depending on the configuration of these methods,
// the semanticsChecker will either add all messages or none.

// Our rationale for allowing all operations on unsupported types is that only the REAL error
// Will be displayed. If a variable is not declared, only the declaration error shows.
// If this is fixed by declaring it, type errors will be displayed if present.

public class UnknownType extends Type {
    @Override
    public boolean supports(CompareOperator compareOperator) {
        return true;
    }

    @Override
    public boolean supports(BooleanOperator booleanOperator) {
        return true;
    }

    @Override
    public boolean supports(NumericOperator numericOperator) {
        return true;
    }

    @Override
    public Value defaultValue() {
        throw new RuntimeException("UnknownValues can not be present in a running application.");
    }

    @Override
    public void generateWidget(String label, String id, QuestionForm frame) {

    }

    @Override
    public boolean equals(Type other) {
        return true;
    }
}
