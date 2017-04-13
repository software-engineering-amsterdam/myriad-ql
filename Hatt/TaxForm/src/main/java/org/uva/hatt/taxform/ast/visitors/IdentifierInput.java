package org.uva.hatt.taxform.ast.visitors;

import org.uva.hatt.taxform.ast.nodes.types.ValueType;

import java.util.HashSet;
import java.util.Set;

public class IdentifierInput {
    private Set<String> dependencies;
    private ValueType type;

    public IdentifierInput(ValueType type){
        this.type = type;
        this.dependencies = new HashSet<>();
    }

    public ValueType getType(){
        return this.type;
    }

    public Set<String> getDependencies(){
        return this.dependencies;
    }

    public void setDependencies(String dependency){
        this.dependencies.add(dependency);
    }
}
