// Generated from QLParser.g4 by ANTLR 4.6
package org.ql.grammar;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QLParserParser}.
 */
public interface QLParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QLParserParser#form}.
	 * @param ctx the parse tree
	 */
	void enterForm(QLParserParser.FormContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParserParser#form}.
	 * @param ctx the parse tree
	 */
	void exitForm(QLParserParser.FormContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParserParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(QLParserParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParserParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(QLParserParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParserParser#question}.
	 * @param ctx the parse tree
	 */
	void enterQuestion(QLParserParser.QuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParserParser#question}.
	 * @param ctx the parse tree
	 */
	void exitQuestion(QLParserParser.QuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParserParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(QLParserParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParserParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(QLParserParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParserParser#defaulvalue}.
	 * @param ctx the parse tree
	 */
	void enterDefaulvalue(QLParserParser.DefaulvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParserParser#defaulvalue}.
	 * @param ctx the parse tree
	 */
	void exitDefaulvalue(QLParserParser.DefaulvalueContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(QLParserParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(QLParserParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParserParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(QLParserParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParserParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(QLParserParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParserParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(QLParserParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParserParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(QLParserParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParserParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(QLParserParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParserParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(QLParserParser.TypeContext ctx);
}