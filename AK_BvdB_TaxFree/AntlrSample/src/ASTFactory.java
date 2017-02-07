import org.antlr.v4.runtime.tree.ParseTree;

import java.util.List;

/**
 * Created by Alex on 7-2-2017.
 */
public class ASTFactory {

    public static Node generateAST(ParseTree parseTree) {
        generateNode(parseTree);
    }

    private static Node generateNode(ParseTree parseTree) {
        Node formNode = new FormNode(QLGrammarParser.RULE_form, null);
        for (int i = 0; i < parseTree.getChildCount(); i++) {
            Node childNode = generateNode(parseTree.getChild(i));
            formNode.addChild(childNode);
        }
        return formNode;
    }
}
