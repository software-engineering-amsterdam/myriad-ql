package qls.evaluation;

import java.util.List;
import java.util.Stack;

import ql.ui.Environment;
import ql.ui.Notifier;
import ql.ui.field.*;
import ql.value.BoolValue;
import ql.value.IntegerValue;
import ql.value.StringValue;
import qls.ast.DefaultStyle;
import qls.ast.DefaultVisitor;
import qls.ast.DefaultWidget;
import qls.ast.Page;
import qls.ast.Question;
import qls.ast.QuestionWithWidget;
import qls.ast.Section;
import qls.ast.Stylesheet;
import qls.ast.StylesheetVisitor;
import qls.ast.WidgetVisitor;
import qls.ast.widget.CheckBox;
import qls.ast.widget.DropDown;
import qls.ast.widget.NumberField;
import qls.ast.widget.RadioButton;
import qls.ast.widget.Slider;
import qls.ast.widget.SpinBox;
import qls.ast.widget.TextField;

public class Evaluator implements StylesheetVisitor, WidgetVisitor, DefaultVisitor {

	private final Environment environment;
	private final Notifier notifier;
	
	private final Stack<DefaultWidget> defaults;
	private String currentQuestion;
	
	public Evaluator(Environment environment, Notifier notifier) {
		
		this.environment = environment;
		this.notifier = notifier;
		this.defaults = new Stack<>();
		currentQuestion = "";
	}
	
	@Override
	public void visit(Stylesheet stylesheet) {
		for (Page page : stylesheet) {
			page.accept(this);
		}
	}

	@Override
	// TODO combined method for page and section
	public void visit(Page page) {

		addToStack(page.getDefaultWidgets());
		
		for (Section section : page) {	
			section.accept(this);	
		}

		removeFromStack(page.getDefaultWidgets().size());
	}
	

	@Override
	public void visit(Section section) {

		addToStack(section.getDefaultWidgets());
		
		for (Question question : section) {
			question.accept(this);
		}	

		removeFromStack(section.getDefaultWidgets().size());
	}
	
	private void addToStack(List<DefaultWidget> defaultElements) {
		
		for (DefaultWidget def : defaultElements) {
			defaults.push(def);
		}
	}
	
	private void removeFromStack(int nrElements) {
		
		for (int i = 0; i < nrElements; ++i) {
			defaults.pop();
		}
	}
	
	@Override
	public void visit(Question question) {
		saveQuestionStyle(question.getName());
	}

	@Override 
	public void visit(QuestionWithWidget question) {
				
		saveQuestionStyle(question.getName());
		environment.addField(question.getName(), question.getWidget().accept(this));
	}
	
	private void saveQuestionStyle(String question) {
		
		currentQuestion = question;
		
		for (DefaultWidget def : defaults) {
			def.accept(this);
			if (environment.isStyled(question)) {
				break;
			}
		}
	}

	@Override
	public Field visit(CheckBox checkbox) {
		return new CheckBoxF(currentQuestion, notifier, new BoolValue());
	}

	@Override
	public Field visit(RadioButton radio) {
		return new RadioButtonF(currentQuestion, radio.getTrueText(), radio.getFalseText(), notifier, new BoolValue());
	}

	@Override
	public Field visit(SpinBox spinbox) {
		return new SpinBoxF(currentQuestion, notifier, new IntegerValue());
	}
	
	@Override 
	public Field visit(Slider slide) {
		return new SliderF(currentQuestion, notifier, new IntegerValue());
	}
	
	@Override
	public Field visit(DropDown dropDown) {
		return new DropDownF(currentQuestion, dropDown.getTrueText(), dropDown.getFalseText(), notifier, new BoolValue());
	}

	@Override
	public Field visit(NumberField numberField) {
		return new NumberF(currentQuestion, notifier, new IntegerValue());
	}

	@Override
	public Field visit(TextField textField) {
		return new TextF(currentQuestion, notifier, new StringValue());
	}
	
	@Override
	public void visit(DefaultStyle defaultStyle) {		
		
		if (environment.getType(currentQuestion).equals(defaultStyle.getType())) {
			environment.addStyle(currentQuestion, defaultStyle.getStyle());
		}	
	}

	@Override
	public void visit(DefaultWidget defaultWidget) {
		
		if (environment.getType(currentQuestion).equals(defaultWidget.getType())) {
			environment.addField(currentQuestion, defaultWidget.getWidget().accept(this));
		}
	}

}
