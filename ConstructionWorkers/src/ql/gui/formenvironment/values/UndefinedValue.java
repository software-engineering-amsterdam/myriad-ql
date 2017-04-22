/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/gui/evaluation/values/UndefinedValue.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.gui.formenvironment.values;

public class UndefinedValue extends Value {

    private final String value;

    UndefinedValue() {
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
