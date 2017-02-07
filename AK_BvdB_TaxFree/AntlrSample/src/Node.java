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

    private void registerToParent() {
        if (mParent != null) {
            mParent.addChild(this);
        }
    }

    public boolean addChild(Node child) {
        if (false == mChildren.contains(child)) {
            mChildren.add(child);
        } else {
            LogUtil.logError(child.mName + " already registered to " + mName);
            return false;
        }
        return true;
    }


}
