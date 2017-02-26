/**
 * SemanticChecker.java.
 *
 * TODO: Refactor!
 */

package semanticChecker;

import ASTnodes.Form;
import ASTnodes.types.Type;
import semanticChecker.formDataStorage.valueData.ValueData;
import semanticChecker.messageHandling.MessageData;

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
