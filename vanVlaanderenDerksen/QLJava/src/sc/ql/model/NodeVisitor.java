package sc.ql.model;

import sc.ql.model.atoms.*;
import sc.ql.model.expressions.*;
import sc.ql.model.form_elements.*;

public interface NodeVisitor<T> {
	public T visit(Question question) throws Exception;
	public T visit(ConditionalBlock conditional_block) throws Exception;
	public T visit(IfStatement if_statement) throws Exception;
	public T visit(NotExpression not_expression) throws Exception;
	public T visit(OpExpression op_expression) throws Exception;
	public T visit(AtomBoolean atom_boolean) throws Exception;
	public T visit(AtomFloat atom_float) throws Exception;
	public T visit(AtomId atom_id) throws Exception;
	public T visit(AtomInteger atom_integer) throws Exception;
	public T visit(AtomMoney atom_money) throws Exception;
	public T visit(AtomString atom_string) throws Exception;
}
