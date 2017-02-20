package checkSemantics;

import ASTnodes.Form;
import ASTnodes.types.Type;
import checkSemantics.messageHandling.MessageData;
import checkSemantics.messageHandling.MessageHandler;
import checkSemantics.messageHandling.errors.ErrorHandler;
import checkSemantics.messageHandling.warnings.WarningHandler;

import java.util.HashMap;
import java.util.List;

/**
 * Created by LGGX on 16-Feb-17.
 */
public class CheckSemantics {

    private HashMap identifierMap;
    private MessageData messageLists;

    public CheckSemantics(Form ast) {

        identifierMap = new HashMap<String, Type>();
        messageLists = new MessageData();

        new StatementChecker(ast, identifierMap, messageLists);

        new TypeChecker(ast, identifierMap, messageLists);

        if (messageLists.getMessages().size() > 0) {
            List<MessageHandler> messages = messageLists.getMessages();

            for( MessageHandler message : messages) {
                if (message instanceof ErrorHandler)
                    System.out.println("Error found!");
                System.out.println(message.getMessage());
            }
        }

    }
}
