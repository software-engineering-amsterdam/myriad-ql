// Generated from QL.g4 by ANTLR 4.5.3
package sc.ql.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QLParser}.
 */
public interface QLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QLParser#form}.
	 * @param ctx the parse tree
	 */
	void enterForm(QLParser.FormContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#form}.
	 * @param ctx the parse tree
	 */
	void exitForm(QLParser.FormContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#questions}.
	 * @param ctx the parse tree
	 */
	void enterQuestions(QLParser.QuestionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#questions}.
	 * @param ctx the parse tree
	 */
	void exitQuestions(QLParser.QuestionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#question}.
	 * @param ctx the parse tree
	 */
	void enterQuestion(QLParser.QuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#question}.
	 * @param ctx the parse tree
	 */
	void exitQuestion(QLParser.QuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(QLParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(QLParser.PrimaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(QLParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(QLParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#calcExpr}.
	 * @param ctx the parse tree
	 */
	void enterCalcExpr(QLParser.CalcExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#calcExpr}.
	 * @param ctx the parse tree
	 */
	void exitCalcExpr(QLParser.CalcExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#relExpr}.
	 * @param ctx the parse tree
	 */
	void enterRelExpr(QLParser.RelExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#relExpr}.
	 * @param ctx the parse tree
	 */
	void exitRelExpr(QLParser.RelExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#boolExpr}.
	 * @param ctx the parse tree
	 */
	void enterBoolExpr(QLParser.BoolExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#boolExpr}.
	 * @param ctx the parse tree
	 */
	void exitBoolExpr(QLParser.BoolExprContext ctx);
}