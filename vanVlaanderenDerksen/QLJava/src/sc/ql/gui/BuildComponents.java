package sc.ql.gui;

import java.awt.Component;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import sc.ql.gui.values.*;
import sc.ql.gui.widgets.Widget;
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
	private Map<String, Value> questionValues;
	
	public BuildComponents(JPanel jPanel, Map<String, Value> questionValues) {
		this.panel = jPanel;
		this.questionValues = questionValues;
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
		Value condition = statement.getCondition().accept(this);
		Boolean visible = !condition.isEmptyValue();
		
		JPanel statementPanel = new JPanel();
		statementPanel.setLayout(new MigLayout());
		
		JPanel thenPanel = new JPanel();
		thenPanel.setLayout(new MigLayout());
		
		thenPanel.setVisible(visible);
		
		for(FormElement formElement : statement.getThenBody()) {
			thenPanel.add(formElement.accept(this), "span");
		}
		
		statementPanel.add(thenPanel);
		
		return statementPanel;
	}
	
	@Override
	public JPanel visit(IfThenElseStatement statement) {
		Value condition = statement.getCondition().accept(this);
		Boolean visible = !condition.isEmptyValue();
		
		JPanel statementPanel = new JPanel();
		statementPanel.setLayout(new MigLayout());
		
		JPanel thenPanel = new JPanel();
		thenPanel.setLayout(new MigLayout());
		JPanel elsePanel = new JPanel();
		elsePanel.setLayout(new MigLayout());
		
		thenPanel.setVisible(visible);
		elsePanel.setVisible(!visible);
		
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
		
		Widget widgetComponent = question.getType().getWidget(this.panel);
		questionPanel.add(widgetComponent.getComponent());
		
		String questionId = question.getId();
		Value value = questionValues.containsKey(questionId) ? questionValues.get(questionId) : new EmptyValue();
		questionValues.put(questionId, value);
		
		return questionPanel;
	}
	
	@Override
	public JPanel visit(CalculatedQuestion question) {
		JPanel questionPanel = new JPanel();
		
		JLabel label = new JLabel(question.getLabel());
		questionPanel.add(label);
		
		Widget widgetComponent = question.getType().getWidget(this.panel);
		questionPanel.add(widgetComponent.getComponent());
		
		Value value = question.getExpression().accept(this);
		questionValues.put(question.getId(), value);
		
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
		Value value_left = expression.getLeft().accept(this);
		Value value_right = expression.getRight().accept(this);
		
		return value_left.divide(value_right);
	}

	@Override
	public Value visit(Multiply expression) {
		Value value_left = expression.getLeft().accept(this);
		Value value_right = expression.getRight().accept(this);
		
		return value_left.multiply(value_right);
	}

	@Override
	public Value visit(Substract expression) {
		Value value_left = expression.getLeft().accept(this);
		Value value_right = expression.getRight().accept(this);
		
		return value_left.subtract(value_right);
	}

	@Override
	public Value visit(BooleanLiteral expression) {
		return new BooleanValue(expression.getValue());
	}

	@Override
	public Value visit(IdLiteral expression) {
		String questionId = expression.getValue();
		Value value = questionValues.containsKey(questionId) ? questionValues.get(questionId) : new EmptyValue();
		return value;
	}

	@Override
	public Value visit(IntegerLiteral expression) {
		return new IntegerValue(expression.getValue());
	}

	@Override
	public Value visit(MoneyLiteral expression) {
		return new MoneyValue(expression.getValue());
	}

	@Override
	public Value visit(StringLiteral expression) {
		return new StringValue(expression.getValue());
	}

	@Override
	public Value visit(And expression) {
		Value value_left = expression.getLeft().accept(this);
		Value value_right = expression.getRight().accept(this);
		
		return value_left.and(value_right);
	}
	
	@Override
	public Value visit(Or expression) {
		Value value_left = expression.getLeft().accept(this);
		Value value_right = expression.getRight().accept(this);
		
		return value_left.or(value_right);
	}

	@Override
	public Value visit(Equals expression) {
		Value value_left = expression.getLeft().accept(this);
		Value value_right = expression.getRight().accept(this);
		
		return value_left.equals(value_right);
	}

	@Override
	public Value visit(EqualsNot expression) {
		Value value_left = expression.getLeft().accept(this);
		Value value_right = expression.getRight().accept(this);
		
		return value_left.equalsNot(value_right);
	}

	@Override
	public Value visit(GreaterThen expression) {
		Value value_left = expression.getLeft().accept(this);
		Value value_right = expression.getRight().accept(this);
		
		return value_left.greaterThen(value_right);
	}

	@Override
	public Value visit(GreaterThenEqual expression) {
		Value value_left = expression.getLeft().accept(this);
		Value value_right = expression.getRight().accept(this);
		
		return value_left.greaterThenEqual(value_right);
	}

	@Override
	public Value visit(LessThen expression) {
		Value value_left = expression.getLeft().accept(this);
		Value value_right = expression.getRight().accept(this);
		
		return value_left.lessThen(value_right);
	}

	@Override
	public Value visit(LessThenEqual expression) {
		Value value_left = expression.getLeft().accept(this);
		Value value_right = expression.getRight().accept(this);
		
		return value_left.lessThenEqual(value_right);
	}
	
}
