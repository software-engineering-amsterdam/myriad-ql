/**
 * CodeLocation.java.
 */

package ASTnodes;

public class CodeLocation {

    private final int startingLine;

    public CodeLocation(int startingLine) {
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
