/**
 * Node.java.
 */

package ASTnodes;

public abstract class Node {

    private final CodeLocation location;

    public Node(CodeLocation location) {
        this.location = location;
    }

    public CodeLocation getLocation() {
        return location;
    }
}
