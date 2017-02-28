package org.uva.taxfree.main;

import org.uva.taxfree.ast.Ast;
import org.uva.taxfree.model.environment.SymbolTable;
import org.uva.taxfree.model.node.Node;
import org.uva.taxfree.model.node.statement.NamedNode;

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

    public SemanticsAnalyzer(Ast ast, SymbolTable symbolTable) {
        mAst = ast;
        mSymbolTable = symbolTable;
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
        List<String> errorMessages = new ArrayList<>();

        List<String> questions = getQuestionIds();
//        Set<ConditionNode> conditions = mAst.getConditionsV2();
//        for (ConditionNode conditionNode : conditions) {
//            for (LiteralNode variable : conditionNode.getVariables()) {
//                variable.toString();
//            }
//        }

        return errorMessages;
    }

//    This is for condition stuff, not for undefined questions
//    private List<String> getUndefinedQuestionErrors() {
//        // TODO: Retrieve once, having this for test (and evaluate) purposes.
//        Set<Node> conditionsTmp = mAst.getConditions(); // This one uses the implicit way
//        Set<ConditionNode> conditions = mAst.getConditionsV2();
//
//        List<String> errorMessages = new ArrayList<>();
//        List<String> questions = getQuestionIds();
//        for(ConditionNode conditionNode : conditions) {
//
//        }
//
//        // TODO
//        // Get conditions, for every varName check the value/question belonging to it
//        return errorMessages;
//    }

    private List<String> getDuplicateQuestionErrors() {
        List<String> errorMessages = new ArrayList<>();
        Set<String> processedQuestionIds = new LinkedHashSet<>();
        for (String questionId : getQuestionIds()) {
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
