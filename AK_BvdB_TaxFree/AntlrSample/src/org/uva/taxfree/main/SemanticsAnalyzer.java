package org.uva.taxfree.main;

import org.uva.taxfree.gui.MessageList;
import org.uva.taxfree.model.environment.SymbolTable;
import org.uva.taxfree.model.node.blocks.BlockNode;

public class SemanticsAnalyzer {
    private final BlockNode mAbstractSyntaxTree;
    private final SymbolTable mSymbolTable;

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

    public SemanticsAnalyzer(BlockNode abstractSyntaxTree, SymbolTable symbolTable) {
        mAbstractSyntaxTree = abstractSyntaxTree;
        mSymbolTable = symbolTable;
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
        mAbstractSyntaxTree.checkSemantics(mSymbolTable, semanticsMessages);
//        mEnvironment.getDuplicateDeclarationErrors(semanticsMessages);
//        mEnvironment.getDuplicateLabelErrors(semanticsMessages);
//        mEnvironment.getUndefinedDeclarationErrors(semanticsMessages);
//        mEnvironment.getCyclicDependencyErrors(semanticsMessages);
//        if (!semanticsMessages.fatalError()) {
//            mEnvironment.getConditionErrors(semanticsMessages);
//        }
        return semanticsMessages;
    }
}
