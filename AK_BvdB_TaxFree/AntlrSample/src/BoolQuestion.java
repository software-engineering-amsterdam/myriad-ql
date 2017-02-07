import java.util.List;

/**
 * Created by Alex on 7-2-2017.
 */
public class BoolQuestion extends QuestionNode {

    private boolean mValue;

    public BoolQuestion(int type, Node parent, List<Node> children) {
        super(type, parent, children);
    }
}
