package sc.ql.model.visitors;

import sc.ql.model.expressions.*;
import sc.ql.model.expressions.arithmetical.*;
import sc.ql.model.expressions.literals.*;
import sc.ql.model.expressions.logical.*;

public interface ExpressionVisitor<T> {
	public T visit(BinaryExpression expression);
	public T visit(NotExpression expression);
	
	public T visit(Add expression);
	public T visit(Divide expression);
	public T visit(Multiply expression);
	public T visit(Substract expression);
	
	public T visit(BooleanLiteral expression);
	public T visit(IdLiteral expression);
	public T visit(IntegerLiteral expression);
	public T visit(MoneyLiteral expression);
	public T visit(StringLiteral expression);
	
	public T visit(And expression);
	public T visit(Equals expression);
	public T visit(EqualsNot expression);
	public T visit(GreaterThen expression);
	public T visit(GreaterThenEqual expression);
	public T visit(LessThen expression);
	public T visit(LessThenEqual expression);
	public T visit(Or expression);
}
