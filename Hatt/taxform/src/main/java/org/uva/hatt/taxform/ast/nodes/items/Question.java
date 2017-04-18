package org.uva.hatt.taxform.ast.nodes.items;

import org.uva.hatt.taxform.ast.nodes.types.ValueType;

public class Question extends Item{

    private final String label;
    private final String identifier;
    private final ValueType type;

    public Question(int lineNumber, String label, String identifier, ValueType type) {
        super(lineNumber);
        this.label = label;
        this.identifier = identifier;
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public String getIdentifier() {
        return identifier;
    }

    public ValueType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Question{" +
                "label='" + label + '\'' +
                ", identifier='" + identifier + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public <T> T accept(ItemVisitor<T> visitor){
        return visitor.visit(this);
    }
}
