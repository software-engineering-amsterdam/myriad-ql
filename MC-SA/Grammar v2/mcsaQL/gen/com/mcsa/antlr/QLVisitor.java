// Generated from /Users/matt/Repos/myriad-ql/MC-SA/Grammar v2/mcsaQL/src/grammars/QL.g4 by ANTLR 4.6
package com.mcsa.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link QLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface QLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link QLParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(QLParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#statementContent}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementContent(QLParser.StatementContentContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#categorise}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCategorise(QLParser.CategoriseContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#ifCase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfCase(QLParser.IfCaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#ifCaseArgs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfCaseArgs(QLParser.IfCaseArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#caseNewInput}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseNewInput(QLParser.CaseNewInputContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(QLParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#mathaction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMathaction(QLParser.MathactionContext ctx);
}