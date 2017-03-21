package qls.evaluation;

import QL.ast.type.Type;
import QL.ui.Environment;
import QL.ui.Notifier;
import QL.ui.Style;
import QL.ui.field.Field;
import qls.ast.*;
import qls.ast.widget.Checkbox;
import qls.ast.widget.Radio;
import qls.ast.widget.Spinbox;

import java.util.HashMap;
import java.util.Map;

public class Evaluator implements StylesheetVisitor, WidgetVisitor {

	private Environment environment;
	private Notifier notifier;
	private Map<Type, Style> defaultStyle;
	
	public Evaluator(Environment environment, Notifier notifier) {
		this.environment = environment;
		this.notifier = notifier;
		this.defaultStyle = new HashMap<>();
	}
	
	@Override
	public void visit(Stylesheet stylesheet) {
		for (Page page : stylesheet.getPages()) {
			page.accept(this);
		}
	}

	@Override
	public void visit(Page page) {
		for (Section section : page.getSections()) {
			section.accept(this);
		}

		for (DefaultWidget defaultStyle : page.getDefaultWidgets()) {
			// TODO
		}
	}

	@Override
	public void visit(Section section) {
		for (Question question : section.getQuestions()) {
			question.accept(this);
		}		
	}

	@Override
	public void visit(Question question) {
		
//		referenceTable.getType(question.getName()).accept(this);
//		styles.put(question.getName(), new Style())
		
	}

	@Override
	public Field visit(Checkbox checkbox) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Field visit(Radio radio) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Field visit(Spinbox spinbox) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void visit(QuestionWithWidget questionWithDefault) {
		// TODO Auto-generated method stub
		
	}
	
//	@Override
//	public void visit(QuestionWithWidget questionWithDefault) {
//		questionWithDefault.getWidget().accept(this);
//		
//	}
//
//	@Override
//	public Field visit(Checkbox checkbox) {
//		checkbox.accept(this);
//	}
//
//	@Override
//	public Field visit(Radio radio) {
//		radio.accept(this);
//	}
//
//	@Override
//	public Field visit(Spinbox spinbox) {
//		spinbox.accept(this);
//	}





}
