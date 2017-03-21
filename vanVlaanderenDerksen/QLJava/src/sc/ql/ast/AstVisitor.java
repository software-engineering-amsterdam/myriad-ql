package sc.ql.ast;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import sc.ql.antlr.QLBaseVisitor;
import sc.ql.antlr.QLParser;
import sc.ql.model.*;
import sc.ql.model.expressions.*;
import sc.ql.model.expressions.arithmetical.*;
import sc.ql.model.expressions.literals.*;
import sc.ql.model.expressions.logical.*;
import sc.ql.model.form_elements.*;
import sc.ql.model.types.*;

public class AstVisitor extends QLBaseVisitor<Node> {
	@Override 
	public Form visitForm(QLParser.FormContext ctx) {
		Form form = new Form(ctx.formlabel.getText(), getFormElements(ctx.formElement()));
		form.setPosition(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());

		return form;
	}

	@Override 
	public Question visitQuestion(QLParser.QuestionContext ctx) {
		String label 	= ctx.label.getText();
		label 			= label.substring(1, label.length()-1); // Remove the surrounding quotes
		IdLiteral id 	= new IdLiteral(ctx.id.getText());
		Type type 		= (Type) visit(ctx.type());	
		
		Question question = new Question(label, id, type);
		question.setPosition(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
		
		return question;
	}
	
	@Override 
	public CalculatedQuestion visitCalculatedQuestion(QLParser.CalculatedQuestionContext ctx) {		
		String label 	= ctx.label.getText();
		label 			= label.substring(1, label.length()-1); // Remove the surrounding quotes
		IdLiteral id 	= new IdLiteral(ctx.id.getText());
		Type type 		= (Type) visit(ctx.type());	
		Expression expression  	= (Expression) visit(ctx.expression());
		
		CalculatedQuestion question = new CalculatedQuestion(label, id, type, expression);
		question.setPosition(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
		
		return question;
	}

	@Override 
	public IfThenStatement visitIfThenStatement(QLParser.IfThenStatementContext ctx) {
		Expression condition 	   = (Expression) visit(ctx.expression());
		List<FormElement> thenBody = getFormElements(ctx.thenBody);
		
		IfThenStatement statement = new IfThenStatement(condition, thenBody);
		statement.setPosition(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
		
		return statement;
	}

	@Override 
	public IfThenElseStatement visitIfThenElseStatement(QLParser.IfThenElseStatementContext ctx) {
		Expression condition 	   = (Expression) visit(ctx.expression());
		List<FormElement> thenBody = getFormElements(ctx.thenBody);
		List<FormElement> elseBody = getFormElements(ctx.elseBody);

		IfThenElseStatement statement = new IfThenElseStatement(condition, thenBody, elseBody);
		statement.setPosition(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
		
		return statement;
	}
	
	@Override 
	public BooleanType visitBooleanType(QLParser.BooleanTypeContext ctx) {
		BooleanType type = new BooleanType();
		type.setPosition(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
		
		return type;
	}
	
	@Override 
	public IntegerType visitIntegerType(QLParser.IntegerTypeContext ctx) {
		IntegerType type = new IntegerType();
		type.setPosition(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
		
		return type;
	}
	
	@Override 
	public MoneyType visitMoneyType(QLParser.MoneyTypeContext ctx) {
		MoneyType type = new MoneyType();
		type.setPosition(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
		
		return type;
	}
	
	@Override 
	public StringType visitStringType(QLParser.StringTypeContext ctx) {
		StringType type = new StringType();
		type.setPosition(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
		
		return type;
	}
	
	@Override 
	public Expression visitParenthesis(QLParser.ParenthesisContext ctx) {
		return (Expression) visit(ctx.expression());
	}
	
	@Override 
	public NotExpression visitNotExpression(QLParser.NotExpressionContext ctx) {
		NotExpression expression = new NotExpression((Expression) visit(ctx.expression()));
		expression.setPosition(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());		

		return expression;
	}
	
	@Override 
	public Multiply visitMultiply(QLParser.MultiplyContext ctx) {
		Multiply expression = new Multiply((Expression) visit(ctx.left), (Expression) visit(ctx.right));
		expression.setPosition(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());		

		return expression;
	}
	
	@Override 
	public Divide visitDivide(QLParser.DivideContext ctx) {
		Divide expression = new Divide((Expression) visit(ctx.left), (Expression) visit(ctx.right));
		expression.setPosition(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());		

		return expression;
	}
	
	@Override 
	public Add visitAdd(QLParser.AddContext ctx) {
		Add expression = new Add((Expression) visit(ctx.left), (Expression) visit(ctx.right));
		expression.setPosition(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());		

		return expression;
	}
	
	@Override 
	public Substract visitSubstract(QLParser.SubstractContext ctx) {
		Substract expression = new Substract((Expression) visit(ctx.left), (Expression) visit(ctx.right));
		expression.setPosition(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());		

		return expression;
	}
	
	@Override 
	public LessThen visitLessThen(QLParser.LessThenContext ctx) {
		LessThen expression = new LessThen((Expression) visit(ctx.left), (Expression) visit(ctx.right));
		expression.setPosition(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());		

		return expression;
	}
	
	@Override 
	public LessThenEqual visitLessThenEqual(QLParser.LessThenEqualContext ctx) {
		LessThenEqual expression = new LessThenEqual((Expression) visit(ctx.left), (Expression) visit(ctx.right));
		expression.setPosition(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());		

		return expression;
	}
	
	@Override 
	public GreaterThen visitGreaterThen(QLParser.GreaterThenContext ctx) {
		GreaterThen expression = new GreaterThen((Expression) visit(ctx.left), (Expression) visit(ctx.right));
		expression.setPosition(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());		

		return expression;
	}
	
	@Override 
	public GreaterThenEqual visitGreaterThenEqual(QLParser.GreaterThenEqualContext ctx) {
		GreaterThenEqual expression = new GreaterThenEqual((Expression) visit(ctx.left), (Expression) visit(ctx.right));
		expression.setPosition(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());		

		return expression;
	}
	
	@Override 
	public Equals visitEquals(QLParser.EqualsContext ctx) {
		Equals expression = new Equals((Expression) visit(ctx.left), (Expression) visit(ctx.right));
		expression.setPosition(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());		

		return expression;
	}
	
	@Override 
	public EqualsNot visitEqualsNot(QLParser.EqualsNotContext ctx) {
		EqualsNot expression = new EqualsNot((Expression) visit(ctx.left), (Expression) visit(ctx.right));
		expression.setPosition(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());		

		return expression;
	}
	
	@Override 
	public And visitAnd(QLParser.AndContext ctx) {
		And expression = new And((Expression) visit(ctx.left), (Expression) visit(ctx.right));
		expression.setPosition(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());		

		return expression;
	}
	
	@Override 
	public Or visitOr(QLParser.OrContext ctx) {
		Or expression = new Or((Expression) visit(ctx.left), (Expression) visit(ctx.right));
		expression.setPosition(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());		

		return expression;
	}

	@Override 
	public IdLiteral visitIdLiteral(QLParser.IdLiteralContext ctx) {
		IdLiteral idLiteral = new IdLiteral(ctx.literal.getText());
		idLiteral.setPosition(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
		
		return idLiteral;
	}

	@Override 
	public IntegerLiteral visitIntegerLiteral(QLParser.IntegerLiteralContext ctx) {
		IntegerLiteral integerLiteral = new IntegerLiteral(Integer.parseInt(ctx.literal.getText()));
		integerLiteral.setPosition(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
		
		return integerLiteral;
	}
	
	@Override 
	public MoneyLiteral visitMoneyLiteral(QLParser.MoneyLiteralContext ctx) {
		MoneyLiteral moneyLiteral = new MoneyLiteral(new BigDecimal(ctx.literal.getText()));
		moneyLiteral.setPosition(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
		
		return moneyLiteral;
	}

	@Override 
	public StringLiteral visitStringLiteral(QLParser.StringLiteralContext ctx) {
		StringLiteral stringLiteral = new StringLiteral(ctx.literal.getText());
		stringLiteral.setPosition(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
		
		return stringLiteral;
	}

	@Override 
	public BooleanLiteral visitBooleanLiteral(QLParser.BooleanLiteralContext ctx) {
		BooleanLiteral booleanLiteral = new BooleanLiteral(Boolean.valueOf(ctx.literal.getText()));
		booleanLiteral.setPosition(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
		
		return booleanLiteral;
	}
	
	private List<FormElement> getFormElements(List<QLParser.FormElementContext> elements) {
		List<FormElement> formElements = new ArrayList<FormElement>();
		for(QLParser.FormElementContext formElement : elements) {
			formElements.add((FormElement) visit(formElement));
		}
		
		return formElements;
	}
}