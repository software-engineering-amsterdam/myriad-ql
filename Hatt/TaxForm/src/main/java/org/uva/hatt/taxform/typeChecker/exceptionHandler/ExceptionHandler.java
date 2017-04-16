package org.uva.hatt.taxform.typeChecker.exceptionHandler;

import org.uva.hatt.taxform.typeChecker.exceptionHandler.warning.Warning;
import org.uva.hatt.taxform.typeChecker.exceptionHandler.error.Error;

import java.util.ArrayList;
import java.util.List;

public class ExceptionHandler {
    private List<Warning> warnings;
    private List<Error> errors;

    public ExceptionHandler(){
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
