// Generated from /Users/matt/Documents/IntelliJ/mcql/grammar/QL.g4 by ANTLR 4.6
package com.matthewchapman.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic validator for a parse tree produced
 * by {@link QLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface QLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link QLParser#formDeclaration}.
	 * @param ctx the parse tree
	 * @return the validator result
	 */
	T visitFormDeclaration(QLParser.FormDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code question}
	 * labeled alternative in {@link QLParser#statement}.
	 * @param ctx the parse tree
	 * @return the validator result
	 */
	T visitQuestion(QLParser.QuestionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifStatement}
	 * labeled alternative in {@link QLParser#statement}.
	 * @param ctx the parse tree
	 * @return the validator result
	 */
	T visitIfStatement(QLParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code negation}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the validator result
	 */
	T visitNegation(QLParser.NegationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subtraction}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the validator result
	 */
	T visitSubtraction(QLParser.SubtractionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notEqual}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the validator result
	 */
	T visitNotEqual(QLParser.NotEqualContext ctx);
	/**
	 * Visit a parse tree produced by the {@code logicalAnd}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the validator result
	 */
	T visitLogicalAnd(QLParser.LogicalAndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parameterGroup}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the validator result
	 */
	T visitParameterGroup(QLParser.ParameterGroupContext ctx);
	/**
	 * Visit a parse tree produced by the {@code division}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the validator result
	 */
	T visitDivision(QLParser.DivisionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code equal}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the validator result
	 */
	T visitEqual(QLParser.EqualContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringLiteral}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the validator result
	 */
	T visitStringLiteral(QLParser.StringLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parameter}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the validator result
	 */
	T visitParameter(QLParser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lessThan}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the validator result
	 */
	T visitLessThan(QLParser.LessThanContext ctx);
	/**
	 * Visit a parse tree produced by the {@code greaterThanEqualTo}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the validator result
	 */
	T visitGreaterThanEqualTo(QLParser.GreaterThanEqualToContext ctx);
	/**
	 * Visit a parse tree produced by the {@code integerLiteral}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the validator result
	 */
	T visitIntegerLiteral(QLParser.IntegerLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multiplication}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the validator result
	 */
	T visitMultiplication(QLParser.MultiplicationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code logicalOr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the validator result
	 */
	T visitLogicalOr(QLParser.LogicalOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addition}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the validator result
	 */
	T visitAddition(QLParser.AdditionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code greaterThan}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the validator result
	 */
	T visitGreaterThan(QLParser.GreaterThanContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lessThanEqualTo}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the validator result
	 */
	T visitLessThanEqualTo(QLParser.LessThanEqualToContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#calculatedValue}.
	 * @param ctx the parse tree
	 * @return the validator result
	 */
	T visitCalculatedValue(QLParser.CalculatedValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code booleanType}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 * @return the validator result
	 */
	T visitBooleanType(QLParser.BooleanTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code integerType}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 * @return the validator result
	 */
	T visitIntegerType(QLParser.IntegerTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringType}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 * @return the validator result
	 */
	T visitStringType(QLParser.StringTypeContext ctx);
}