package org.ql.typechecker;

import org.ql.ast.Form;
import org.ql.typechecker.messages.Message;

import java.util.ArrayList;
import java.util.List;

public class TypeChecker {

    List<Message> errorMessages = new ArrayList<Message>();

    public boolean isValidForm(Form form) {
        TypeCheckerVisitor typeCheckerVisitor = new TypeCheckerVisitor();
        return true;
    }
}
