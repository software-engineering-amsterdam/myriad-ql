package org.uva.taxfree.main;

import org.uva.taxfree.model.environment.Environment;

import java.util.ArrayList;
import java.util.List;

public class SemanticsAnalyzer {
    private final Environment mEnvironment;

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

    public SemanticsAnalyzer(Environment environment) {
        mEnvironment = environment;
    }

    public boolean validSemantics() {
        return getSemanticErrors().isEmpty();
    }

    public List<String> getSemanticErrors() {
        List<String> errorMessages = getUndefinedQuestionErrors();
        errorMessages.addAll(mEnvironment.getDuplicateDeclarationErrors());
        errorMessages.addAll(mEnvironment.getDuplicateLabelErrors());
        errorMessages.addAll(mEnvironment.getUndefinedDeclarationErrors());
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

//    This is for blocks stuff, not for undefined questions
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

    private List<String> getQuestionIds() {
        List<String> ids = new ArrayList<>();
//        for (Node node : mEnvironment.getDeclarations()) {
//            ids.add(node.toString());
//        }
        return ids;
    }

    private List<String> getConditionIds() {
        List<String> ids = new ArrayList<>();
//        for (Node node : mEnvironment.getAbstractSyntaxTree()getConditions()) {
//            ids.add(node.toString());
//        }
        return ids;
    }
}
