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
	 * Visit a parse tree produced by the {@code booleanType}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanType(QLParser.BooleanTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringType}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringType(QLParser.StringTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intType}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntType(QLParser.IntTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dateType}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDateType(QLParser.DateTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code decimalType}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecimalType(QLParser.DecimalTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code moneyType}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoneyType(QLParser.MoneyTypeContext ctx);
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
	 * Visit a parse tree produced by the {@code logicalBooleanExpression}
	 * labeled alternative in {@link QLParser#boolExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalBooleanExpression(QLParser.LogicalBooleanExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code negatedBooleanExpression}
	 * labeled alternative in {@link QLParser#boolExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegatedBooleanExpression(QLParser.NegatedBooleanExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code booleanValueExpression}
	 * labeled alternative in {@link QLParser#boolExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanValueExpression(QLParser.BooleanValueExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code booleanIdentifierExpression}
	 * labeled alternative in {@link QLParser#boolExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanIdentifierExpression(QLParser.BooleanIdentifierExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code logicalIntegerExpression}
	 * labeled alternative in {@link QLParser#boolExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalIntegerExpression(QLParser.LogicalIntegerExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addExpression}
	 * labeled alternative in {@link QLParser#numExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddExpression(QLParser.AddExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nestedExpression}
	 * labeled alternative in {@link QLParser#numExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNestedExpression(QLParser.NestedExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code identifierExpression}
	 * labeled alternative in {@link QLParser#numExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierExpression(QLParser.IdentifierExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multiExpression}
	 * labeled alternative in {@link QLParser#numExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiExpression(QLParser.MultiExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code numberValueExpression}
	 * labeled alternative in {@link QLParser#numExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberValueExpression(QLParser.NumberValueExpressionContext ctx);
}
