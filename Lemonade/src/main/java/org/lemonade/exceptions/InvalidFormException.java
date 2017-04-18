package org.lemonade.exceptions;

import java.util.List;
import java.util.stream.Collectors;

import org.lemonade.QLParserErrorListener;

public class InvalidFormException extends Exception {

    private List<QLParserErrorListener.SyntaxErrorItem> formErrors;

    public InvalidFormException(List<QLParserErrorListener.SyntaxErrorItem> formErrors) {
        this.formErrors = formErrors;
    }

    public List<String> getFormErrors() {
        return formErrors.stream()
                .map(QLParserErrorListener.SyntaxErrorItem::toString)
                .collect(Collectors.toList());
    }

}
