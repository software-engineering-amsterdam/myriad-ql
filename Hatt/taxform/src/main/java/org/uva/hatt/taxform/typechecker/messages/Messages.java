package org.uva.hatt.taxform.typechecker.messages;

import org.uva.hatt.taxform.typechecker.messages.warning.Warning;
import org.uva.hatt.taxform.typechecker.messages.error.Error;

import java.util.ArrayList;
import java.util.List;

public class Messages {

    private final List<Warning> warnings;
    private final List<Error> errors;

    public Messages(){
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
