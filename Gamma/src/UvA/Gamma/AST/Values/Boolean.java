package UvA.Gamma.AST.Values;

import UvA.Gamma.AST.ASTNode;

/**
 * Created by Tjarco, 14-02-17.
 */

public class Boolean implements ASTNode {
    private boolean value;

    public Boolean(boolean value){
        this.value = value;
    }

    public Boolean(String value){
        this.value = java.lang.Boolean.valueOf(value);
    }

    public boolean getValue(){
        return value;
    }

    public boolean and(Boolean other){
       return this.value && other.getValue();
    }

    public boolean or(Boolean other){
        return this.value || other.getValue();
    }

    public boolean equals(Boolean other){
        return this.value == other.getValue();
    }

    @Override
    public String toString() {
        return "<Boolean> " + this.value;
    }
}
