package qls.ast;

import QL.ui.field.Field;
import qls.ast.widget.Checkbox;
import qls.ast.widget.Radio;
import qls.ast.widget.Spinbox;

public interface WidgetVisitor {

	Field visit(Checkbox checkbox);
	Field visit(Radio radio);
	Field visit(Spinbox spinbox);
	
}
