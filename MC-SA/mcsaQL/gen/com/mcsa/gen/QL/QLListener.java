// Generated from /Users/matt/Repos/myriad-ql/MC-SA/mcsaQL/grammars/QL.g4 by ANTLR 4.6
package com.mcsa.gen.QL;

package org.uva.sea.ql.parser.antlr;
import org.uva.sea.ql.ast.expr.*;
import org.uva.sea.ql.ast.stat.*;
import org.uva.sea.ql.ast.form.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QLParser}.
 */
public interface QLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QLParser#unExpr}.
	 * @param ctx the parse tree
	 */
	void enterUnExpr(QLParser.UnExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#unExpr}.
	 * @param ctx the parse tree
	 */
	void exitUnExpr(QLParser.UnExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#mulExpr}.
	 * @param ctx the parse tree
	 */
	void enterMulExpr(QLParser.MulExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#mulExpr}.
	 * @param ctx the parse tree
	 */
	void exitMulExpr(QLParser.MulExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#addExpr}.
	 * @param ctx the parse tree
	 */
	void enterAddExpr(QLParser.AddExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#addExpr}.
	 * @param ctx the parse tree
	 */
	void exitAddExpr(QLParser.AddExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#relExpr}.
	 * @param ctx the parse tree
	 */
	void enterRelExpr(QLParser.RelExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#relExpr}.
	 * @param ctx the parse tree
	 */
	void exitRelExpr(QLParser.RelExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(QLParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(QLParser.AndExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#orExpr}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr(QLParser.OrExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#orExpr}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr(QLParser.OrExprContext ctx);
}