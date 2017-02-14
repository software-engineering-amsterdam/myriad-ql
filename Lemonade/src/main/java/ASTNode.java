import java.util.ArrayList;
import java.util.List;

/**
 * Created by dimitribelfor on 13/02/2017.
 */
public class ASTNode {
    private int lineNo;//Start in the code (for errors)
    private List<ASTNode> children;
    //Maybe I'm forgetting stuff

    public ASTNode (int lineNo) {
        this.lineNo = lineNo;
        this.children = new ArrayList<ASTNode>();
    }

    public void addChild (ASTNode child) {
        this.children.add(child);
    }
}
