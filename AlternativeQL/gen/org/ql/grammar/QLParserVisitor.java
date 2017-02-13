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
	 * Visit a parse tree produced by {@link QLParserParser#defaultValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefaultValue(QLParserParser.DefaultValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(QLParserParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParserParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(QLParserParser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParserParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(QLParserParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code booleanLiteral}
	 * labeled alternative in {@link QLParserParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanLiteral(QLParserParser.BooleanLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringLiteral}
	 * labeled alternative in {@link QLParserParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteral(QLParserParser.StringLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code floatLiteral}
	 * labeled alternative in {@link QLParserParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatLiteral(QLParserParser.FloatLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code integerLiteral}
	 * labeled alternative in {@link QLParserParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerLiteral(QLParserParser.IntegerLiteralContext ctx);
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