package org.uva.taxfree.model.node.declarations;

import org.uva.taxfree.gui.MessageList;
import org.uva.taxfree.gui.QuestionForm;
import org.uva.taxfree.gui.widgets.CalculationWidget;
import org.uva.taxfree.model.environment.SymbolTable;
import org.uva.taxfree.model.node.expression.ExpressionNode;
import org.uva.taxfree.model.types.Type;

import java.util.HashSet;
import java.util.Set;

public class CalculationNode extends DeclarationNode {
    private final ExpressionNode mExpression;

    public CalculationNode(String label, String id, Type type, ExpressionNode expression) {
        super(label, id, type);
        mExpression = expression;
    }

    @Override
    public void fillSymbolTable(SymbolTable symbolTable) {
        symbolTable.addDeclaration(this);
        symbolTable.addCalculation(this);
        symbolTable.addExpression(mExpression);
    }

    public String toString() {
        return mExpression.toString();
    }

    public String resolveValue() {
        return mExpression.evaluate();
    }

    @Override
    public void checkSemantics(SymbolTable symbolTable, MessageList semanticsMessages) {
        Set<String> dependencies = getUsedVariables();
        symbolTable.generateDependencies(dependencies);
        if (dependencies.contains(getId())) {
            semanticsMessages.addError("Cyclic dependency error in " + getId() + ", (" + mExpression.evaluate() + ")");
        }
    }

    public Set<String> getUsedVariables() {
        Set<String> declarations = new HashSet<>();
        getDependencies(declarations);
        mExpression.getDependencies(declarations);
        return declarations;
    }

    @Override
    public void fillQuestionForm(QuestionForm frame) {
        frame.addWidget(new CalculationWidget(getLabel(), getId(), mExpression));
    }
}
