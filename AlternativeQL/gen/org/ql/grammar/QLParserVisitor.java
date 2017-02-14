// Generated from QLParser.g4 by ANTLR 4.6
package org.ql.grammar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link QLParserParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface QLParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link QLParserParser#form}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForm(QLParserParser.FormContext ctx);
	/**
	 * Visit a parse tree produced by the {@code question}
	 * labeled alternative in {@link QLParserParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion(QLParserParser.QuestionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code if}
	 * labeled alternative in {@link QLParserParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf(QLParserParser.IfContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParserParser#questionText}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionText(QLParserParser.QuestionTextContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParserParser#defaultValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefaultValue(QLParserParser.DefaultValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code negation}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegation(QLParserParser.NegationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code product}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProduct(QLParserParser.ProductContext ctx);
	/**
	 * Visit a parse tree produced by the {@code grouped}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGrouped(QLParserParser.GroupedContext ctx);
	/**
	 * Visit a parse tree produced by the {@code increment}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIncrement(QLParserParser.IncrementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subtraction}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubtraction(QLParserParser.SubtractionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notEqual}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotEqual(QLParserParser.NotEqualContext ctx);
	/**
	 * Visit a parse tree produced by the {@code logicalAnd}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalAnd(QLParserParser.LogicalAndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lowerThan}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLowerThan(QLParserParser.LowerThanContext ctx);
	/**
	 * Visit a parse tree produced by the {@code greaterThanOrEqual}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGreaterThanOrEqual(QLParserParser.GreaterThanOrEqualContext ctx);
	/**
	 * Visit a parse tree produced by the {@code division}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivision(QLParserParser.DivisionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringLiteral}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteral(QLParserParser.StringLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code decrement}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecrement(QLParserParser.DecrementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parameter}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(QLParserParser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code equals}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquals(QLParserParser.EqualsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lowerThanOrEqual}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLowerThanOrEqual(QLParserParser.LowerThanOrEqualContext ctx);
	/**
	 * Visit a parse tree produced by the {@code floatLiteral}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatLiteral(QLParserParser.FloatLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code integerLiteral}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerLiteral(QLParserParser.IntegerLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code logicalOr}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalOr(QLParserParser.LogicalOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code booleanLiteral}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanLiteral(QLParserParser.BooleanLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addition}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddition(QLParserParser.AdditionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code greaterThan}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGreaterThan(QLParserParser.GreaterThanContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParserParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(QLParserParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code typeBoolean}
	 * labeled alternative in {@link QLParserParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeBoolean(QLParserParser.TypeBooleanContext ctx);
	/**
	 * Visit a parse tree produced by the {@code typeFloat}
	 * labeled alternative in {@link QLParserParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeFloat(QLParserParser.TypeFloatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code typeInteger}
	 * labeled alternative in {@link QLParserParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeInteger(QLParserParser.TypeIntegerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code typeString}
	 * labeled alternative in {@link QLParserParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeString(QLParserParser.TypeStringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code typeMoney}
	 * labeled alternative in {@link QLParserParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeMoney(QLParserParser.TypeMoneyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code typeDate}
	 * labeled alternative in {@link QLParserParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDate(QLParserParser.TypeDateContext ctx);
}