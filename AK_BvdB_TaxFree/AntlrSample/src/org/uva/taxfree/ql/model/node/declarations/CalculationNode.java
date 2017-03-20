package org.uva.taxfree.ql.model.node.declarations;

import org.uva.taxfree.ql.gui.MessageList;
import org.uva.taxfree.ql.gui.QuestionForm;
import org.uva.taxfree.ql.gui.widgets.CalculationWidget;
import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.ql.model.environment.SymbolTable;
import org.uva.taxfree.ql.model.node.expression.ExpressionNode;
import org.uva.taxfree.ql.model.types.Type;

import java.util.HashSet;
import java.util.Set;

public class CalculationNode extends DeclarationNode {
    private final ExpressionNode mExpression;

    public CalculationNode(String label, String id, Type type, ExpressionNode expression, SourceInfo sourceInfo) {
        super(label, id, type, sourceInfo);
        mExpression = expression;
    }

    @Override
    public void fillSymbolTable(SymbolTable symbolTable) {
        super.fillSymbolTable(symbolTable);
        symbolTable.addCalculation(this);
        mExpression.fillSymbolTable(symbolTable);
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
        for (String dependency : dependencies) {
            if (!symbolTable.contains(dependency)) {
                semanticsMessages.addError("Unresolved variable: " + dependency);
            }
        }
        mExpression.checkSemantics(symbolTable, semanticsMessages);
    }


    public Set<String> getUsedVariables() {
        Set<String> declarations = new HashSet<>();
        mExpression.getDependencies(declarations);
        return declarations;
    }

    @Override
    public void fillQuestionForm(QuestionForm frame) {
        frame.addWidget(new CalculationWidget(getLabel(), getId(), mExpression));
    }
}
