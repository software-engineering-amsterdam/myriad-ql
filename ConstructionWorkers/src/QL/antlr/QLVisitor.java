// Generated from C:/Users/vince/Documents/Documenten/School/Jaar 5 (Master Software Engineering)/Software Construction/myriad-ql/ConstructionWorkers/src\QL.QL.g4 by ANTLR 4.6
package QL.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link QLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface QLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link QLParser#form}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForm(QLParser.FormContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simpleQuestion}
	 * labeled alternative in {@link QLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleQuestion(QLParser.SimpleQuestionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code computedQuestion}
	 * labeled alternative in {@link QLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComputedQuestion(QLParser.ComputedQuestionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifStatement}
	 * labeled alternative in {@link QLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(QLParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolType}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolType(QLParser.BoolTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringType}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringType(QLParser.StringTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intType}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntType(QLParser.IntTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code moneyType}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoneyType(QLParser.MoneyTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code orExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpression(QLParser.OrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenthesesExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesesExpression(QLParser.ParenthesesExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code andExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpression(QLParser.AndExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringExpression(QLParser.StringExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolExpression(QLParser.BoolExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addSubExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSubExpression(QLParser.AddSubExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntExpression(QLParser.IntExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code moneyExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoneyExpression(QLParser.MoneyExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code identifierExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierExpression(QLParser.IdentifierExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multDivExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultDivExpression(QLParser.MultDivExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code comparisonExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonExpression(QLParser.ComparisonExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unaryExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpression(QLParser.UnaryExpressionContext ctx);
}