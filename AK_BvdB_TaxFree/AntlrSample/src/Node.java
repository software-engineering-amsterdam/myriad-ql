import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 7-2-2017.
 */
public abstract class Node {

    private int mName;
    private final int mType;
    private Node mParent;
    private List<Node> mChildren;

    public Node(int type, Node parent) {
        mType = type;
        mParent = parent;
        mChildren = new ArrayList<>();
    }

    public int getMyType() {
        return mType;
    }

    public Node getParent() {
        return mParent;
    }

    public List<Node> getChildren() {
        return mChildren;
    }

    public void addChild(Node child) {
        mChildren.add(child);
    }
}
