package org.uva.hatt.taxform.typeChecker.messages.warning;

public class DuplicateLabel extends Warning{

    public DuplicateLabel(int lineNumber, String message) {
        super("Duplicate question label at line " + lineNumber + ": Label: " + message);
    }
}