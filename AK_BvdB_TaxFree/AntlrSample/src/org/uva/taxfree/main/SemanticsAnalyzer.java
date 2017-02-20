package org.uva.taxfree.main;

import org.uva.taxfree.ast.Ast;
import org.uva.taxfree.model.NamedNode;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SemanticsAnalyzer {
    private Ast mAst;

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
        return hasDuplicateLabels().isEmpty();
    }

    public List<String> hasDuplicateLabels() {
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
}
