/**
 * LineNumber.java.
 */

package QL.ASTnodes;

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
