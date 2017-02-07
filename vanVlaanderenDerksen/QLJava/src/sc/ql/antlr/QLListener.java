// Generated from D:/Software Engineering/Software Construction/Lab assignment/myriad-ql/vanVlaanderenDerksen/QLJava/src/sc/ql/antlr/QL.g4 by ANTLR 4.5.3
package sc.ql.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QLParser}.
 */
public interface QLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QLParser#parse}.
	 * @param ctx the parse tree
	 */
	void enterParse(QLParser.ParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#parse}.
	 * @param ctx the parse tree
	 */
	void exitParse(QLParser.ParseContext ctx);
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
	 * Enter a parse tree produced by {@link QLParser#unExpr}.
	 * @param ctx the parse tree
	 */
	void enterUnExpr(QLParser.UnExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#unExpr}.
	 * @param ctx the parse tree
	 */
	void exitUnExpr(QLParser.UnExprContext ctx);
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