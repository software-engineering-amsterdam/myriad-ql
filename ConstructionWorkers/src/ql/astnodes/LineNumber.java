/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/astnodes/LineNumber.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.astnodes;

public class LineNumber {

    private final int lineNumber;

    public LineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getStartingLine() {
        return lineNumber;
    }

    @Override
    public String toString() {
        return "Code starting line: " + lineNumber;
    }
}
