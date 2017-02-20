package org.uva.taxfree.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.uva.taxfree.gen.QLGrammarListener;
import org.uva.taxfree.gen.QLGrammarParser;
import org.uva.taxfree.model.FormNode;
import org.uva.taxfree.model.Node;

import java.util.Stack;

//public class OurQLGrammarListener implements ParseTreeListener { // Now we do not need to override everything while developing
public class OurQLGrammarListener implements QLGrammarListener { // To enforce us to override every method. We can also extend the base one to not override everything

    private Ast mAst;
    Stack<Node> mParentStack = new Stack<>();

    public void enterForm(QLGrammarParser.FormContext ctx) {
        Node newFormNode = new FormNode("FORM");
        if (mAst == null) {
            mAst = new Ast(newFormNode);
        }
        mParentStack.push(newFormNode);
    }
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

    @Override
    public void exitForm(QLGrammarParser.FormContext ctx) {

    }

    @Override
    public void enterStatement(QLGrammarParser.StatementContext ctx) {

    }

    @Override
    public void exitStatement(QLGrammarParser.StatementContext ctx) {

    }

    public void enterQuestion(QLGrammarParser.QuestionContext ctx) {

    }

    @Override
    public void exitQuestion(QLGrammarParser.QuestionContext ctx) {

    }

    @Override
    public void enterCalculation(QLGrammarParser.CalculationContext ctx) {

    }

    @Override
    public void exitCalculation(QLGrammarParser.CalculationContext ctx) {

    }

    @Override
    public void enterIfStatement(QLGrammarParser.IfStatementContext ctx) {

    }

    @Override
    public void exitIfStatement(QLGrammarParser.IfStatementContext ctx) {

    }

    @Override
    public void enterIfElseStatement(QLGrammarParser.IfElseStatementContext ctx) {

    }

    @Override
    public void exitIfElseStatement(QLGrammarParser.IfElseStatementContext ctx) {

    }

    @Override
    public void enterExpression(QLGrammarParser.ExpressionContext ctx) {

    }

    @Override
    public void exitExpression(QLGrammarParser.ExpressionContext ctx) {

    }

    @Override
    public void enterBooleanType(QLGrammarParser.BooleanTypeContext ctx) {

    }

    @Override
    public void exitBooleanType(QLGrammarParser.BooleanTypeContext ctx) {

    }

    @Override
    public void enterStringType(QLGrammarParser.StringTypeContext ctx) {

    }

    @Override
    public void exitStringType(QLGrammarParser.StringTypeContext ctx) {

    }

    @Override
    public void enterIntegerType(QLGrammarParser.IntegerTypeContext ctx) {

    }

    @Override
    public void exitIntegerType(QLGrammarParser.IntegerTypeContext ctx) {

    }

    @Override
    public void enterVarType(QLGrammarParser.VarTypeContext ctx) {

    }

    @Override
    public void exitVarType(QLGrammarParser.VarTypeContext ctx) {

    }

    public Ast getAst() {
        return mAst;
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
