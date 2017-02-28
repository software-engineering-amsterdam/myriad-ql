/**
 * SemanticChecker.java.
 *
 * TODO: Refactor!
 */

package ql.semanticchecker;

import ql.astnodes.Form;
import ql.astnodes.types.Type;
import ql.gui.formenvironment.ValueData;
import ql.semanticchecker.messagehandling.MessageData;

import java.util.HashMap;
import java.util.Map;

public class SemanticChecker {

    private Map<String, Type> identifierToTypeMap;
    private MessageData messages;
    private IdentifierChecker identifierChecker;
    private TypeChecker typeChecker;

    public SemanticChecker(Form ast, ValueData questionStates) {
        identifierToTypeMap = new HashMap<>();
        messages = new MessageData();
        // TODO: refactor?
        identifierChecker = new IdentifierChecker(ast, identifierToTypeMap, messages, questionStates);
        typeChecker = new TypeChecker(ast, identifierToTypeMap, messages);
    }

    public IdentifierChecker getIDCheck() {
        return identifierChecker;
    }

    public TypeChecker getTypeCheck() {
        return typeChecker;
    }

    public MessageData getMessages() {
        return messages;
    }
}
