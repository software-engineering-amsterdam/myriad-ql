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

    private final int startingLine;

    public LineNumber(int startingLine) {
        this.startingLine = startingLine;
    }

    public int getStartingLine() {
        return startingLine;
    }

    @Override
    public String toString() {
        return "Code starting line: " + startingLine;
    }
}
