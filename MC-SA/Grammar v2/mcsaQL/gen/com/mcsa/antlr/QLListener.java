// Generated from /Users/matt/Repos/myriad-ql/MC-SA/Grammar v2/mcsaQL/src/grammars/QL.g4 by ANTLR 4.6
package com.mcsa.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QLParser}.
 */
public interface QLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QLParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(QLParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(QLParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#statementContent}.
	 * @param ctx the parse tree
	 */
	void enterStatementContent(QLParser.StatementContentContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#statementContent}.
	 * @param ctx the parse tree
	 */
	void exitStatementContent(QLParser.StatementContentContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#categorise}.
	 * @param ctx the parse tree
	 */
	void enterCategorise(QLParser.CategoriseContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#categorise}.
	 * @param ctx the parse tree
	 */
	void exitCategorise(QLParser.CategoriseContext ctx);
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
	 * Enter a parse tree produced by {@link QLParser#caseNewInput}.
	 * @param ctx the parse tree
	 */
	void enterCaseNewInput(QLParser.CaseNewInputContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#caseNewInput}.
	 * @param ctx the parse tree
	 */
	void exitCaseNewInput(QLParser.CaseNewInputContext ctx);
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