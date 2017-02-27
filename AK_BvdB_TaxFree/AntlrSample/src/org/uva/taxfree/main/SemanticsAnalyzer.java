package org.uva.taxfree.main;

import org.uva.taxfree.ast.Ast;
import org.uva.taxfree.model.NamedNode;
import org.uva.taxfree.model.Node;
import org.uva.taxfree.model.SymbolTable;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SemanticsAnalyzer {
    private Ast mAst;
    private SymbolTable mSymbolTable;

    /*
     * UNDEFINES - reference to undefined questions // TODO
     *   if (nonExists) etc.
     * // DUPLICATE-QUESTIONS - duplicate question declarations with different types
     *  "Question?" -> varName : boolean
     *  "Question2?" -> varName : string
     * WRONGCONDITIONS - conditions that are not of the type boolean // TODO
     * WRONGCONDITIONS - operands of invalid type to operators // TODO
     * IFELSERECURSION - cyclic dependencies between questions // TODO
     * // DUPLICATE-LABELS - duplicate labels (warning)
     *
     * TODO:
     * - Check | if ("")
     * - Check | if (1)
     */

    public SemanticsAnalyzer(Ast ast) {
        mAst = ast;
    }

    public SymbolTable getSymbolTable() {
        return mSymbolTable;
    }

    public boolean validSemantics() {
        return getSemanticErrors().isEmpty();
    }

    public List<String> getSemanticErrors() {
        List<String> errorMessages = getUndefinedQuestionErrors();
        errorMessages.addAll(getDuplicateQuestionErrors());
        errorMessages.addAll(getDuplicateLabelErrors());
        return errorMessages;
    }

    private List<String> getUndefinedQuestionErrors() {
        mAst.getConditionsV2(); // TODO: Remove, having this for test purposes.
        List<String> errorMessages = new ArrayList<>();
        // TODO
        // Get conditions, for every varName check the value/question belonging to it
        return errorMessages;
    }

    private List<String> getDuplicateQuestionErrors() {
        List<String> errorMessages = new ArrayList<>();
        Set<String> processedQuestionIds = new LinkedHashSet<>();
        for (NamedNode questionNode : mAst.getQuestions()) {
            String questionId = questionNode.toString();
            if (!processedQuestionIds.add(questionId)) {
                errorMessages.add("Duplicate question declaration found: " + questionId);
            }
        }
        return errorMessages;
    }

    private List<String> getDuplicateLabelErrors() {
        List<String> errorMessages = new ArrayList<>();
        Set<String> processedQuestionLabels = new LinkedHashSet<>();
        for (NamedNode questionNode : mAst.getQuestions()) {
            String questionLabel = questionNode.getLabel();
            if (!processedQuestionLabels.add(questionLabel)) {
                errorMessages.add("Duplicate question label found: " + questionLabel);
            }
        }
        return errorMessages;
    }

    private List<String> getQuestionIds() {
        List<String> ids = new ArrayList<>();
        for (NamedNode namedNode : mAst.getQuestions()) {
            ids.add(namedNode.toString());
        }
        return ids;
    }

    private List<String> getConditionIds() {
        List<String> ids = new ArrayList<>();
        for (Node node : mAst.getConditions()) {
            ids.add(node.toString());
        }
        return ids;
    }

}
