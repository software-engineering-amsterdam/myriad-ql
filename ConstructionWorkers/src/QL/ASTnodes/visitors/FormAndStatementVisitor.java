/**
 * FormAndStatementVisitor.java.
 */

package ql.astnodes.visitors;

import ql.astnodes.Form;
import ql.astnodes.statements.*;

public interface FormAndStatementVisitor<T> {
    T visit(Form form);

    T visit(SimpleQuestion statement);
    T visit(ComputedQuestion statement);
    T visit(IfStatement statement);
}
