// Generated from C:/Users/sotos/Documents/GitHub/myriad-ql/MC-SA/mcsaQL/grammars\Exp.g4 by ANTLR 4.6
package com.mcsa.gen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExpParser}.
 */
public interface ExpListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ExpParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(ExpParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(ExpParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpParser#content}.
	 * @param ctx the parse tree
	 */
	void enterContent(ExpParser.ContentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpParser#content}.
	 * @param ctx the parse tree
	 */
	void exitContent(ExpParser.ContentContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpParser#categorise}.
	 * @param ctx the parse tree
	 */
	void enterCategorise(ExpParser.CategoriseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpParser#categorise}.
	 * @param ctx the parse tree
	 */
	void exitCategorise(ExpParser.CategoriseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpParser#caseNewInput}.
	 * @param ctx the parse tree
	 */
	void enterCaseNewInput(ExpParser.CaseNewInputContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpParser#caseNewInput}.
	 * @param ctx the parse tree
	 */
	void exitCaseNewInput(ExpParser.CaseNewInputContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(ExpParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(ExpParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpParser#mathaction}.
	 * @param ctx the parse tree
	 */
	void enterMathaction(ExpParser.MathactionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpParser#mathaction}.
	 * @param ctx the parse tree
	 */
	void exitMathaction(ExpParser.MathactionContext ctx);
}