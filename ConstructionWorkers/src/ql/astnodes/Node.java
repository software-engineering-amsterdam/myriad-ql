/**
 * Node.java.
 */

package ql.astnodes;

public abstract class Node {

    private final LineNumber lineNumber;

    public Node() {
        this.lineNumber = null;
    }

    public Node(LineNumber lineNumber) {
        this.lineNumber = lineNumber;
    }

    public LineNumber getLineNumber() {
        return lineNumber;
    }
}
