package sc.ql.checkform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sc.ql.model.ConditionalBlock;
import sc.ql.model.NodeVisitor;
import sc.ql.model.atoms.AtomBoolean;
import sc.ql.model.atoms.AtomId;
import sc.ql.model.atoms.AtomInteger;
import sc.ql.model.atoms.AtomMoney;
import sc.ql.model.atoms.AtomString;
import sc.ql.model.expressions.NotExpression;
import sc.ql.model.expressions.OpExpression;
import sc.ql.model.form_elements.IfStatement;
import sc.ql.model.form_elements.Question;
import sc.ql.model.form_elements.Question.Type;

public class DependenciesVisitor implements NodeVisitor<List<AtomId>> {
	//private HashMap<AtomId, ConditionalBlock> dependencies = new ArrayList<Question>();
	
	@Override
	public List<AtomId> visit(Question question) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AtomId> visit(ConditionalBlock conditional_block) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AtomId> visit(IfStatement if_statement) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AtomId> visit(NotExpression not_expression) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AtomId> visit(OpExpression op_expression) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AtomId> visit(AtomBoolean atom_boolean) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AtomId> visit(AtomId atom_id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AtomId> visit(AtomInteger atom_integer) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AtomId> visit(AtomMoney atom_money) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AtomId> visit(AtomString atom_string) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
