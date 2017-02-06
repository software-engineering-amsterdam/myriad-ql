// Generated from /Users/matt/Repos/myriad-ql/MC-SA/mcsaQL/grammars/Exp.g4 by ANTLR 4.6
package com.mcsa.gen.Exp;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExpParser}.
 */
public interface ExpListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ExpParser#eval}.
	 * @param ctx the parse tree
	 */
	void enterEval(ExpParser.EvalContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpParser#eval}.
	 * @param ctx the parse tree
	 */
	void exitEval(ExpParser.EvalContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpParser#additionExp}.
	 * @param ctx the parse tree
	 */
	void enterAdditionExp(ExpParser.AdditionExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpParser#additionExp}.
	 * @param ctx the parse tree
	 */
	void exitAdditionExp(ExpParser.AdditionExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpParser#multiplyExp}.
	 * @param ctx the parse tree
	 */
	void enterMultiplyExp(ExpParser.MultiplyExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpParser#multiplyExp}.
	 * @param ctx the parse tree
	 */
	void exitMultiplyExp(ExpParser.MultiplyExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpParser#atomExp}.
	 * @param ctx the parse tree
	 */
	void enterAtomExp(ExpParser.AtomExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpParser#atomExp}.
	 * @param ctx the parse tree
	 */
	void exitAtomExp(ExpParser.AtomExpContext ctx);
}