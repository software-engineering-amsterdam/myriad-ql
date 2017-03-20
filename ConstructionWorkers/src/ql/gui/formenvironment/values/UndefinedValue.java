/**
 * UndefinedValue.java.
 */

package ql.gui.formenvironment.values;

public class UndefinedValue extends Value {

    private final String value;

    public UndefinedValue() {
        this.value = "Undefined";
    }

    @Override
    public boolean undefined() {
        return true;
    }

    @Override
    public String getValue() {
        return value;
    }
}
