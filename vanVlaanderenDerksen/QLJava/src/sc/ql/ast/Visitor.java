package sc.ql.ast;

import sc.ql.antlr.QLBaseVisitor;
import sc.ql.antlr.QLParser;
import sc.ql.model.*;

public class Visitor extends QLBaseVisitor<Object> {
	
	@Override 
	public Form visitForm(QLParser.FormContext ctx) {
		return new Form();
	}

	@Override 
	public Question visitQuestion(QLParser.QuestionContext ctx) {
		return new Question();
	}

	@Override 
	public CalcQuestion visitCalcQuestion(QLParser.CalcQuestionContext ctx) {
		return new CalcQuestion();
	}

	@Override 
	public IfStatement visitIf_statement(QLParser.If_statementContext ctx) {
		return new IfStatement();
	}

	@Override 
	public ConditionalBlock visitConditional_block(QLParser.Conditional_blockContext ctx) {
		return new ConditionalBlock();
	}

	@Override 
	public ID visitIdAtom(QLParser.IdAtomContext ctx) {
		return new ID();
	}

	@Override 
	public OpExpression visitOpExpr(QLParser.OpExprContext ctx) {
		return new OpExpression();
	}

	@Override 
	public IntExpression visitIntAtom(QLParser.IntAtomContext ctx) {
		return new IntExpression();
	}
	
	@Override 
	public StrExpression visitStrAtom(QLParser.StrAtomContext ctx) {
		return new StrExpression);
	}

	@Override 
	public BoolExpression visitBoolAtom(QLParser.BoolAtomContext ctx) {
		return new BoolExpression();
	}

	@Override 
	public BoolExpression visitRelExpr(QLParser.RelExprContext ctx) {
		return new BoolExpression();
	}

	@Override 
	public BoolExpression visitBoolExpr(QLParser.BoolExprContext ctx) {
		return new BoolExpression();
	}

	@Override 
	public Expression visitParenExpr(QLParser.ParenExprContext ctx) {
		return new Expression();
	}	
}