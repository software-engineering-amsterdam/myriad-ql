public class AST {

    private Node mRootNode;

    public AST(Node rootNode) {
        mRootNode = rootNode;
    }
    public String getName(){
        return mRootNode.getName();
    }
}

