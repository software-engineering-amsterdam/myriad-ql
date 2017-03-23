package org.ql.gui;

import org.ql.ast.form.Form;
import org.ql.ast.identifier.Identifier;
import org.ql.ast.identifier.IdentifierSet;
import org.ql.ast.statement.Question;
import org.ql.evaluator.ConditionEvaluator;
import org.ql.evaluator.QuestionEvaluator;
import org.ql.evaluator.ValueTable;
import org.ql.evaluator.value.Value;

import java.util.List;

public class GUIEvaluator {
    private final ConditionEvaluator conditionEvaluator;
    private final QuestionEvaluator questionEvaluator;
    private final ValueTable valueTable;
    private final IdentifierSet modifiedQuestions;

    public GUIEvaluator(ValueTable valueTable) {
        this.valueTable = valueTable;
        this.modifiedQuestions = new IdentifierSet();

        conditionEvaluator = new ConditionEvaluator();
        questionEvaluator = new QuestionEvaluator(this.modifiedQuestions);
    }

    public void declareQuestionValue(Identifier identifier, Value newValue) {
        modifiedQuestions.declare(identifier);
        valueTable.declare(identifier, newValue);
    }

    public void refreshValues(Form form) {
        questionEvaluator.updateValueTable(form, valueTable);
    }

    public List<Question> evaluateQuestions(Form form) {
        return conditionEvaluator.visitForm(form, valueTable);
    }

    public boolean isQuestionModified(Identifier id) {
        return modifiedQuestions.isDeclared(id);
    }
}
