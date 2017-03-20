/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/semanticchecker/messagehandling/MessageData.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
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

    public Boolean containsNoErrors() {
        return !(errors.size() > 0);
    }

    public Boolean containsNoWarnings() {
        return !(warnings.size() > 0);
    }
}
