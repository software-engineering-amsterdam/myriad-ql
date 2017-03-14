/*
 * Software Construction - University of Amsterdam
 *
 * ./src/qls/astnodes/styles/StyleType.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */


package qls.astnodes.styles;

import ql.astnodes.LineNumber;
import ql.astnodes.Node;
import qls.visitorinterfaces.StyleAndWidgetVisitor;

import java.util.Objects;

public abstract class StyleType extends Node{

    private final String name;
    private final String value;

    StyleType(String name, String value, LineNumber lineNumber) {
        super(lineNumber);
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object o){
        if (o == null) {
            return false;
        }

        if (!(o instanceof StyleType)) {
            return false;
        }

        StyleType other = (StyleType) o;
        return Objects.equals(this.name, other.name);
    }

    public abstract <T> T accept(StyleAndWidgetVisitor<T> visitor);
}
