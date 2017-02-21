package sc.ql.ast;

import java.util.ArrayList;
import java.util.List;

import sc.ql.antlr.QLBaseVisitor;
import sc.ql.antlr.QLParser;
import sc.ql.model.*;
import sc.ql.model.Expressions.*;
import sc.ql.model.FormElements.*;

public class AstVisitor extends QLBaseVisitor<Node> {
	
	@Override 
	public Node visitForm(QLParser.FormContext ctx) {
		List<FormElement> form_elements = new ArrayList<FormElement>();

		for(QLParser.Form_elementContext form_element : ctx.form_element()) {
			form_elements.add((FormElement) visit(form_element));
		}
		
		return new Form(form_elements);
	}

	@Override 
	public Node visitQuestion(QLParser.QuestionContext ctx) {
		String question = ctx.STR().toString();
		ID id 			= new ID(ctx.ID().toString());
		Atom type 		= Atom.valueOf(ctx.TYPE().toString());

		return new Question(question, id, type);
	}

	@Override 
	public Node visitCalcQuestion(QLParser.CalcQuestionContext ctx) {
		String question 		= ctx.STR().toString();
		ID id 					= new ID(ctx.ID().toString());
		Atom type 				= Atom.valueOf(ctx.TYPE().toString());
		Expression expression	= (Expression) visit(ctx.expression());
		
		return new CalcQuestion(question, id, type, expression);
	}

	@Override 
	public Node visitIf_statement(QLParser.If_statementContext ctx) {
		return new IfStatement();
	}

	@Override 
	public Node visitConditional_block(QLParser.Conditional_blockContext ctx) {
		return new ConditionalBlock();
	}
	
	@Override 
	public Node visitRelExpr(QLParser.RelExprContext ctx) {
		return new BoolExpression();
	}

	@Override 
	public Node visitBoolExpr(QLParser.BoolExprContext ctx) {
		return new BoolExpression();
	}

	@Override 
	public Node visitParenExpr(QLParser.ParenExprContext ctx) {
		return new Expression();
	}	
	
	@Override 
	public Node visitOpExpr(QLParser.OpExprContext ctx) {
		return new OpExpression();
	}

	@Override 
	public Node visitIdAtom(QLParser.IdAtomContext ctx) {
		return new ID("test");
	}

	@Override 
	public Node visitIntAtom(QLParser.IntAtomContext ctx) {
		return Atom.INTEGER;
	}
	
	@Override 
	public Node visitStrAtom(QLParser.StrAtomContext ctx) {
		return Atom.TEXT;
	}

	@Override 
	public Node visitBoolAtom(QLParser.BoolAtomContext ctx) {
		return Atom.BOOLEAN;
	}
}