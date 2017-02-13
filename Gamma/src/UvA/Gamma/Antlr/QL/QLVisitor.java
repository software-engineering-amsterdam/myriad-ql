package UvA.Gamma.Antlr.QL;// Generated from src/UvA/Gamma/Antlr/QL//QL.g4 by ANTLR 4.6
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
	 * Visit a parse tree produced by the {@code in}
	 * labeled alternative in {@link QLParser#formItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIn(QLParser.InContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cond}
	 * labeled alternative in {@link QLParser#formItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCond(QLParser.CondContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#input}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInput(QLParser.InputContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#baseType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseType(QLParser.BaseTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code base_type}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBase_type(QLParser.Base_typeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expression}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(QLParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(QLParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code andor}
	 * labeled alternative in {@link QLParser#boolExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndor(QLParser.AndorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code comparison}
	 * labeled alternative in {@link QLParser#boolExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparison(QLParser.ComparisonContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bool}
	 * labeled alternative in {@link QLParser#boolExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool(QLParser.BoolContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolId}
	 * labeled alternative in {@link QLParser#boolExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolId(QLParser.BoolIdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code div}
	 * labeled alternative in {@link QLParser#intExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDiv(QLParser.DivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code add}
	 * labeled alternative in {@link QLParser#intExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd(QLParser.AddContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intId}
	 * labeled alternative in {@link QLParser#intExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntId(QLParser.IntIdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code int}
	 * labeled alternative in {@link QLParser#intExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt(QLParser.IntContext ctx);
}
