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
    private List<String> mUndefinedQuestions;

    /*
     * UNDEFINES - reference to undefined questions
     *   if (nonExists) etc.
     * DUPLICATE-QUESTIONS - duplicate question declarations with different types
     *  "Question?" -> varName : boolean
     *  "Question2?" -> varName : string
     * WRONGCONDITIONS - conditions that are not of the type boolean
     * WRONGCONDITIONS - operands of invalid type to operators
     * IFELSERECURSION - cyclic dependencies between questions
     * // DUPLICATE-LABELS - duplicate labels (warning)
     */

    public SemanticsAnalyzer(Ast ast) {
        mAst = ast;
    }

    public boolean check() {
        List<String> errorMessages = getUndefinedQuestions();
        errorMessages.addAll(getDuplicateLabels());
        return errorMessages.isEmpty();
    }

    public List<String> getDuplicateLabels() {
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

    public List<String> getUndefinedQuestions() {
        List<String> errorMessages = new ArrayList<>();
        Set<String> processedConditionIds = new LinkedHashSet<>();
        for (String Id : getConditionIds()) {
            if(!getQuestionIds().contains(Id)){
                errorMessages.add("Unresolved condition in questions: " + Id);
            }
        }
        return errorMessages;
    }

    private List<String> getQuestionIds() {
        List<String> ids = new ArrayList<>();
        for (NamedNode namedNode : mAst.getQuestions()) {
            ids.add(namedNode.getId());
        }
        return ids;
    }

    private List<String> getConditionIds() {
        List<String> ids = new ArrayList<>();
        for (Node node : mAst.getConditions()) {
            ids.add(node.getId());
        }
        return ids;
    }

}
