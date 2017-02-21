/**
 * MessageData.java.
 */

package lexicalChecker.messageHandling;

import lexicalChecker.messageHandling.errors.ErrorHandler;
import lexicalChecker.messageHandling.warnings.WarningHandler;

import java.util.ArrayList;
import java.util.List;

public class MessageData {

    private List<WarningHandler> warnings;
    private List<ErrorHandler> errors;

    public MessageData() {
        warnings = new ArrayList<>();
        errors = new ArrayList<>();
    }

    public void addWarning(WarningHandler warning) {
        warnings.add(warning);
    }

    public void addError(ErrorHandler error) {
        errors.add(error);
    }

    public List<WarningHandler> getWarnings() {
        return warnings;
    }

    public List<ErrorHandler> getErrors() {
        return errors;
    }
}
