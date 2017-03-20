package org.lemonade.exceptions;

import java.util.List;

public class InvalidFormException extends Exception {

    private List<String> formErrors;

    public InvalidFormException(List<String> formErrors) {
        this.formErrors = formErrors;
    }

    public List<String> getFormErrors() {
        return formErrors;
    }

}
