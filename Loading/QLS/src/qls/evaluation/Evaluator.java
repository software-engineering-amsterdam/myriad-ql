package qls.evaluation;

import java.util.Stack;

import QL.ui.Environment;
import QL.ui.Notifier;
import QL.ui.field.Check;
import QL.ui.field.DropDownMenu;
import QL.ui.field.Field;
import QL.ui.field.Number;
import QL.ui.field.RadioB;
import QL.ui.field.Slide;
import QL.ui.field.SpinBox;
import QL.ui.field.Text;
import QL.value.BoolValue;
import QL.value.IntegerValue;
import QL.value.StringValue;
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
import qls.ast.widget.Checkbox;
import qls.ast.widget.DropDown;
import qls.ast.widget.NumberField;
import qls.ast.widget.Radio;
import qls.ast.widget.Slider;
import qls.ast.widget.Spinbox;
import qls.ast.widget.TextField;

public class Evaluator implements StylesheetVisitor, WidgetVisitor, DefaultVisitor {

	private Environment environment;
	private Notifier notifier;
	
	private Stack<DefaultWidget> defaults;
	private String currentQuestion;
	
	public Evaluator(Environment environment, Notifier notifier) {
		
		this.environment = environment;
		this.notifier = notifier;
		this.defaults = new Stack<DefaultWidget>();
		currentQuestion = "";
	}
	
	@Override
	public void visit(Stylesheet stylesheet) {
		for (Page page : stylesheet.getPages()) {
			page.accept(this);
		}
	}

	@Override
	// TODO combined method for page and section
	public void visit(Page page) {
		
		// Push on stack
		for (DefaultWidget def : page.getDefaultWidgets()) {
			defaults.push(def);
		}
		
		for (Section section : page.getSections()) {	
			section.accept(this);	
		}
		
		// Pop off stack
		for (int i = 0; i < page.getDefaultWidgets().size(); ++i) {
			defaults.pop();
		}
	}
	

	@Override
	public void visit(Section section) {
		
		// Push on stack
		for (DefaultWidget def : section.getDefaultWidgets()) {
			defaults.push(def);
		}
		
		for (Question question : section.getQuestions()) {
			question.accept(this);
		}	
		
		// Pop off stack
		for (int i = 0; i < section.getDefaultWidgets().size(); ++i) {
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
		
		// Override default widget
		environment.add(question.getName(), question.getWidget().accept(this));	
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
	public Field visit(Checkbox checkbox) {
		return new Check(currentQuestion, notifier, new BoolValue());
	}

	@Override
	public Field visit(Radio radio) {
		return new RadioB(currentQuestion, radio.getTrueText(), radio.getFalseText(), notifier, new BoolValue());
	}

	@Override
	public Field visit(Spinbox spinbox) {
		return new SpinBox(currentQuestion, notifier, new IntegerValue());
	}
	
	@Override 
	public Field visit(Slider slide) {
		return new Slide(currentQuestion, notifier, new IntegerValue());
	}
	
	@Override
	public Field visit(DropDown dropDown) {
		return new DropDownMenu(currentQuestion, dropDown.getTrueText(), dropDown.getFalseText(), notifier, new BoolValue());
	}

	@Override
	public Field visit(NumberField numberField) {
		return new Number(currentQuestion, notifier, new IntegerValue());
	}

	@Override
	public Field visit(TextField textField) {
		return new Text(currentQuestion, notifier, new StringValue());
	}
	
	@Override
	public void visit(DefaultStyle defaultStyle) {		
		
		if (environment.getType(currentQuestion).equals(defaultStyle.getType())) {
			environment.add(currentQuestion, defaultStyle.getStyle());
		}	
	}

	@Override
	public void visit(DefaultWidget defaultWidget) {
		
		if (environment.getType(currentQuestion).equals(defaultWidget.getType())) {
			environment.add(currentQuestion, defaultWidget.getWidget().accept(this));
		}
	}

}
