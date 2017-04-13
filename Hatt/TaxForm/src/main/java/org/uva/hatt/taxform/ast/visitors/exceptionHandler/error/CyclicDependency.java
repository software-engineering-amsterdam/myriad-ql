package org.uva.hatt.taxform.ast.visitors.exceptionHandler.error;

public class CyclicDependency extends Error{

    public CyclicDependency(int lineNumber, String dependency1, String dependency2){
        super("Cyclic dependency at line " + lineNumber + ": Identifier '" + dependency1 + "' is dependent on '" + dependency2 +"'");

    }
}
