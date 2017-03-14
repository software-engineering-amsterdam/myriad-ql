package QL.ast.type;

import QL.ast.TypeVisitor;
import QL.ui.Notifier;
import QL.ui.field.Field;
import QL.ui.field.Text;
import QL.value.StringValue;
import QL.value.Value;

public class StringType extends Type {

	public StringType(int line) {
		super("string", line);
	}
	
	@Override
	public <T> T accept(TypeVisitor<T> v) {
		return v.visit(this);
	}


}
