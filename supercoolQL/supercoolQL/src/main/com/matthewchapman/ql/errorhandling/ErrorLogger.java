package com.matthewchapman.ql.errorhandling;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by matt on 13/03/2017.
 *
 * Contains a collection of Error and Warning
 */
public class ErrorLogger {

    private final List<Error> errors;
    private final List<Warning> warnings;

    public ErrorLogger() {
        errors = new ArrayList<>();
        warnings = new ArrayList<>();
    }

    public void addError(int line, int column, String id, String message) {
        errors.add(new Error(line, column, id, message));
    }

    public void addWarning(int line, int column, String id, String message) {
        warnings.add(new Warning(line, column, id, message));
    }

    public void addMultipleErrors(ErrorLogger logger) {
        this.errors.addAll(logger.getErrors());
    }

    public void addMultipleWarnings(ErrorLogger logger) {
        this.warnings.addAll(logger.getWarnings());
    }

    public int getErrorNumber() {
        return this.errors.size();
    }

    public int getWarningNumber() {
        return this.warnings.size();
    }

    @Contract(pure = true)
    private List<Error> getErrors() {
        return this.errors;
    }

    @Contract(pure = true)
    private List<Warning> getWarnings() {
        return this.warnings;
    }

    @Override
    public String toString() {
        String result = "";
        Collections.sort(this.errors);

        result = result.concat(this.getErrorsAsString());

        result = result.concat(this.getWarningsAsString());


        return result;
    }

    public String getErrorsAsString() {
        String result = "";
        Collections.sort(this.errors);

        for (Error error : this.errors) {
            result = result.concat(error.toString() + "\n");
        }

        return result;
    }

    public String getWarningsAsString() {
        String result = "";
        Collections.sort(this.warnings);

        for (Error error : this.warnings) {
            result = result.concat(error.toString() + "\n");
        }

        return result;
    }

}
