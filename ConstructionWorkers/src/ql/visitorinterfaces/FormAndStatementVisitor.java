/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/visitorinterfaces/FormAndStatementVisitor.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.visitorinterfaces;

import ql.astnodes.Form;
import ql.astnodes.statements.*;

public interface FormAndStatementVisitor<T> {
    T visit(Form form);

    T visit(SimpleQuestion statement);
    T visit(ComputedQuestion statement);
    T visit(IfStatement statement);
}
