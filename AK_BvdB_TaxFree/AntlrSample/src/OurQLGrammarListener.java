import org.antlr.v4.runtime.tree.ParseTree;

public class OurQLGrammarListener extends QLGrammarBaseListener {
    @Override
    public void enterForm(QLGrammarParser.FormContext ctx) {
//        System.out.println("ENTERED FORM");
//        List list = ctx.children;
        printChildren(ctx);
//        Node ourAST = new Node(QLGrammarParser.RULE_form, null, ctx);
        ASTFactory.generateAST(ctx);
        super.enterForm(ctx);
    }

    private void printChildren(ParseTree ctx) {
        if (ctx.getChildCount() == 0) {
//            if (validRule(((TerminalNode) ctx).getSymbol().getType())) {
//                System.out.println("Symbol! " + ((TerminalNode) ctx).getSymbol().getType() + " | " + ctx.getText() );
//            }
        } else {
//            System.out.println("Found children:");
            for (int i = 0; i < ctx.getChildCount(); i++) {
                printChildren(ctx.getChild(i));
            }
        }
        switch ("asdas") {
            case "a": break;
            default: break;
        }
    }

    private boolean validRule(int ruleType) {
        return ruleType < QLGrammarParser.ruleNames.length;
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
