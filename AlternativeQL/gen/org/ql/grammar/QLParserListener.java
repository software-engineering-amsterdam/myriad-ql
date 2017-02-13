// Generated from QLParser.g4 by ANTLR 4.6
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
	 * Enter a parse tree produced by {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(QLParserParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(QLParserParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParserParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(QLParserParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParserParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(QLParserParser.ParameterContext ctx);
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
	 * Enter a parse tree produced by the {@code booleanLiteral}
	 * labeled alternative in {@link QLParserParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterBooleanLiteral(QLParserParser.BooleanLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code booleanLiteral}
	 * labeled alternative in {@link QLParserParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitBooleanLiteral(QLParserParser.BooleanLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringLiteral}
	 * labeled alternative in {@link QLParserParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterStringLiteral(QLParserParser.StringLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringLiteral}
	 * labeled alternative in {@link QLParserParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitStringLiteral(QLParserParser.StringLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code floatLiteral}
	 * labeled alternative in {@link QLParserParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterFloatLiteral(QLParserParser.FloatLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code floatLiteral}
	 * labeled alternative in {@link QLParserParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitFloatLiteral(QLParserParser.FloatLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code integerLiteral}
	 * labeled alternative in {@link QLParserParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterIntegerLiteral(QLParserParser.IntegerLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code integerLiteral}
	 * labeled alternative in {@link QLParserParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitIntegerLiteral(QLParserParser.IntegerLiteralContext ctx);
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