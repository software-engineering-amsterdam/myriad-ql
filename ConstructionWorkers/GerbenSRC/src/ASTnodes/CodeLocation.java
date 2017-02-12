package ASTnodes;

/**
 * Created by LGGX on 09-Feb-17.
 */
public class CodeLocation {

    private final int startLine;

    public CodeLocation(int startLine) {
        this.startLine = startLine;
    }

    public int getStartLine() {
        return startLine;
    }

    @Override
    public String toString() {
        return "Code line: " + startLine;
    }

}
