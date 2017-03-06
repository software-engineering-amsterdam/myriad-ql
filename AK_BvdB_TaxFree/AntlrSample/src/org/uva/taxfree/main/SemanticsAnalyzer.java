package org.uva.taxfree.main;

import org.uva.taxfree.model.environment.Environment;

import java.util.ArrayList;
import java.util.List;

public class SemanticsAnalyzer {
    private final Environment mEnvironment;

    /*
     * //UNDEFINES - reference to undefined questions
     *   if (nonExists) etc.
     * // DUPLICATE-QUESTIONS - duplicate question declarations with different types
     * WRONGCONDITIONS - conditions that are not of the type boolean // TODO
     * WRONGCONDITIONS - operands of invalid type to operators // TODO
     * IFELSERECURSION - cyclic dependencies between questions // TODO
     * // DUPLICATE-LABELS - duplicate labels (TODO: make it a warning)
     *
     * TODO:
     * - Check | if ("")
     * - Check | if (1)
     */

    public SemanticsAnalyzer(Environment environment) {
        mEnvironment = environment;
    }

    public boolean validSemantics() {
        return getSemanticErrors().isEmpty();
    }

    public List<String> getSemanticErrors() {
        List<String> errorMessages = new ArrayList<>();
        errorMessages.addAll(mEnvironment.getDuplicateDeclarationErrors());
        errorMessages.addAll(mEnvironment.getDuplicateLabelErrors());
        errorMessages.addAll(mEnvironment.getUndefinedDeclarationErrors());
        errorMessages.addAll(mEnvironment.getCyclicDependencyErrors());
        return errorMessages;
    }
}
