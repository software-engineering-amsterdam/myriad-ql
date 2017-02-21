// Generated from C:/Users/sotos/Documents/GitHub/myriad-ql/MC-SA/sa parsing/mcsaQL/src/grammars\QL.g4 by ANTLR 4.6
package com.mcsa.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QLParser}.
 */
public interface QLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QLParser#formDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFormDeclaration(QLParser.FormDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#formDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFormDeclaration(QLParser.FormDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#content}.
	 * @param ctx the parse tree
	 */
	void enterContent(QLParser.ContentContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#content}.
	 * @param ctx the parse tree
	 */
	void exitContent(QLParser.ContentContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#categories}.
	 * @param ctx the parse tree
	 */
	void enterCategories(QLParser.CategoriesContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#categories}.
	 * @param ctx the parse tree
	 */
	void exitCategories(QLParser.CategoriesContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(QLParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(QLParser.IfStatementContext ctx);
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
	 * Enter a parse tree produced by {@link QLParser#ifCase}.
	 * @param ctx the parse tree
	 */
	void enterIfCase(QLParser.IfCaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#ifCase}.
	 * @param ctx the parse tree
	 */
	void exitIfCase(QLParser.IfCaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#ifCaseArgs}.
	 * @param ctx the parse tree
	 */
	void enterIfCaseArgs(QLParser.IfCaseArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#ifCaseArgs}.
	 * @param ctx the parse tree
	 */
	void exitIfCaseArgs(QLParser.IfCaseArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#questionParameters}.
	 * @param ctx the parse tree
	 */
	void enterQuestionParameters(QLParser.QuestionParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#questionParameters}.
	 * @param ctx the parse tree
	 */
	void exitQuestionParameters(QLParser.QuestionParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(QLParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(QLParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#mathaction}.
	 * @param ctx the parse tree
	 */
	void enterMathaction(QLParser.MathactionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#mathaction}.
	 * @param ctx the parse tree
	 */
	void exitMathaction(QLParser.MathactionContext ctx);
}