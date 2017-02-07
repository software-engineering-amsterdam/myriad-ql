// Generated from Hello.g4 by ANTLR 4.6
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HelloParser}.
 */
public interface HelloListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HelloParser#root}.
	 * @param ctx the parse tree
	 */
	void enterRoot(HelloParser.RootContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#root}.
	 * @param ctx the parse tree
	 */
	void exitRoot(HelloParser.RootContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(HelloParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(HelloParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#question}.
	 * @param ctx the parse tree
	 */
	void enterQuestion(HelloParser.QuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#question}.
	 * @param ctx the parse tree
	 */
	void exitQuestion(HelloParser.QuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(HelloParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(HelloParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#computed_question}.
	 * @param ctx the parse tree
	 */
	void enterComputed_question(HelloParser.Computed_questionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#computed_question}.
	 * @param ctx the parse tree
	 */
	void exitComputed_question(HelloParser.Computed_questionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(HelloParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(HelloParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(HelloParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(HelloParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(HelloParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(HelloParser.AtomContext ctx);
}