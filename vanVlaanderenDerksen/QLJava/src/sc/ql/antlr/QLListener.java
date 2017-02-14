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
	 * Enter a parse tree produced by {@link QLParser#form_element}.
	 * @param ctx the parse tree
	 */
	void enterForm_element(QLParser.Form_elementContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#form_element}.
	 * @param ctx the parse tree
	 */
	void exitForm_element(QLParser.Form_elementContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#conditional_block}.
	 * @param ctx the parse tree
	 */
	void enterConditional_block(QLParser.Conditional_blockContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#conditional_block}.
	 * @param ctx the parse tree
	 */
	void exitConditional_block(QLParser.Conditional_blockContext ctx);
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
}