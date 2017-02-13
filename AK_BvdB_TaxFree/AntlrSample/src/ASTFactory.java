import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * Created by Alex on 7-2-2017.
 */
public class ASTFactory {

    public static Node generateAST(ParseTree parseTree) {
        Node rootNode = new FormNode("Form", null)
        return generateNode(parseTree, rootNode);
    }

    private static Node generateNode(ParseTree parseTree, Node parent) {
        Node newNode = registerChild(parseTree, parent);
        for (int i = 0; i < parseTree.getChildCount(); i++) {
            registerChild(parseTree.getChild(i), newNode);
        }
        return newNode;
    }

    private static void registerChild(ParseTree parseTree, Node parent) {
        switch (((RuleContext) parseTree).getRuleIndex()) {
            case QLGrammarParser.RULE_form:
                new FormNode("Form", parent);
                break;
            case QLGrammarParser.RULE_question:
                System.out.println("Found question!");
                generateQuestionNode(parseTree);
                break;
            case QLGrammarParser.RULE_ifStatement:
                System.out.println("Found if-statement!");
                break;
            default:
                System.out.println("Unknown rule");
                break;
        }
    }

    private static Node generateQuestionNode(ParseTree parseTree) {
        // All logic for this
        return null;
    }
}
