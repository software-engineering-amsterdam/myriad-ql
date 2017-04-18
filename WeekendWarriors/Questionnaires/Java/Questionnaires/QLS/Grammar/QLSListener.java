// Generated from Questionnaires\QLS\Grammar\QLS.g4 by ANTLR 4.6
#pragma warning disable 3021
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QLSParser}.
 */
public interface QLSListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QLSParser#stylesheet}.
	 * @param ctx the parse tree
	 */
	void enterStylesheet(QLSParser.StylesheetContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#stylesheet}.
	 * @param ctx the parse tree
	 */
	void exitStylesheet(QLSParser.StylesheetContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#page}.
	 * @param ctx the parse tree
	 */
	void enterPage(QLSParser.PageContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#page}.
	 * @param ctx the parse tree
	 */
	void exitPage(QLSParser.PageContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#section}.
	 * @param ctx the parse tree
	 */
	void enterSection(QLSParser.SectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#section}.
	 * @param ctx the parse tree
	 */
	void exitSection(QLSParser.SectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void enterWidget(QLSParser.WidgetContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void exitWidget(QLSParser.WidgetContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#question}.
	 * @param ctx the parse tree
	 */
	void enterQuestion(QLSParser.QuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#question}.
	 * @param ctx the parse tree
	 */
	void exitQuestion(QLSParser.QuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#defaultStyle}.
	 * @param ctx the parse tree
	 */
	void enterDefaultStyle(QLSParser.DefaultStyleContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#defaultStyle}.
	 * @param ctx the parse tree
	 */
	void exitDefaultStyle(QLSParser.DefaultStyleContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#cssItem}.
	 * @param ctx the parse tree
	 */
	void enterCssItem(QLSParser.CssItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#cssItem}.
	 * @param ctx the parse tree
	 */
	void exitCssItem(QLSParser.CssItemContext ctx);
}