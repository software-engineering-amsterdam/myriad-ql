package org.uva.taxfree.main;

import org.uva.taxfree.ast.Ast;
import org.uva.taxfree.model.NamedNode;
import org.uva.taxfree.model.Node;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SemanticsAnalyzer {
    private Ast mAst;

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
     */

    public SemanticsAnalyzer(Ast ast) {
        mAst = ast;
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

    public List<String> getUndefinedQuestionErrors() {
        // TODO
        List<String> errorMessages = new ArrayList<>();
        Set<String> processedConditionIds = new LinkedHashSet<>();
        for (String Id : getConditionIds()) {
            if(!getQuestionIds().contains(Id)){
                errorMessages.add("Unresolved condition in questions: " + Id);
            }
        }
        return errorMessages;
    }

    public List<String> getDuplicateQuestionErrors() {
        List<String> errorMessages = new ArrayList<>();
        Set<String> processedQuestionIds = new LinkedHashSet<>();
        for (NamedNode questionNode : mAst.getQuestions()) {
            String questionId = questionNode.getId();
            if (!processedQuestionIds.add(questionId)) {
                errorMessages.add("Duplicate question declaration found: " + questionId);
            }
        }
        return errorMessages;
    }

    public List<String> getDuplicateLabelErrors() {
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
