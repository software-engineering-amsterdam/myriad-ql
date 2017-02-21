/**
 * LexicalChecker.java.
 *
 * TODO: Finish/refactor!
 */

package lexicalChecker;

import ASTnodes.Form;
import ASTnodes.types.Type;
import lexicalChecker.messageHandling.MessageData;
import lexicalChecker.messageHandling.errors.ErrorHandler;
import lexicalChecker.messageHandling.warnings.WarningHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LexicalChecker {

    private Map<String, Type> identifierTypeMap;
    private MessageData messages;

    public LexicalChecker(Form ast) {
        identifierTypeMap = new HashMap<>();
        messages = new MessageData();

        new StatementChecker(ast, identifierTypeMap, messages);
        new TypeChecker(ast, identifierTypeMap, messages);
    }

    public MessageData getMessages() {
        return messages;
    }
}
