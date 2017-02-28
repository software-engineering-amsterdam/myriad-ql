/**
 * MessageData.java.
 */

package ql.semanticchecker.messagehandling;

import ql.semanticchecker.messagehandling.errors.Error;
import ql.semanticchecker.messagehandling.warnings.Warning;

import java.util.ArrayList;
import java.util.List;

public class MessageData {

    private List<Warning> warnings;
    private List<Error> errors;

    public MessageData() {
        warnings = new ArrayList<>();
        errors = new ArrayList<>();
    }

    public void addWarning(Warning warning) {
        warnings.add(warning);
    }

    public void addError(Error error) {
        errors.add(error);
    }

    public List<Warning> getWarnings() {
        return warnings;
    }

    public List<Error> getErrors() {
        return errors;
    }
}
