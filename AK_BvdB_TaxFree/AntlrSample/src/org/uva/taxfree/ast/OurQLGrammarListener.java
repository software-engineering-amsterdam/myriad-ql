package org.uva.taxfree.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.uva.taxfree.gen.QLGrammarBaseListener;
import org.uva.taxfree.gen.QLGrammarParser;
import org.uva.taxfree.model.*;

import java.util.Stack;

//public class OurQLGrammarListener implements ParseTreeListener { // Now we do not need to override everything while developing
public class OurQLGrammarListener extends QLGrammarBaseListener{ // To enforce us to override every method. We can also extend the base one to not override everything

    private Ast mAst;
    Stack<Node> mParentStack = new Stack<>();

    private void popParent() {
        mParentStack.pop();
    }

    private void addParentNodeToAst(Node node) {
        addNodeToAst(node);
        mParentStack.push(node);
    }

    private void addNodeToAst(Node node) {
        mParentStack.peek().addChild(node);
    }

    public Ast getAst() {
        return mAst;
    }


    // Enters
    @Override
    public void enterForm(QLGrammarParser.FormContext ctx) {
        super.enterForm(ctx);
        Node formNode = new FormNode(ctx.formId.toString());
        if (mAst == null) {
            // It's the root!
            mAst = new Ast(formNode);
            mParentStack.push(formNode);
        } else {
            addParentNodeToAst(formNode);
        }
    }


    @Override
    public void enterQuestion(QLGrammarParser.QuestionContext ctx) {
        super.enterQuestion(ctx);
        Node questionNode = new StringQuestion(ctx.QUESTION().toString(), ctx.STRING_LITERAL().toString());
        addNodeToAst(questionNode);
    }


    // Exits
    @Override
    public void exitIfStatement(QLGrammarParser.IfStatementContext ctx) {
        super.exitIfStatement(ctx);
        popParent();
    }

    @Override
    public void exitIfElseStatement(QLGrammarParser.IfElseStatementContext ctx) {
        super.exitIfElseStatement(ctx);
        popParent();
    }

    @Override
    public void exitExpression(QLGrammarParser.ExpressionContext ctx) {
        super.exitExpression(ctx);
        popParent();
    }

    @Override
    public void exitForm(QLGrammarParser.FormContext ctx) {
        super.exitForm(ctx);
        popParent(); // Clear stack
    }
    @Override
    public void exitQuestion(QLGrammarParser.QuestionContext ctx) {
        super.exitQuestion(ctx);
    }

    @Override
    public void enterCalculation(QLGrammarParser.CalculationContext ctx) {
        super.enterCalculation(ctx);
    }

    @Override
    public void exitCalculation(QLGrammarParser.CalculationContext ctx) {
        super.exitCalculation(ctx);
    }

    @Override
    public void enterBooleanType(QLGrammarParser.BooleanTypeContext ctx) {
        super.enterBooleanType(ctx);
    }

    @Override
    public void exitBooleanType(QLGrammarParser.BooleanTypeContext ctx) {
        super.exitBooleanType(ctx);
    }

    @Override
    public void enterStringType(QLGrammarParser.StringTypeContext ctx) {
        super.enterStringType(ctx);
    }

    @Override
    public void exitStringType(QLGrammarParser.StringTypeContext ctx) {
        super.exitStringType(ctx);
    }

    @Override
    public void enterIntegerType(QLGrammarParser.IntegerTypeContext ctx) {
        super.enterIntegerType(ctx);
    }

    @Override
    public void exitIntegerType(QLGrammarParser.IntegerTypeContext ctx) {
        super.exitIntegerType(ctx);
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
        super.enterEveryRule(ctx);
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
        super.exitEveryRule(ctx);
    }

    @Override
    public void visitTerminal(TerminalNode node) {
        super.visitTerminal(node);
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
        super.visitErrorNode(node);
    }
}
