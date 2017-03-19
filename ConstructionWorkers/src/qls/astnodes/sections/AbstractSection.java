/*
 * Software Construction - University of Amsterdam
 *
 * ./src/qls/astnodes/sections/AbstractSection.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package qls.astnodes.sections;

import ql.astnodes.LineNumber;
import ql.astnodes.Node;
import qls.visitorinterfaces.StyleSheetVisitor;

public abstract class AbstractSection extends Node{

    public AbstractSection(LineNumber lineNumber) {
        super(lineNumber);
    }

    public abstract void accept(StyleSheetVisitor visitor);
}
