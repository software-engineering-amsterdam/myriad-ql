package qls.ast;

import ql.ui.field.Field;
import qls.ast.widget.CheckBox;
import qls.ast.widget.DropDown;
import qls.ast.widget.NumberField;
import qls.ast.widget.RadioButton;
import qls.ast.widget.Slider;
import qls.ast.widget.SpinBox;
import qls.ast.widget.TextField;

public interface WidgetVisitor {

	Field visit(CheckBox checkbox);
	Field visit(RadioButton radio);
	Field visit(SpinBox spinbox);
	Field visit(Slider slide);
	Field visit(NumberField numberField);
	Field visit(DropDown dropDown);
	Field visit(TextField textField);
}
