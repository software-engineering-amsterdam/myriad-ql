import java.util.Stack;

import org.antlr.v4.runtime.tree.TerminalNode;

public class MakeForm extends QLBaseListener {

    private Stack<Form> stack = new Stack<>();

    public Form getForm() {
        return stack.get(0);
    }

    @Override public void enterForm(final QLParser.FormContext ctx) {
        System.err.println("Entering form");
    }

    @Override public void exitForm(final QLParser.FormContext ctx) {
        System.err.println("Exiting form");
    }

    @Override public void enterFormbod(final QLParser.FormbodContext ctx) {
        System.err.println("Entering formbod");
    }

    @Override public void exitFormbod(final QLParser.FormbodContext ctx) {
        System.err.println("Exiting formbod");
    }

    @Override public void enterQuestions(final QLParser.QuestionsContext ctx) {
        System.err.println("Entering questions");
    }

    @Override public void exitQuestions(final QLParser.QuestionsContext ctx) {
        System.err.println("Exiting questions");
    }

    @Override public void enterQuestion(final QLParser.QuestionContext ctx) {
        System.err.println("Entering question");
    }

    @Override public void exitQuestion(final QLParser.QuestionContext ctx) {
        System.err.println("Exiting question");
    }

    @Override public void enterExpr(final QLParser.ExprContext ctx) {
        System.err.println("Entering expr");
    }

    @Override public void exitExpr(final QLParser.ExprContext ctx) {
        System.err.println("Exiting expr");
    }

    @Override public void enterConditional(final QLParser.ConditionalContext ctx) {
        System.err.println("Entering conditional");
    }

    @Override public void exitConditional(final QLParser.ConditionalContext ctx) {
        System.err.println("Exiting conditional");
    }

    @Override public void enterLabel(final QLParser.LabelContext ctx) {
        System.err.println("Entering label");
    }

    @Override public void exitLabel(final QLParser.LabelContext ctx) {
        System.err.println("Exiting label");
    }

    @Override public void enterIdentifier(final QLParser.IdentifierContext ctx) {
        System.err.println("Entering identifier");
    }

    @Override public void exitIdentifier(final QLParser.IdentifierContext ctx) {
        System.err.println("Exiting identifier");
    }

    @Override public void enterType_specifier(final QLParser.Type_specifierContext ctx) {
        System.err.println("Entering type_specifier");
    }

    @Override public void exitType_specifier(final QLParser.Type_specifierContext ctx) {
        System.err.println("Exiting type_specifier");
    }

    @Override public void enterUnaryoperator(final QLParser.UnaryoperatorContext ctx) {
        System.err.println("Entering unaryoperator");
    }

    @Override public void exitUnaryoperator(final QLParser.UnaryoperatorContext ctx) {
        System.err.println("Exiting unaryoperator");
    }

    @Override public void enterBinaryoperator(final QLParser.BinaryoperatorContext ctx) {
        System.err.println("Entering binaryoperator");
    }

    @Override public void exitBinaryoperator(final QLParser.BinaryoperatorContext ctx) {
        System.err.println("Exiting binaryoperator");
    }

    @Override public void visitTerminal(final TerminalNode node) {
        System.err.println("terminal " + node.getText());
    }

}
