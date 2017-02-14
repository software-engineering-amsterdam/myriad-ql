// Generated from QLParser.g4 by ANTLR 4.6
package org.ql.grammar;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parseForm tree produced by
 * {@link QLParserParser}.
 */
public interface QLParserListener extends ParseTreeListener {
	/**
	 * Enter a parseForm tree produced by {@link QLParserParser#form}.
	 * @param ctx the parseForm tree
	 */
	void enterForm(QLParserParser.FormContext ctx);
	/**
	 * Exit a parseForm tree produced by {@link QLParserParser#form}.
	 * @param ctx the parseForm tree
	 */
	void exitForm(QLParserParser.FormContext ctx);
	/**
	 * Enter a parseForm tree produced by the {@code question}
	 * labeled alternative in {@link QLParserParser#statement}.
	 * @param ctx the parseForm tree
	 */
	void enterQuestion(QLParserParser.QuestionContext ctx);
	/**
	 * Exit a parseForm tree produced by the {@code question}
	 * labeled alternative in {@link QLParserParser#statement}.
	 * @param ctx the parseForm tree
	 */
	void exitQuestion(QLParserParser.QuestionContext ctx);
	/**
	 * Enter a parseForm tree produced by the {@code if}
	 * labeled alternative in {@link QLParserParser#statement}.
	 * @param ctx the parseForm tree
	 */
	void enterIf(QLParserParser.IfContext ctx);
	/**
	 * Exit a parseForm tree produced by the {@code if}
	 * labeled alternative in {@link QLParserParser#statement}.
	 * @param ctx the parseForm tree
	 */
	void exitIf(QLParserParser.IfContext ctx);
	/**
	 * Enter a parseForm tree produced by {@link QLParserParser#questionText}.
	 * @param ctx the parseForm tree
	 */
	void enterQuestionText(QLParserParser.QuestionTextContext ctx);
	/**
	 * Exit a parseForm tree produced by {@link QLParserParser#questionText}.
	 * @param ctx the parseForm tree
	 */
	void exitQuestionText(QLParserParser.QuestionTextContext ctx);
	/**
	 * Enter a parseForm tree produced by {@link QLParserParser#defaultValue}.
	 * @param ctx the parseForm tree
	 */
	void enterDefaultValue(QLParserParser.DefaultValueContext ctx);
	/**
	 * Exit a parseForm tree produced by {@link QLParserParser#defaultValue}.
	 * @param ctx the parseForm tree
	 */
	void exitDefaultValue(QLParserParser.DefaultValueContext ctx);
	/**
	 * Enter a parseForm tree produced by the {@code negation}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void enterNegation(QLParserParser.NegationContext ctx);
	/**
	 * Exit a parseForm tree produced by the {@code negation}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void exitNegation(QLParserParser.NegationContext ctx);
	/**
	 * Enter a parseForm tree produced by the {@code product}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void enterProduct(QLParserParser.ProductContext ctx);
	/**
	 * Exit a parseForm tree produced by the {@code product}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void exitProduct(QLParserParser.ProductContext ctx);
	/**
	 * Enter a parseForm tree produced by the {@code increment}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void enterIncrement(QLParserParser.IncrementContext ctx);
	/**
	 * Exit a parseForm tree produced by the {@code increment}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void exitIncrement(QLParserParser.IncrementContext ctx);
	/**
	 * Enter a parseForm tree produced by the {@code subtraction}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void enterSubtraction(QLParserParser.SubtractionContext ctx);
	/**
	 * Exit a parseForm tree produced by the {@code subtraction}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void exitSubtraction(QLParserParser.SubtractionContext ctx);
	/**
	 * Enter a parseForm tree produced by the {@code notEqual}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void enterNotEqual(QLParserParser.NotEqualContext ctx);
	/**
	 * Exit a parseForm tree produced by the {@code notEqual}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void exitNotEqual(QLParserParser.NotEqualContext ctx);
	/**
	 * Enter a parseForm tree produced by the {@code logicalAnd}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void enterLogicalAnd(QLParserParser.LogicalAndContext ctx);
	/**
	 * Exit a parseForm tree produced by the {@code logicalAnd}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void exitLogicalAnd(QLParserParser.LogicalAndContext ctx);
	/**
	 * Enter a parseForm tree produced by the {@code lowerThan}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void enterLowerThan(QLParserParser.LowerThanContext ctx);
	/**
	 * Exit a parseForm tree produced by the {@code lowerThan}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void exitLowerThan(QLParserParser.LowerThanContext ctx);
	/**
	 * Enter a parseForm tree produced by the {@code greaterThanOrEqual}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void enterGreaterThanOrEqual(QLParserParser.GreaterThanOrEqualContext ctx);
	/**
	 * Exit a parseForm tree produced by the {@code greaterThanOrEqual}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void exitGreaterThanOrEqual(QLParserParser.GreaterThanOrEqualContext ctx);
	/**
	 * Enter a parseForm tree produced by the {@code division}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void enterDivision(QLParserParser.DivisionContext ctx);
	/**
	 * Exit a parseForm tree produced by the {@code division}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void exitDivision(QLParserParser.DivisionContext ctx);
	/**
	 * Enter a parseForm tree produced by the {@code stringLiteral}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void enterStringLiteral(QLParserParser.StringLiteralContext ctx);
	/**
	 * Exit a parseForm tree produced by the {@code stringLiteral}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void exitStringLiteral(QLParserParser.StringLiteralContext ctx);
	/**
	 * Enter a parseForm tree produced by the {@code decrement}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void enterDecrement(QLParserParser.DecrementContext ctx);
	/**
	 * Exit a parseForm tree produced by the {@code decrement}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void exitDecrement(QLParserParser.DecrementContext ctx);
	/**
	 * Enter a parseForm tree produced by the {@code parameter}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void enterParameter(QLParserParser.ParameterContext ctx);
	/**
	 * Exit a parseForm tree produced by the {@code parameter}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void exitParameter(QLParserParser.ParameterContext ctx);
	/**
	 * Enter a parseForm tree produced by the {@code equals}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void enterEquals(QLParserParser.EqualsContext ctx);
	/**
	 * Exit a parseForm tree produced by the {@code equals}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void exitEquals(QLParserParser.EqualsContext ctx);
	/**
	 * Enter a parseForm tree produced by the {@code lowerThanOrEqual}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void enterLowerThanOrEqual(QLParserParser.LowerThanOrEqualContext ctx);
	/**
	 * Exit a parseForm tree produced by the {@code lowerThanOrEqual}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void exitLowerThanOrEqual(QLParserParser.LowerThanOrEqualContext ctx);
	/**
	 * Enter a parseForm tree produced by the {@code floatLiteral}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void enterFloatLiteral(QLParserParser.FloatLiteralContext ctx);
	/**
	 * Exit a parseForm tree produced by the {@code floatLiteral}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void exitFloatLiteral(QLParserParser.FloatLiteralContext ctx);
	/**
	 * Enter a parseForm tree produced by the {@code integerLiteral}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void enterIntegerLiteral(QLParserParser.IntegerLiteralContext ctx);
	/**
	 * Exit a parseForm tree produced by the {@code integerLiteral}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void exitIntegerLiteral(QLParserParser.IntegerLiteralContext ctx);
	/**
	 * Enter a parseForm tree produced by the {@code logicalOr}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void enterLogicalOr(QLParserParser.LogicalOrContext ctx);
	/**
	 * Exit a parseForm tree produced by the {@code logicalOr}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void exitLogicalOr(QLParserParser.LogicalOrContext ctx);
	/**
	 * Enter a parseForm tree produced by the {@code booleanLiteral}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void enterBooleanLiteral(QLParserParser.BooleanLiteralContext ctx);
	/**
	 * Exit a parseForm tree produced by the {@code booleanLiteral}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void exitBooleanLiteral(QLParserParser.BooleanLiteralContext ctx);
	/**
	 * Enter a parseForm tree produced by the {@code group}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void enterGroup(QLParserParser.GroupContext ctx);
	/**
	 * Exit a parseForm tree produced by the {@code group}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void exitGroup(QLParserParser.GroupContext ctx);
	/**
	 * Enter a parseForm tree produced by the {@code addition}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void enterAddition(QLParserParser.AdditionContext ctx);
	/**
	 * Exit a parseForm tree produced by the {@code addition}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void exitAddition(QLParserParser.AdditionContext ctx);
	/**
	 * Enter a parseForm tree produced by the {@code greaterThan}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void enterGreaterThan(QLParserParser.GreaterThanContext ctx);
	/**
	 * Exit a parseForm tree produced by the {@code greaterThan}
	 * labeled alternative in {@link QLParserParser#expression}.
	 * @param ctx the parseForm tree
	 */
	void exitGreaterThan(QLParserParser.GreaterThanContext ctx);
	/**
	 * Enter a parseForm tree produced by {@link QLParserParser#identifier}.
	 * @param ctx the parseForm tree
	 */
	void enterIdentifier(QLParserParser.IdentifierContext ctx);
	/**
	 * Exit a parseForm tree produced by {@link QLParserParser#identifier}.
	 * @param ctx the parseForm tree
	 */
	void exitIdentifier(QLParserParser.IdentifierContext ctx);
	/**
	 * Enter a parseForm tree produced by the {@code typeBoolean}
	 * labeled alternative in {@link QLParserParser#type}.
	 * @param ctx the parseForm tree
	 */
	void enterTypeBoolean(QLParserParser.TypeBooleanContext ctx);
	/**
	 * Exit a parseForm tree produced by the {@code typeBoolean}
	 * labeled alternative in {@link QLParserParser#type}.
	 * @param ctx the parseForm tree
	 */
	void exitTypeBoolean(QLParserParser.TypeBooleanContext ctx);
	/**
	 * Enter a parseForm tree produced by the {@code typeFloat}
	 * labeled alternative in {@link QLParserParser#type}.
	 * @param ctx the parseForm tree
	 */
	void enterTypeFloat(QLParserParser.TypeFloatContext ctx);
	/**
	 * Exit a parseForm tree produced by the {@code typeFloat}
	 * labeled alternative in {@link QLParserParser#type}.
	 * @param ctx the parseForm tree
	 */
	void exitTypeFloat(QLParserParser.TypeFloatContext ctx);
	/**
	 * Enter a parseForm tree produced by the {@code typeInteger}
	 * labeled alternative in {@link QLParserParser#type}.
	 * @param ctx the parseForm tree
	 */
	void enterTypeInteger(QLParserParser.TypeIntegerContext ctx);
	/**
	 * Exit a parseForm tree produced by the {@code typeInteger}
	 * labeled alternative in {@link QLParserParser#type}.
	 * @param ctx the parseForm tree
	 */
	void exitTypeInteger(QLParserParser.TypeIntegerContext ctx);
	/**
	 * Enter a parseForm tree produced by the {@code typeString}
	 * labeled alternative in {@link QLParserParser#type}.
	 * @param ctx the parseForm tree
	 */
	void enterTypeString(QLParserParser.TypeStringContext ctx);
	/**
	 * Exit a parseForm tree produced by the {@code typeString}
	 * labeled alternative in {@link QLParserParser#type}.
	 * @param ctx the parseForm tree
	 */
	void exitTypeString(QLParserParser.TypeStringContext ctx);
	/**
	 * Enter a parseForm tree produced by the {@code typeMoney}
	 * labeled alternative in {@link QLParserParser#type}.
	 * @param ctx the parseForm tree
	 */
	void enterTypeMoney(QLParserParser.TypeMoneyContext ctx);
	/**
	 * Exit a parseForm tree produced by the {@code typeMoney}
	 * labeled alternative in {@link QLParserParser#type}.
	 * @param ctx the parseForm tree
	 */
	void exitTypeMoney(QLParserParser.TypeMoneyContext ctx);
	/**
	 * Enter a parseForm tree produced by the {@code typeDate}
	 * labeled alternative in {@link QLParserParser#type}.
	 * @param ctx the parseForm tree
	 */
	void enterTypeDate(QLParserParser.TypeDateContext ctx);
	/**
	 * Exit a parseForm tree produced by the {@code typeDate}
	 * labeled alternative in {@link QLParserParser#type}.
	 * @param ctx the parseForm tree
	 */
	void exitTypeDate(QLParserParser.TypeDateContext ctx);
}