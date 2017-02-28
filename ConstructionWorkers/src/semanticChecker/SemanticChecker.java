/**
 * SemanticChecker.java.
 *
 * TODO: Refactor!
 */

package semanticChecker;

import ASTnodes.Form;
import ASTnodes.types.Type;
import semanticChecker.messageHandling.MessageData;

import java.util.HashMap;
import java.util.Map;

public class SemanticChecker {

    private Map<String, Type> identifierToTypeMap;
    private MessageData messages;

    public SemanticChecker(Form ast) {
        identifierToTypeMap = new HashMap<>();
        messages = new MessageData();
        // TODO: refactor?
        new StatementChecker(ast, identifierToTypeMap, messages);
        new TypeChecker(ast, identifierToTypeMap, messages);
    }

    public MessageData getMessages() {
        return messages;
    }
}
