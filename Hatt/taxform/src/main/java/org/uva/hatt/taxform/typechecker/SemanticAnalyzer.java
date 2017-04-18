package org.uva.hatt.taxform.typechecker;

import org.uva.hatt.taxform.ast.nodes.Form;
import org.uva.hatt.taxform.typechecker.messages.Messages;

public class SemanticAnalyzer {

    private final Messages messages;
    private final TypeChecker typeChecker;
    private final CircularDependencyChecker circularDependencyChecker;

    public SemanticAnalyzer() {
        this.messages = new Messages();
        this.typeChecker = new TypeChecker(messages);
        this.circularDependencyChecker = new CircularDependencyChecker(messages);
    }

    public Messages analyze(Form form) {
        typeChecker.visit(form);
        circularDependencyChecker.visit(form);

        return messages;
    }
}
