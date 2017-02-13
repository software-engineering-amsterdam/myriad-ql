package org.uva.taxfree.ast;

import org.uva.taxfree.gen.QLGrammarBaseListener;
import org.uva.taxfree.gen.QLGrammarParser;
import org.uva.taxfree.model.FormNode;
import org.uva.taxfree.model.Node;
import org.uva.taxfree.model.QuestionNode;

public class OurQLGrammarListener extends QLGrammarBaseListener {
    private Node mCurrentParent;

    @Override
    public void enterForm(QLGrammarParser.FormContext ctx) {
        super.enterForm(ctx);
        System.out.println("ENTERED FORM");
        Node newFormNode = new FormNode("FORM", mCurrentParent);
        mCurrentParent = newFormNode;
    }

    @Override
    public void enterStatement(QLGrammarParser.StatementContext ctx) {
//        System.out.println("ENTERED STATEMENT " + ctx.getText());
        super.enterStatement(ctx);
    }

    @Override
    public void enterQuestion(QLGrammarParser.QuestionContext ctx) {
        super.enterQuestion(ctx);
        System.out.println("ENTERED QUESTION " + ctx.getText());
        new QuestionNode(ctx.VARNAME().toString(), mCurrentParent);
    }
}
