package org.uva.hatt.taxform.typechecker;

import org.uva.hatt.taxform.ast.nodes.types.ValueType;

import java.util.HashSet;
import java.util.Set;

class IdentifierInput {
    private final Set<String> dependencies;
    private final ValueType type;

    IdentifierInput(ValueType type){
        this.type = type;
        this.dependencies = new HashSet<>();
    }

    ValueType getType(){
        return this.type;
    }

    public Set<String> getDependencies(){
        return this.dependencies;
    }

    public void setDependencies(String dependency){
        this.dependencies.add(dependency);
    }
}
