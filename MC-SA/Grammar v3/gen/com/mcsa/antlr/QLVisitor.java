// Generated from /Users/matt/Repos/myriad-ql/MC-SA/Grammar v3/src/com/mcsa/grammars/QL.g4 by ANTLR 4.6
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
	 * Visit a parse tree produced by {@link QLParser#formDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormDeclaration(QLParser.FormDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(QLParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(QLParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion(QLParser.QuestionContext ctx);
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
	 * Visit a parse tree produced by {@link QLParser#questionParameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionParameters(QLParser.QuestionParametersContext ctx);
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