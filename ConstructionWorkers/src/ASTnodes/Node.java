/**
 * Node.java.
 */

package ASTnodes;

public abstract class Node {

    private final CodeLocation location;

    public Node() {
        this.location = null;
    }

    public Node(CodeLocation location) {
        this.location = location;
    }

    public CodeLocation getLocation() {
        return location;
    }
}
