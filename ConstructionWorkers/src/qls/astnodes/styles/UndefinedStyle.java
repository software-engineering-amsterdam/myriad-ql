/*
 * Software Construction - University of Amsterdam
 *
 * ./src/qls/astnodes/styles/UndefinedStyle.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package qls.astnodes.styles;

import ql.astnodes.LineNumber;

public class UndefinedStyle extends Style {

    public UndefinedStyle(LineNumber lineNumber) {
        super(lineNumber);
    }

    @Override
    public boolean isUndefined() {
        return true;
    }
}
