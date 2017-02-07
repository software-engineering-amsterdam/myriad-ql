// Generated from D:/Software Engineering/Software Construction/Lab assignment/myriad-ql/vanVlaanderenDerksen/QLJava/src/sc/ql/antlr/QL.g4 by ANTLR 4.5.3
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
	 * Visit a parse tree produced by {@link QLParser#parse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParse(QLParser.ParseContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary(QLParser.PrimaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#unExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnExpr(QLParser.UnExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#calcExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCalcExpr(QLParser.CalcExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#relExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelExpr(QLParser.RelExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#boolExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolExpr(QLParser.BoolExprContext ctx);
}