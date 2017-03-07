package org.uva.taxfree.main;

import org.uva.taxfree.gui.MessageList;
import org.uva.taxfree.model.environment.Environment;

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

    // showMessages()

    public boolean validSemantics() {
        return !getSemanticErrors().fatalError();
    }

    public boolean hasMessages() {
        return !getSemanticErrors().isEmpty();
    }

    // kan naar main
    public MessageList getSemanticErrors() {
        MessageList semanticsMessages = new MessageList();
        mEnvironment.getDuplicateDeclarationErrors(semanticsMessages);
        mEnvironment.getDuplicateLabelErrors(semanticsMessages);
        mEnvironment.getUndefinedDeclarationErrors(semanticsMessages);
        mEnvironment.getCyclicDependencyErrors(semanticsMessages);
        if (!semanticsMessages.fatalError()) {
            mEnvironment.getConditionErrors(semanticsMessages);
        }
        return semanticsMessages;
    }
}
