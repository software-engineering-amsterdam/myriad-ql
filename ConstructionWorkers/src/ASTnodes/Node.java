package ASTnodes;

/**
 * Created by LGGX on 09-Feb-17.
 */
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
