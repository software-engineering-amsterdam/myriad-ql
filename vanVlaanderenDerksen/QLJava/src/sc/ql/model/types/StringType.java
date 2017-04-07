package sc.ql.model.types;

import javax.swing.JPanel;

import sc.ql.gui.widgets.StringWidget;
import sc.ql.gui.widgets.Widget;

public class StringType extends Type {

	@Override
	public String toString() {
		return "String";
	}
	
	@Override
	public Boolean isStringType() {
		return true;
	}
	
	@Override
	public Boolean isCompatibleWith(Type type) {
		return type.isCompatibleWith(this);
	}
	
	@Override
	public Boolean isCompatibleWith(StringType type) {
		return true;
	}
	
	@Override
	public Widget getWidget(JPanel panel) {
		return new StringWidget(panel);
	}
	
}
