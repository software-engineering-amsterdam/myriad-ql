/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/astnodes/Node.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.astnodes;

public abstract class Node {

    private final LineNumber lineNumber;

    public Node() {
        lineNumber = null;
    }

    public Node(LineNumber lineNumber) {
        this.lineNumber = lineNumber;
    }

    public LineNumber getLineNumber() {
        return lineNumber;
    }
}
