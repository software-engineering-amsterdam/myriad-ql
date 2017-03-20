package com.matthewchapman.ql.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by matt on 13/03/2017.
 */
public class QLErrorLogger {

    private final List<QLError> errors;
    private final List<QLWarning> warnings;

    public QLErrorLogger() {
        errors = new ArrayList<>();
        warnings = new ArrayList<>();
    }

    public void addError(int line, int column, String id, String message) {
        errors.add(new QLError(line, column, id, message));
    }

    public void addWarning(int line, int column, String id, String message) {
        warnings.add(new QLWarning(line, column, id, message));
    }

    public void addMultipleErrors(QLErrorLogger logger) {
        this.errors.addAll(logger.getErrors());
    }

    public void addMultipleWarnings(QLErrorLogger logger) { this.warnings.addAll(logger.getWarnings());}

    public int getErrorNumber() {
        return this.errors.size();
    }

    public int getWarningNumber() { return this.warnings.size(); }

    private List<QLError> getErrors() {
        return this.errors;
    }

    private List<QLWarning> getWarnings() { return this.warnings; }

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

        for (QLError error : this.errors) {
            result = result.concat(error.toString() + "\n");
        }

        return result;
    }

    public String getWarningsAsString() {
        String result = "";
        Collections.sort(this.warnings);

        for (QLError error : this.warnings) {
            result = result.concat(error.toString() + "\n");
        }

        return result;
    }

}
