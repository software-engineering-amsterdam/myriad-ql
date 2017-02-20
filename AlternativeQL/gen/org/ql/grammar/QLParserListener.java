// Generated from /Users/yoan-alexander/Sites/myriad-ql/AlternativeQL/src/org/ql/grammar/QLParser.g4 by ANTLR 4.6
package org.ql.grammar;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QLParserParser}.
 */
public interface QLParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QLParserParser#form}.
	 * @param ctx the parse tree
	 */
	void enterForm(QLParserParser.FormContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParserParser#form}.
	 * @param ctx the parse tree
	 */
	void exitForm(QLParserParser.FormContext ctx);
	/**
	 * Enter a parse tree produced by the {@code question}
	 * labeled alternative in {@link QLParserParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterQuestion(QLParserParser.QuestionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code question}
	 * labeled alternative in {@link QLParserParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitQuestion(QLParserParser.QuestionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code if}
	 * labeled alternative in {@link QLParserParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIf(QLParserParser.IfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code if}
	 * labeled alternative in {@link QLParserParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIf(QLParserParser.IfContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParserParser#questionText}.
	 * @param ctx the parse tree
	 */
	void enterQuestionText(QLParserParser.QuestionTextContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParserParser#questionText}.
	 * @param ctx the parse tree
	 */
	void exitQuestionText(QLParserParser.QuestionTextContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParserParser#defaultValue}.
	 * @param ctx the parse tree
	 */
	void enterDefaultValue(QLParserParser.DefaultValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParserParser#defaultValue}.
	 * @param ctx the parse tree
	 */
	void exitDefaultValue(QLParserParser.DefaultValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code decimalLiteral}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterDecimalLiteral(QLParserParser.DecimalLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code decimalLiteral}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitDecimalLiteral(QLParserParser.DecimalLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code negation}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNegation(QLParserParser.NegationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code negation}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNegation(QLParserParser.NegationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code product}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterProduct(QLParserParser.ProductContext ctx);
	/**
	 * Exit a parse tree produced by the {@code product}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitProduct(QLParserParser.ProductContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subtraction}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSubtraction(QLParserParser.SubtractionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subtraction}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSubtraction(QLParserParser.SubtractionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notEqual}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNotEqual(QLParserParser.NotEqualContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notEqual}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNotEqual(QLParserParser.NotEqualContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalAnd}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalAnd(QLParserParser.LogicalAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalAnd}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalAnd(QLParserParser.LogicalAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code increment}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIncrement(QLParserParser.IncrementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code increment}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIncrement(QLParserParser.IncrementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lowerThan}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLowerThan(QLParserParser.LowerThanContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lowerThan}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLowerThan(QLParserParser.LowerThanContext ctx);
	/**
	 * Enter a parse tree produced by the {@code greaterThanOrEqual}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterGreaterThanOrEqual(QLParserParser.GreaterThanOrEqualContext ctx);
	/**
	 * Exit a parse tree produced by the {@code greaterThanOrEqual}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitGreaterThanOrEqual(QLParserParser.GreaterThanOrEqualContext ctx);
	/**
	 * Enter a parse tree produced by the {@code division}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterDivision(QLParserParser.DivisionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code division}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitDivision(QLParserParser.DivisionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringLiteral}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterStringLiteral(QLParserParser.StringLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringLiteral}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitStringLiteral(QLParserParser.StringLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code decrement}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterDecrement(QLParserParser.DecrementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code decrement}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitDecrement(QLParserParser.DecrementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parameter}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParameter(QLParserParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parameter}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParameter(QLParserParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code equals}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterEquals(QLParserParser.EqualsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code equals}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitEquals(QLParserParser.EqualsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lowerThanOrEqual}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLowerThanOrEqual(QLParserParser.LowerThanOrEqualContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lowerThanOrEqual}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLowerThanOrEqual(QLParserParser.LowerThanOrEqualContext ctx);
	/**
	 * Enter a parse tree produced by the {@code integerLiteral}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIntegerLiteral(QLParserParser.IntegerLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code integerLiteral}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIntegerLiteral(QLParserParser.IntegerLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalOr}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOr(QLParserParser.LogicalOrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalOr}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOr(QLParserParser.LogicalOrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code booleanLiteral}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBooleanLiteral(QLParserParser.BooleanLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code booleanLiteral}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBooleanLiteral(QLParserParser.BooleanLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code group}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterGroup(QLParserParser.GroupContext ctx);
	/**
	 * Exit a parse tree produced by the {@code group}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitGroup(QLParserParser.GroupContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addition}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAddition(QLParserParser.AdditionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addition}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAddition(QLParserParser.AdditionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code greaterThan}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterGreaterThan(QLParserParser.GreaterThanContext ctx);
	/**
	 * Exit a parse tree produced by the {@code greaterThan}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitGreaterThan(QLParserParser.GreaterThanContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParserParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(QLParserParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParserParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(QLParserParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by the {@code typeBoolean}
	 * labeled alternative in {@link QLParserParser#type}.
	 * @param ctx the parse tree
	 */
	void enterTypeBoolean(QLParserParser.TypeBooleanContext ctx);
	/**
	 * Exit a parse tree produced by the {@code typeBoolean}
	 * labeled alternative in {@link QLParserParser#type}.
	 * @param ctx the parse tree
	 */
	void exitTypeBoolean(QLParserParser.TypeBooleanContext ctx);
	/**
	 * Enter a parse tree produced by the {@code typeFloat}
	 * labeled alternative in {@link QLParserParser#type}.
	 * @param ctx the parse tree
	 */
	void enterTypeFloat(QLParserParser.TypeFloatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code typeFloat}
	 * labeled alternative in {@link QLParserParser#type}.
	 * @param ctx the parse tree
	 */
	void exitTypeFloat(QLParserParser.TypeFloatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code typeInteger}
	 * labeled alternative in {@link QLParserParser#type}.
	 * @param ctx the parse tree
	 */
	void enterTypeInteger(QLParserParser.TypeIntegerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code typeInteger}
	 * labeled alternative in {@link QLParserParser#type}.
	 * @param ctx the parse tree
	 */
	void exitTypeInteger(QLParserParser.TypeIntegerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code typeString}
	 * labeled alternative in {@link QLParserParser#type}.
	 * @param ctx the parse tree
	 */
	void enterTypeString(QLParserParser.TypeStringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code typeString}
	 * labeled alternative in {@link QLParserParser#type}.
	 * @param ctx the parse tree
	 */
	void exitTypeString(QLParserParser.TypeStringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code typeMoney}
	 * labeled alternative in {@link QLParserParser#type}.
	 * @param ctx the parse tree
	 */
	void enterTypeMoney(QLParserParser.TypeMoneyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code typeMoney}
	 * labeled alternative in {@link QLParserParser#type}.
	 * @param ctx the parse tree
	 */
	void exitTypeMoney(QLParserParser.TypeMoneyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code typeDate}
	 * labeled alternative in {@link QLParserParser#type}.
	 * @param ctx the parse tree
	 */
	void enterTypeDate(QLParserParser.TypeDateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code typeDate}
	 * labeled alternative in {@link QLParserParser#type}.
	 * @param ctx the parse tree
	 */
	void exitTypeDate(QLParserParser.TypeDateContext ctx);
}