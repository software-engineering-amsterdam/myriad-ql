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
	 * Visit a parse tree produced by the {@code ques}
	 * labeled alternative in {@link QLParser#formItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQues(QLParser.QuesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code comp}
	 * labeled alternative in {@link QLParser#formItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComp(QLParser.CompContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cond}
	 * labeled alternative in {@link QLParser#formItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCond(QLParser.CondContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion(QLParser.QuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#computed}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComputed(QLParser.ComputedContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(QLParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(QLParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(QLParser.ExpressionContext ctx);
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
	 * labeled alternative in {@link QLParser#numExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDiv(QLParser.DivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code add}
	 * labeled alternative in {@link QLParser#numExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd(QLParser.AddContext ctx);
	/**
	 * Visit a parse tree produced by the {@code numId}
	 * labeled alternative in {@link QLParser#numExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumId(QLParser.NumIdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code num}
	 * labeled alternative in {@link QLParser#numExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNum(QLParser.NumContext ctx);
}
