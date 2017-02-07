import java.util.ArrayList;

public abstract class Node {

    private String mName;
    private Node mParent;
    private ArrayList<Node> mChildren;

    public Node(String name, Node parent) {
        mChildren = new ArrayList<>();
        mName = name;
        mParent = parent;
        registerToParent();
    }

    public void addChild(Node child) {
        mChildren.add(child);
    }

    private void registerToParent() {
        if(mParent != null) {
            mParent.addChild(this);
        }
    }
}
