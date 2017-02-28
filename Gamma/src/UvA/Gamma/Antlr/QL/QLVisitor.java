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
	 * Visit a parse tree produced by {@link QLParser#formItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormItem(QLParser.FormItemContext ctx);
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
	 * Visit a parse tree produced by {@link QLParser#elseblock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseblock(QLParser.ElseblockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code booleanExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanExpression(QLParser.BooleanExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code numberExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberExpression(QLParser.NumberExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code moneyExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoneyExpression(QLParser.MoneyExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#boolExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolExpr(QLParser.BoolExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#numExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumExpr(QLParser.NumExprContext ctx);
}
