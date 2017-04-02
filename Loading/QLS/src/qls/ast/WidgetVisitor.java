package qls.ast;

import QL.ui.field.Field;
import qls.ast.widget.Checkbox;
import qls.ast.widget.DropDown;
import qls.ast.widget.NumberField;
import qls.ast.widget.Radio;
import qls.ast.widget.Slider;
import qls.ast.widget.Spinbox;
import qls.ast.widget.TextField;

public interface WidgetVisitor {

	Field visit(Checkbox checkbox);
	Field visit(Radio radio);
	Field visit(Spinbox spinbox);
	Field visit(Slider slide);
	Field visit(NumberField numberField);
	Field visit(DropDown dropDown);
	Field visit(TextField textField);
}
