// Generated from QL.g4 by ANTLR 4.6
package org.ql.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QLParser}.
 */
public interface QLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QLParser#form}.
	 * @param ctx the parse tree
	 */
	void enterForm(QLParser.FormContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#form}.
	 * @param ctx the parse tree
	 */
	void exitForm(QLParser.FormContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(QLParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(QLParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#question}.
	 * @param ctx the parse tree
	 */
	void enterQuestion(QLParser.QuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#question}.
	 * @param ctx the parse tree
	 */
	void exitQuestion(QLParser.QuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(QLParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(QLParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#defaulvalue}.
	 * @param ctx the parse tree
	 */
	void enterDefaulvalue(QLParser.DefaulvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#defaulvalue}.
	 * @param ctx the parse tree
	 */
	void exitDefaulvalue(QLParser.DefaulvalueContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(QLParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(QLParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(QLParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(QLParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#boolean_literal}.
	 * @param ctx the parse tree
	 */
	void enterBoolean_literal(QLParser.Boolean_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#boolean_literal}.
	 * @param ctx the parse tree
	 */
	void exitBoolean_literal(QLParser.Boolean_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#string_literal}.
	 * @param ctx the parse tree
	 */
	void enterString_literal(QLParser.String_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#string_literal}.
	 * @param ctx the parse tree
	 */
	void exitString_literal(QLParser.String_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#float_literal}.
	 * @param ctx the parse tree
	 */
	void enterFloat_literal(QLParser.Float_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#float_literal}.
	 * @param ctx the parse tree
	 */
	void exitFloat_literal(QLParser.Float_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#integer_literal}.
	 * @param ctx the parse tree
	 */
	void enterInteger_literal(QLParser.Integer_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#integer_literal}.
	 * @param ctx the parse tree
	 */
	void exitInteger_literal(QLParser.Integer_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(QLParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(QLParser.TypeContext ctx);
}