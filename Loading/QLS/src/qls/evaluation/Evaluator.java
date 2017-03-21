package qls.evaluation;

import java.util.HashMap;
import java.util.Map;

import QL.ReferenceTable;
import QL.ast.TypeVisitor;
import QL.ast.type.BooleanType;
import QL.ast.type.IntegerType;
import QL.ast.type.StringType;
import QL.ast.type.Type;
import QL.ast.type.UnknownType;
import QL.ui.Environment;
import QL.ui.Notifier;
import QL.ui.Style;
import QL.ui.StyleTable;
import QL.ui.field.Field;
import qls.ast.DefaultWidget;
import qls.ast.Page;
import qls.ast.PageWithDefault;
import qls.ast.Question;
import qls.ast.QuestionWithWidget;
import qls.ast.Section;
import qls.ast.SectionWithDefault;
import qls.ast.Stylesheet;
import qls.ast.StylesheetVisitor;
import qls.ast.WidgetVisitor;
import qls.ast.widget.Checkbox;
import qls.ast.widget.Radio;
import qls.ast.widget.Spinbox;

public class Evaluator implements StylesheetVisitor, WidgetVisitor {
	
//	private StyleTable styles;
//	private final ReferenceTable referenceTable;
	private Environment environment;
	private Notifier notifier;
	private Map<Type, Style> defaultStyles;
	
//	public Evaluator(ReferenceTable referenceTable) {
//		this.referenceTable = referenceTable;
//		this.defaultStyles = new HashMap<>();
//	}
	
	public Evaluator(Environment environment, Notifier notifier) {
		this.environment = environment;
		this.notifier = notifier;
		this.defaultStyles = new HashMap<>();
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
	}
	
	@Override
	public void visit(PageWithDefault page) {
		
		for (DefaultWidget defaultStyle : page.getDefaultWidgets()) {
			
		}
		for (Section section : page.getSections()) {
			section.accept(this);
		}
		
		
	}

	@Override
	public void visit(Section section) {
		for (Question question : section.getQuestions()) {
			question.accept(this);
		}		
	}
	
	@Override
	public void visit(SectionWithDefault section) {
		// TODO Auto-generated method stub
		
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
