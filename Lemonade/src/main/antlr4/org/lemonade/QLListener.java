// Generated from /Users/sander/gits/myriad-ql/Lemonade/src/main/antlr4/QL.g4 by ANTLR 4.6
package org.lemonade;
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
	 * Enter a parse tree produced by {@link QLParser#statements}.
	 * @param ctx the parse tree
	 */
	void enterStatements(QLParser.StatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#statements}.
	 * @param ctx the parse tree
	 */
	void exitStatements(QLParser.StatementsContext ctx);
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
	 * Enter a parse tree produced by {@link QLParser#questions}.
	 * @param ctx the parse tree
	 */
	void enterQuestions(QLParser.QuestionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#questions}.
	 * @param ctx the parse tree
	 */
	void exitQuestions(QLParser.QuestionsContext ctx);
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
	 * Enter a parse tree produced by {@link QLParser#conditionals}.
	 * @param ctx the parse tree
	 */
	void enterConditionals(QLParser.ConditionalsContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#conditionals}.
	 * @param ctx the parse tree
	 */
	void exitConditionals(QLParser.ConditionalsContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#conditional}.
	 * @param ctx the parse tree
	 */
	void enterConditional(QLParser.ConditionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#conditional}.
	 * @param ctx the parse tree
	 */
	void exitConditional(QLParser.ConditionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(QLParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(QLParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#label}.
	 * @param ctx the parse tree
	 */
	void enterLabel(QLParser.LabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#label}.
	 * @param ctx the parse tree
	 */
	void exitLabel(QLParser.LabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(QLParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(QLParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#type_specifier}.
	 * @param ctx the parse tree
	 */
	void enterType_specifier(QLParser.Type_specifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#type_specifier}.
	 * @param ctx the parse tree
	 */
	void exitType_specifier(QLParser.Type_specifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#unaryoperator}.
	 * @param ctx the parse tree
	 */
	void enterUnaryoperator(QLParser.UnaryoperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#unaryoperator}.
	 * @param ctx the parse tree
	 */
	void exitUnaryoperator(QLParser.UnaryoperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#binaryoperator}.
	 * @param ctx the parse tree
	 */
	void enterBinaryoperator(QLParser.BinaryoperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#binaryoperator}.
	 * @param ctx the parse tree
	 */
	void exitBinaryoperator(QLParser.BinaryoperatorContext ctx);
}