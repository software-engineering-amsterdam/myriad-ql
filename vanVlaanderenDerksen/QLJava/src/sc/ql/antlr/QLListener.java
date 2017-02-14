// Generated from QL.g4 by ANTLR 4.5.3
package sc.ql.antlr;
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
	 * Enter a parse tree produced by the {@code Question}
	 * labeled alternative in {@link QLParser#form_element}.
	 * @param ctx the parse tree
	 */
	void enterQuestion(QLParser.QuestionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Question}
	 * labeled alternative in {@link QLParser#form_element}.
	 * @param ctx the parse tree
	 */
	void exitQuestion(QLParser.QuestionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code calcQuestion}
	 * labeled alternative in {@link QLParser#form_element}.
	 * @param ctx the parse tree
	 */
	void enterCalcQuestion(QLParser.CalcQuestionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code calcQuestion}
	 * labeled alternative in {@link QLParser#form_element}.
	 * @param ctx the parse tree
	 */
	void exitCalcQuestion(QLParser.CalcQuestionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code if_statement}
	 * labeled alternative in {@link QLParser#form_element}.
	 * @param ctx the parse tree
	 */
	void enterIf_statement(QLParser.If_statementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code if_statement}
	 * labeled alternative in {@link QLParser#form_element}.
	 * @param ctx the parse tree
	 */
	void exitIf_statement(QLParser.If_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#conditional_block}.
	 * @param ctx the parse tree
	 */
	void enterConditional_block(QLParser.Conditional_blockContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#conditional_block}.
	 * @param ctx the parse tree
	 */
	void exitConditional_block(QLParser.Conditional_blockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code idAtom}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIdAtom(QLParser.IdAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code idAtom}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIdAtom(QLParser.IdAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code opExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterOpExpr(QLParser.OpExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code opExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitOpExpr(QLParser.OpExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intAtom}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIntAtom(QLParser.IntAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intAtom}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIntAtom(QLParser.IntAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code strAtom}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterStrAtom(QLParser.StrAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code strAtom}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitStrAtom(QLParser.StrAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolAtom}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBoolAtom(QLParser.BoolAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolAtom}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBoolAtom(QLParser.BoolAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBoolExpr(QLParser.BoolExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBoolExpr(QLParser.BoolExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code relExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterRelExpr(QLParser.RelExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code relExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitRelExpr(QLParser.RelExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParenExpr(QLParser.ParenExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParenExpr(QLParser.ParenExprContext ctx);
}