package sc.ql.gui;

import java.awt.Component;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import sc.ql.gui.values.*;
import sc.ql.model.Form;
import sc.ql.model.FormElement;
import sc.ql.model.expressions.*;
import sc.ql.model.expressions.arithmetical.*;
import sc.ql.model.expressions.literals.*;
import sc.ql.model.expressions.logical.*;
import sc.ql.model.form_elements.*;
import sc.ql.model.visitors.*;

public class BuildComponents implements FormVisitor<JPanel>, FormElementVisitor<JPanel>, ExpressionVisitor<Value> {
	private JPanel panel;
	private Map<String, Value> fieldValues;
	
	public BuildComponents(JPanel jPanel, Map<String, Value> fieldValues) {
		this.panel = jPanel;
		this.fieldValues = fieldValues;
	}
	
	@Override
	public JPanel visit(Form form) {
		for(FormElement formElement : form.getFormElements()) {
			panel.add(formElement.accept(this), "span");
		}
		
		return panel;
	}
	
	@Override
	public JPanel visit(IfThenStatement statement) {
		Boolean condition = true; //statement.getCondition().accept(this);
		
		JPanel statementPanel = new JPanel();
		statementPanel.setLayout(new MigLayout());
		
		JPanel thenPanel = new JPanel();
		thenPanel.setLayout(new MigLayout());
		
		thenPanel.setVisible(condition);
		
		for(FormElement formElement : statement.getThenBody()) {
			thenPanel.add(formElement.accept(this), "span");
		}
		
		statementPanel.add(thenPanel);
		
		return statementPanel;
	}
	
	@Override
	public JPanel visit(IfThenElseStatement statement) {
		Boolean condition = true; //statement.getCondition().accept(this);
		
		JPanel statementPanel = new JPanel();
		statementPanel.setLayout(new MigLayout());
		
		JPanel thenPanel = new JPanel();
		thenPanel.setLayout(new MigLayout());
		JPanel elsePanel = new JPanel();
		elsePanel.setLayout(new MigLayout());
		
		thenPanel.setVisible(condition);
		elsePanel.setVisible(!condition);
		
		for(FormElement formElement : statement.getThenBody()) {
			thenPanel.add(formElement.accept(this), "span");
		}

		for(FormElement formElement : statement.getElseBody()) {
			elsePanel.add(formElement.accept(this), "span");
		}
		
		statementPanel.add(thenPanel);
		statementPanel.add(elsePanel);
		
		return statementPanel;
	}
	
	@Override
	public JPanel visit(Question question) {	
		JPanel questionPanel = new JPanel();
		
		JLabel label = new JLabel(question.getLabel());
		questionPanel.add(label);
		
		Component widgetComponent = question.getType().getWidget();
		questionPanel.add(widgetComponent);
		
		return questionPanel;
	}
	
	@Override
	public JPanel visit(CalculatedQuestion question) {
		JPanel questionPanel = new JPanel();
		
		JLabel label = new JLabel(question.getLabel());
		questionPanel.add(label);
		
		Component widgetComponent = question.getType().getWidget();
		questionPanel.add(widgetComponent);
		
		return questionPanel;
	}

	@Override
	public Value visit(BinaryExpression expression) {
		return null;
	}

	@Override
	public Value visit(NotExpression expression) {
		Value value = expression.getExpression().accept(this);
		
		return value.negate();
	}

	@Override
	public Value visit(Add expression) {
		Value value_left = expression.getLeft().accept(this);
		Value value_right = expression.getRight().accept(this);
		
		return value_left.add(value_right);
	}

	@Override
	public Value visit(Divide expression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value visit(Multiply expression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value visit(Substract expression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value visit(BooleanLiteral expression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value visit(IdLiteral expression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value visit(IntegerLiteral expression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value visit(MoneyLiteral expression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value visit(StringLiteral expression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value visit(And expression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value visit(Equals expression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value visit(EqualsNot expression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value visit(GreaterThen expression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value visit(GreaterThenEqual expression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value visit(LessThen expression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value visit(LessThenEqual expression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value visit(Or expression) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
