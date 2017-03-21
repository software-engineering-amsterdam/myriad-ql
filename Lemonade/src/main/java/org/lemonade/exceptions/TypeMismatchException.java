package org.lemonade.exceptions;

import java.util.List;

public class TypeMismatchException extends Exception {

    private List<String> typeMismatches;

    public TypeMismatchException(List<String> typeMismatches) {
        this.typeMismatches = typeMismatches;
    }

    public List<String> getTypeMismatches() {
        return typeMismatches;
    }

}
