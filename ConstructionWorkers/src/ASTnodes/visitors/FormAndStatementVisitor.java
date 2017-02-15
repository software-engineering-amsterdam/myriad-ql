/**
 * FormAndStatementVisitor.java.
 */

package ASTnodes.visitors;

import ASTnodes.Form;
import ASTnodes.statements.*;

public interface FormAndStatementVisitor<T> {

    T visit(Form structure);

    T visit(SimpleQuestion statement);
    T visit(ComputedQuestion statement);
    T visit(IfStatement statement);

}
