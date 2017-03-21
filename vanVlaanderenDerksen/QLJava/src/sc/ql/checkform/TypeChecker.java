package sc.ql.checkform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sc.ql.model.Form;
import sc.ql.model.FormElement;
import sc.ql.model.expressions.*;
import sc.ql.model.expressions.arithmetical.*;
import sc.ql.model.expressions.literals.*;
import sc.ql.model.expressions.logical.*;
import sc.ql.model.form_elements.*;
import sc.ql.model.types.*;
import sc.ql.model.visitors.*;

public class TypeChecker implements FormVisitor<List<Message>>, FormElementVisitor<Void>, ExpressionVisitor<Type> {
	private List<Message> messages = new ArrayList<Message>();
	private Map<String, Type> stringTypes = new HashMap<String, Type>();
	private List<String> questionIds = new ArrayList<String>();
	private List<String> labels = new ArrayList<String>();
	private List<String> identifiers = new ArrayList<String>();
	
	public TypeChecker(Map<String, Type> stringTypes) {
		this.stringTypes = stringTypes;
	}
	
	@Override
	public List<Message> visit(Form form) {
		for(FormElement formElement : form.getFormElements()) {
			formElement.accept(this);
		}
		
		return messages;
	}
	
	@Override
	public Void visit(IfThenStatement statement) {
		Type type = statement.getCondition().accept(this);
		
		if (!type.isBooleanType()) {
			messages.add(new Message(Message.MessageType.ERROR, "if-then condition type error", statement.getPosition()));
		}
		
		for(FormElement formElement : statement.getThenBody()) {
			formElement.accept(this);
		}

		return null;
	}
	
	@Override
	public Void visit(IfThenElseStatement statement) {
		Type type = statement.getCondition().accept(this);
		
		if (type != null && !type.isBooleanType()) {
			messages.add(new Message(Message.MessageType.ERROR, "if-then-else condition type error", statement.getPosition()));
		}
		
		for(FormElement formElement : statement.getThenBody()) {
			formElement.accept(this);
		}
		
		for(FormElement formElement : statement.getElseBody()) {
			formElement.accept(this);
		}
		
		return null;
	}
	
	@Override
	public Void visit(Question question) {	
		checkQuestion(question);
		
		return null;
	}
	
	@Override
	public Void visit(CalculatedQuestion question) {
		checkQuestion(question);
		
		Type type = question.getExpression().accept(this);
		
		if (!type.isCompatibleWith(question.getType())) {
			messages.add(new Message(Message.MessageType.ERROR, "question type mismatch", question.getPosition()));
		}
		
		return null;
	}
	
	@Override
	public Type visit(BinaryExpression expression) {
		return checkTypeError(expression, "binary");
	}
	
	@Override
	public Type visit(NotExpression expression) {
		Type type = expression.getExpression().accept(this);
		
		if (!type.isBooleanType()) {
			messages.add(new Message(Message.MessageType.ERROR, "not expression not of type Boolean", expression.getPosition()));
		}
		
		return type;
	}

	@Override
	public Type visit(Add expression) { 
		return  checkNumericError(expression, "add");
	}
	
	@Override
	public Type visit(Divide expression) { 
		return checkNumericError(expression, "divide");
	}
	
	@Override
	public Type visit(Multiply expression) { 
		return checkNumericError(expression, "multiply");
	}
	
	@Override
	public Type visit(Substract expression) { 
		return checkNumericError(expression, "substract");
	}
	
	
	@Override
	public Type visit(BooleanLiteral expression) {
		return new BooleanType();
	}

	@Override
	public Type visit(IdLiteral expression) {
		String identifier = expression.getValue();
		
		if (!questionIds.contains(identifier)) {
			messages.add(new Message(Message.MessageType.ERROR, "identifier is not declared", expression.getPosition()));
		}
		
		Type type = new Type();
		if (this.stringTypes.containsKey(identifier)) {
			type = this.stringTypes.get(identifier);
		}
		
		return type;
	}

	@Override
	public Type visit(IntegerLiteral expression) {
		return new IntegerType();
	}

	@Override
	public Type visit(MoneyLiteral expression) {
		return new MoneyType();
	}

	@Override
	public Type visit(StringLiteral expression) {
		return new StringType();
	}
	

	@Override
	public Type visit(And expression) {
		return checkBooleanError(expression, "and");
	}

	@Override
	public Type visit(Equals expression) {
		return checkTypeError(expression, "equals");
	}

	@Override
	public Type visit(EqualsNot expression) {
		return checkTypeError(expression, "equals-not");
	}

	@Override
	public Type visit(GreaterThen expression) {
		return checkNumericError(expression, "greater-then");
	}

	@Override
	public Type visit(GreaterThenEqual expression) {
		return checkNumericError(expression, "greater-then-equal");
	}

	@Override
	public Type visit(LessThen expression) {
		return checkNumericError(expression, "less-then");
	}

	@Override
	public Type visit(LessThenEqual expression) {
		return checkNumericError(expression, "less-then-equal");
	}

	@Override
	public Type visit(Or expression) {
		return checkBooleanError(expression, "or");
	}
	
	private void checkQuestion(Question question) {
		String label = question.getLabel();
		String identifier = question.getId();
		
		questionIds.add(identifier);
		
		if (labels.contains(label)) {
			messages.add(new Message(Message.MessageType.WARNING, "duplicate label detected", question.getPosition()));
		}
		else {
			labels.add(label);
		}
		
		if (identifiers.contains(identifier)) {
			messages.add(new Message(Message.MessageType.ERROR, "duplicate identifier detected", question.getPosition()));
		}
		else {
			identifiers.add(identifier);
		}
	}
	
	private Type checkTypeError(BinaryExpression expression, String expressionName) {
		Type left_side = expression.getLeft().accept(this);
		Type right_side = expression.getRight().accept(this);
		
		if (!left_side.isCompatibleWith(right_side)) {
			messages.add(new Message(Message.MessageType.ERROR, expressionName+" expression not of the same type", expression.getPosition()));
		}
		
		return left_side;
	}
	
	private Type checkBooleanError(BinaryExpression expression, String expressionName) {
		Type left_side = expression.getLeft().accept(this);
		Type right_side = expression.getRight().accept(this);
		
		if (!left_side.isCompatibleWith(right_side) && left_side.isBooleanType()) {
			messages.add(new Message(Message.MessageType.ERROR, expressionName+" expression not of type Boolean", expression.getPosition()));
		}
		
		return left_side;
	}
	
	private Type checkNumericError(BinaryExpression expression, String expressionName) {
		Type left_side = expression.getLeft().accept(this);
		Type right_side = expression.getRight().accept(this);
		
		if (!left_side.isCompatibleWith(right_side) && left_side.isNumericType()) {
			messages.add(new Message(Message.MessageType.ERROR, expressionName+" expression not of type Numeric", expression.getPosition()));
		}
		
		return left_side;
	}
	
}