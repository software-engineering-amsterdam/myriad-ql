/**
 * FormAndStatementVisitor.java.
 */

package QL.ASTnodes.visitors;

import QL.ASTnodes.Form;
import QL.ASTnodes.statements.*;

public interface FormAndStatementVisitor<T> {
    T visit(Form form);

    T visit(SimpleQuestion statement);
    T visit(ComputedQuestion statement);
    T visit(IfStatement statement);
}
