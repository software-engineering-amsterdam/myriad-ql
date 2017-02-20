package org.ql.typechecker;

import org.ql.ast.Node;
import org.ql.typechecker.messages.Message;

import java.util.ArrayList;
import java.util.List;

public class TypeChecker {

    List<Message> errorMessages = new ArrayList<>();

    public boolean checkTypes(Node ast) {
        TypeCheckerVisitor typeCheckerVisitor = new TypeCheckerVisitor();
        return true;
    }
}
