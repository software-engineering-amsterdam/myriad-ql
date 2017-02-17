package checkSemantics;

import ASTnodes.Form;
import ASTnodes.types.Type;
import checkSemantics.messageHandling.MessageData;
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

        new StatementCheck(ast, identifierMap, messageLists);

        new TypeChecker(ast, identifierMap, messageLists);

        if (messageLists.getErrors().size() > 0) {
            List<ErrorHandler> errors = messageLists.getErrors();

            for( ErrorHandler error : errors) {
                System.out.println(error.getMessage());
            }
        }

        if (messageLists.getWarnings().size() > 0) {
            List<WarningHandler> warnings = messageLists.getWarnings();

            for( WarningHandler warning : warnings) {
                System.out.println(warning.getMessage());
            }
        }

    }
}
