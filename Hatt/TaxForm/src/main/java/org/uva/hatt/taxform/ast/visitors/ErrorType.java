package org.uva.hatt.taxform.ast.visitors;


public enum ErrorType {
    DUPLICATE_LABEL("Duplicate question labels warning at line {0} - Label: {1}"),
    DUPLICATE_DECLARATION("Duplicate question declarations with different types at line {0} - Declaration: {1}"),
    UNDEFINED_REFERENCE("Reference to undefined question at line {0}-  Identifier: {1}");

    ErrorType(String s) {
    }
}