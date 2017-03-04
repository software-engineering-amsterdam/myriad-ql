package value;

import ast.atom.StringAtom;
import ast.type.StringType;
import ast.type.Type;
import ui.QControl;
import ui.field.Text;

public class StringValue extends Value {
	
	private StringAtom value;
	private String name;
	
	public StringValue(String name, String value) {
		this.value = new StringAtom(value, 0);
		this.name = name;
	}
	
//    @Override
//    public equals(StringValue other) {
//        if (o == this) return true;   //If objects equal, is OK
//        if (o instanceof Point) {
//           Point that = (Point)o;
//           return (x == that.x)  && y == that.y);
//        }
//        return false;
//    }
	
	@Override
	public StringAtom getValue() {
		return value;
	}

	@Override
	public Type getType() {
		return new StringType(0);
	}

	@Override
	public QControl getField() {
		return new QControl(new Text(name));
	}

}
