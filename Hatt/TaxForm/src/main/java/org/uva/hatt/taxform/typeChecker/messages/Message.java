package org.uva.hatt.taxform.typeChecker.messages;

import org.uva.hatt.taxform.typeChecker.messages.warning.Warning;
import org.uva.hatt.taxform.typeChecker.messages.error.Error;

import java.util.ArrayList;
import java.util.List;

public class Message {
    private List<Warning> warnings;
    private List<Error> errors;

    public Message(){
        warnings = new ArrayList<>();
        errors = new ArrayList<>();
    }

    public void addWarning(Warning warning){
        warnings.add(warning);
    }

    public void addError(Error error) {
        errors.add(error);
    }

    public List<Warning> getWarnings(){
        return warnings;
    }

    public List<Error> getErrors() {
        return errors;
    }
}
