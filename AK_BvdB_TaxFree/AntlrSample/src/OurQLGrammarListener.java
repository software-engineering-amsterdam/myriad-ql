import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

public class OurQLGrammarListener extends QLGrammarBaseListener {
    @Override
    public void enterForm(QLGrammarParser.FormContext ctx) {
        System.out.println("ENTERED FORM");
//        List list = ctx.children;
        printChildren(ctx);
        super.enterForm(ctx);
    }

    private void printChildren(ParseTree ctx) {
        if (ctx.getChildCount() == 0) {
//            switch (((TerminalNode) ctx).getSymbol().getTokenIndex()) {
//                case QLGrammarParser.RULE_form:
            switch (((TerminalNode) ctx).getSymbol().getTokenIndex()) {
                case QLGrammarParser.RULE_form:
                    break;
                case QLGrammarParser.QUESTION:
                    break;
                case QLGrammarParser.RULE_question:
                    break;
                case QLGrammarParser.RULE_ifStatement:
                    break;
                default:
                    break;
            }
//            if ((((TerminalNode) ctx).getSymbol().getTokenIndex()) < QLGrammarParser.ruleNames.length) {
//            System.out.println("- Symbol: " + ctx.getText() + " | " + ((TerminalNode) ctx).getSymbol().getTokenIndex() + " | " + QLGrammarParser.ruleNames[((TerminalNode) ctx).getSymbol().getTokenIndex()]);
            System.out.println("- Symbol: " + ctx.getText() + " | " + ((TerminalNode) ctx).getSymbol().getTokenIndex() + " | " + ((TerminalNode) ctx).getSymbol().getTokenIndex());
//            }
//            }
        } else {
            System.out.println("Found children:");
            for (int i = 0; i < ctx.getChildCount(); i++) {
                printChildren(ctx.getChild(i));
            }
        }
        switch ("asdas") {
            case "a": break;
            default: break;
        }
    }

    @Override
    public void enterStatement(QLGrammarParser.StatementContext ctx) {
//        System.out.println("ENTERED STATEMENT " + ctx.getText());
        super.enterStatement(ctx);
    }

    @Override
    public void enterQuestion(QLGrammarParser.QuestionContext ctx) {
//        System.out.println("ENTERED QUESTION " + ctx.getText());
        super.enterQuestion(ctx);
    }
}
