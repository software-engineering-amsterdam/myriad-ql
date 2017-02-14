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
		
	}

	@Override 
	public ConditionalBlock visitConditional_block(QLParser.Conditional_blockContext ctx) {
		
	}

	@Override 
	public ID visitIdAtom(QLParser.IdAtomContext ctx) {
		
	}

	@Override 
	public OpExpression visitOpExpr(QLParser.OpExprContext ctx) {
		
	}

	@Override 
	public IntExpression visitIntAtom(QLParser.IntAtomContext ctx) {
	
	}
	
	@Override 
	public StrExpression visitStrAtom(QLParser.StrAtomContext ctx) {
		
	}

	@Override 
	public BoolExpression visitBoolAtom(QLParser.BoolAtomContext ctx) {
		
	}

	@Override 
	public BoolExpression visitRelExpr(QLParser.RelExprContext ctx) {
		
	}

	@Override 
	public BoolExpression visitBoolExpr(QLParser.BoolExprContext ctx) {
		
	}

	@Override 
	public Expression visitParenExpr(QLParser.ParenExprContext ctx) {
		
	}	
}