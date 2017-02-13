package antlr;// Generated from C:/Users/LGGX/IdeaProjects/QL/src\QL.g4 by ANTLR 4.6
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
	 * Enter a parse tree produced by the {@code question}
	 * labeled alternative in {@link QLParser#section}.
	 * @param ctx the parse tree
	 */
	void enterQuestion(QLParser.QuestionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code question}
	 * labeled alternative in {@link QLParser#section}.
	 * @param ctx the parse tree
	 */
	void exitQuestion(QLParser.QuestionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expressionQuestion}
	 * labeled alternative in {@link QLParser#section}.
	 * @param ctx the parse tree
	 */
	void enterExpressionQuestion(QLParser.ExpressionQuestionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expressionQuestion}
	 * labeled alternative in {@link QLParser#section}.
	 * @param ctx the parse tree
	 */
	void exitExpressionQuestion(QLParser.ExpressionQuestionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifStatement}
	 * labeled alternative in {@link QLParser#section}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(QLParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifStatement}
	 * labeled alternative in {@link QLParser#section}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(QLParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolType}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 */
	void enterBoolType(QLParser.BoolTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolType}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 */
	void exitBoolType(QLParser.BoolTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intType}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 */
	void enterIntType(QLParser.IntTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intType}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 */
	void exitIntType(QLParser.IntTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code moneyType}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 */
	void enterMoneyType(QLParser.MoneyTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code moneyType}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 */
	void exitMoneyType(QLParser.MoneyTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringType}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 */
	void enterStringType(QLParser.StringTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringType}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 */
	void exitStringType(QLParser.StringTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code orExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterOrExpression(QLParser.OrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code orExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitOrExpression(QLParser.OrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAndExpression(QLParser.AndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAndExpression(QLParser.AndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterStringExpression(QLParser.StringExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitStringExpression(QLParser.StringExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBoolExpression(QLParser.BoolExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBoolExpression(QLParser.BoolExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addSubExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAddSubExpression(QLParser.AddSubExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addSubExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAddSubExpression(QLParser.AddSubExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIntExpression(QLParser.IntExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIntExpression(QLParser.IntExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code moneyExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMoneyExpression(QLParser.MoneyExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code moneyExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMoneyExpression(QLParser.MoneyExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code identifierExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierExpression(QLParser.IdentifierExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code identifierExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierExpression(QLParser.IdentifierExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code equalityExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpression(QLParser.EqualityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code equalityExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpression(QLParser.EqualityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenthesisExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParenthesisExpression(QLParser.ParenthesisExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenthesisExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParenthesisExpression(QLParser.ParenthesisExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multDivExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMultDivExpression(QLParser.MultDivExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multDivExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMultDivExpression(QLParser.MultDivExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression(QLParser.UnaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression(QLParser.UnaryExpressionContext ctx);
}