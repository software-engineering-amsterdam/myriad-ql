package sc.ql.model.types;

import javax.swing.JPanel;

import sc.ql.gui.BuildComponents;
import sc.ql.gui.values.Value;
import sc.ql.gui.widgets.IntegerWidget;
import sc.ql.gui.widgets.Widget;

public class IntegerType extends Type {

	@Override
	public String toString() {
		return "Integer";
	}
	
	@Override
	public Boolean isNumericType() {
		return true;
	}
	
	@Override
	public Boolean isCompatibleWith(Type type) {
		return type.isCompatibleWith(this);
	}
	
	@Override
	public Boolean isCompatibleWith(IntegerType type) {
		return true;
	}
	
	@Override
	public Widget getWidget(BuildComponents buildComponents, String questionId, Value value) {
		return new IntegerWidget(buildComponents, questionId, value);
	}
	
}
