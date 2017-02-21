package sc.ql.ast;

import java.util.ArrayList;
import java.util.List;

import sc.ql.antlr.QLBaseVisitor;
import sc.ql.antlr.QLParser;
import sc.ql.model.*;
import sc.ql.model.Atom;
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
		String question 		= ctx.STR().toString();
		Atom<String> id 		= new Atom<String>(Atom.Type.ID, ctx.ID().toString());
		Atom.Type type 			= Atom.Type.valueOf(ctx.TYPE().toString());
		Expression expression	= null;//new Expression();
		
		return new Question(question, id, type, expression);
	}

	@Override 
	public Node visitIf_statement(QLParser.If_statementContext ctx) {
		List<ConditionalBlock> conditional_blocks = new ArrayList<ConditionalBlock>();
		for(QLParser.Conditional_blockContext conditional_block : ctx.conditional_block()) {
			conditional_blocks.add((ConditionalBlock) visit(conditional_block));
		}
		
		List<FormElement> form_elements = new ArrayList<FormElement>();
		for(QLParser.Form_elementContext form_element : ctx.form_element()) {
			form_elements.add((FormElement) visit(form_element));
		}
		
		return new IfStatement(conditional_blocks, form_elements);
	}

	@Override 
	public Node visitConditional_block(QLParser.Conditional_blockContext ctx) {
		Expression expression = (Expression) visit(ctx.expression());
		
		List<FormElement> form_elements = new ArrayList<FormElement>();
		for(QLParser.Form_elementContext form_element : ctx.form_element()) {
			form_elements.add((FormElement) visit(form_element));
		}
		
		return new ConditionalBlock(expression, form_elements);
	}
	
	@Override 
	public Node visitRelExpr(QLParser.RelExprContext ctx) {
		//System.out.println(visit(ctx.equals());
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
		System.out.println(visit(ctx.left));
		System.out.println(ctx.op.getText());
		System.out.println(visit(ctx.right));
		
		return new OpExpression();
	}

	@Override 
	public Node visitIdAtom(QLParser.IdAtomContext ctx) {
		Atom.Type type = Atom.Type.ID;
		String value = ctx.atom.getText();
		
		return new Atom<String>(type, value);
	}

	@Override 
	public Node visitIntAtom(QLParser.IntAtomContext ctx) {
		//System.out.println(ctx.getText());
		Atom.Type type = Atom.Type.INTEGER;
		Integer value = Integer.parseInt(ctx.atom.getText());	
		
		return new Atom<Integer>(type, value);
	}

	@Override 
	public Node visitStrAtom(QLParser.StrAtomContext ctx) {
		Atom.Type type = Atom.Type.STRING;
		String value = ctx.atom.getText();
		
		return new Atom<String>(type, value);
	}

	@Override 
	public Node visitBoolAtom(QLParser.BoolAtomContext ctx) {
		Atom.Type type = Atom.Type.BOOLEAN;
		Boolean value = Boolean.valueOf(ctx.atom.getText());
		
		System.out.println(ctx.atom.getText());
		System.out.println(value);
		
		return new Atom<Boolean>(type, value);
	}
}