package org.uva.hatt.taxform.ast.visitors;

import org.uva.hatt.taxform.ast.nodes.*;
import org.uva.hatt.taxform.ast.nodes.items.Question;
import org.uva.hatt.taxform.ast.nodes.types.ValueType;

import java.util.*;

public class TypeCheckerVisitor extends AstVisitor<ASTNode>{

    private Map<String, String> errors = new HashMap<>();
    private Map<String, String> warnings = new HashMap<>();
    private Map<String, ValueType> questions = new HashMap<>();

    @Override
    public ASTNode visit(Form form) {
//        form.getQuestions().forEach(this::visit);

        return form;
    }

    @Override
    public ASTNode visit(FormId formId) {
        return formId;
    }

    @Override
    public ASTNode visit(Question question) {
        String questionLabel = question.getQuestion();

//        Optional<String> foundItem = questions.stream().filter(label -> label.equals(questionLabel)).findFirst();
        if (questions.containsKey(questionLabel)) {
            warnings.put("duplicate question", questionLabel);

//            String valueOfType = questions.get(questionLabel).getValueOfType();
//            if (!valueOfType.equals(question.getType().getValueOfType())) {
//                errors.put("duplicate question declarations with different types", questionLabel);
//            }
        } else {
            questions.put(questionLabel, question.getType());
        }

        return question;
    }

    @Override
    public ASTNode visit(ValueType valueType) {
        return valueType;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public Map<String, String> getWarnings() {
        return warnings;
    }
}
