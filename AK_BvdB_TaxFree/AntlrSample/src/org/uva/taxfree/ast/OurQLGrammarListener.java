package org.uva.taxfree.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.uva.taxfree.gen.QLGrammarListener;
import org.uva.taxfree.gen.QLGrammarParser;
import org.uva.taxfree.model.FormNode;
import org.uva.taxfree.model.Node;
import org.uva.taxfree.model.QuestionNode;

public class OurQLGrammarListener implements QLGrammarListener {
    private AST mAST;
    private Node mCurrentParent;

    @Override
    public void enterForm(QLGrammarParser.FormContext ctx) {
        Node newFormNode = new FormNode("FORM", mCurrentParent);
        if (mAST == null) {
            mAST = new AST(newFormNode);
        }
        mCurrentParent = newFormNode;
    }

    @Override
    public void enterQuestion(QLGrammarParser.QuestionContext ctx) {
        new QuestionNode(ctx.VARNAME().toString(), ctx.VARTYPE().toString(), mCurrentParent);
    }

    public AST getAST() {
        return mAST;
    }

    // Intentionally left blank
    @Override
    public void exitForm(QLGrammarParser.FormContext ctx) {

    }

    @Override
    public void exitQuestion(QLGrammarParser.QuestionContext ctx) {

    }

    @Override
    public void enterIfStatement(QLGrammarParser.IfStatementContext ctx) {

    }

    @Override
    public void exitIfStatement(QLGrammarParser.IfStatementContext ctx) {

    }

    @Override
    public void enterStatement(QLGrammarParser.StatementContext ctx) {

    }

    @Override
    public void exitStatement(QLGrammarParser.StatementContext ctx) {

    }

    @Override
    public void visitTerminal(TerminalNode terminalNode) {

    }

    @Override
    public void visitErrorNode(ErrorNode errorNode) {

    }

    @Override
    public void enterEveryRule(ParserRuleContext parserRuleContext) {

    }

    @Override
    public void exitEveryRule(ParserRuleContext parserRuleContext) {

    }
}
