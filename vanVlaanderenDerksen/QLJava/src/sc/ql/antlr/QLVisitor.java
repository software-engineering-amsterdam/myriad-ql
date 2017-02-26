// Generated from QL.g4 by ANTLR 4.5.3
package sc.ql.antlr;
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
	 * Visit a parse tree produced by {@link QLParser#form}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForm(QLParser.FormContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Question}
	 * labeled alternative in {@link QLParser#form_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion(QLParser.QuestionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code if_statement}
	 * labeled alternative in {@link QLParser#form_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_statement(QLParser.If_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#conditional_block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditional_block(QLParser.Conditional_blockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idAtom}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdAtom(QLParser.IdAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpr(QLParser.NotExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code opExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpExpr(QLParser.OpExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intAtom}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntAtom(QLParser.IntAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code strAtom}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStrAtom(QLParser.StrAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolAtom}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolAtom(QLParser.BoolAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpr(QLParser.ParenExprContext ctx);
}