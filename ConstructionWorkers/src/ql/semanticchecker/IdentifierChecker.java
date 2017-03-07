/**
 * IdentifierChecker.java.
 */

package ql.semanticchecker;

import ql.astnodes.Form;
import ql.astnodes.expressions.Expression;
import ql.astnodes.expressions.binaries.equality.*;
import ql.astnodes.expressions.binaries.logic.AND;
import ql.astnodes.expressions.binaries.logic.OR;
import ql.astnodes.expressions.binaries.numerical.Addition;
import ql.astnodes.expressions.binaries.numerical.Division;
import ql.astnodes.expressions.binaries.numerical.Multiplication;
import ql.astnodes.expressions.binaries.numerical.Subtraction;
import ql.astnodes.expressions.literals.*;
import ql.astnodes.expressions.unaries.Negation;
import ql.astnodes.expressions.unaries.Negative;
import ql.astnodes.expressions.unaries.Parentheses;
import ql.astnodes.expressions.unaries.Positive;
import ql.astnodes.statements.ComputedQuestion;
import ql.astnodes.statements.IfStatement;
import ql.astnodes.statements.SimpleQuestion;
import ql.astnodes.statements.Statement;
import ql.astnodes.types.Type;
import ql.astnodes.visitors.ExpressionVisitor;
import ql.astnodes.visitors.FormAndStatementVisitor;
import ql.semanticchecker.messagehandling.MessageData;
import ql.semanticchecker.messagehandling.errors.DuplicateIdentifierError;
import ql.semanticchecker.messagehandling.errors.IfExpressionUndefinedError;
import ql.semanticchecker.messagehandling.warnings.DuplicateIdentifierWarning;
import ql.semanticchecker.messagehandling.warnings.DuplicateLabelWarning;

import java.util.HashSet;

import java.util.Map;
import java.util.Set;

public class IdentifierChecker implements FormAndStatementVisitor<Identifier> {

    private Map<String, Type> identifierToTypeMap;
    private MessageData messages;
    private Set<String> questionLabels;

    public IdentifierChecker(Form ast, Map<String, Type> identifierToTypeMap, MessageData messages) {
        this.identifierToTypeMap = identifierToTypeMap;
        this.messages = messages;
        this.questionLabels = new HashSet<>();

        ast.accept(this);
    }

    @Override
    public Identifier visit(Form form) {
        for (Statement statement : form.getStatements()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public Identifier visit(IfStatement statement) {
        IfStatementVisitor visitor = new IfStatementVisitor(statement, statement.getExpression());

        if(visitor.getUndefinedIdentifier()) {
            messages.addError(new IfExpressionUndefinedError(statement.getLineNumber()));
        }

        for (Statement subStatement : statement.getStatements()) {
            subStatement.accept(this);
        }
        return null;
    }

    @Override
    public Identifier visit(SimpleQuestion statement) {
        if (!duplicateQuestionIdentifiers(statement)) {
            identifierToTypeMap.put(statement.getIdentifier().getName(), statement.getType());
        }

        if (!duplicateQuestionLabels(statement)) {
            questionLabels.add(statement.getLabel());
        }
        return null;
    }

    @Override
    public Identifier visit(ComputedQuestion statement) {
        if (!duplicateQuestionIdentifiers(statement)) {
            identifierToTypeMap.put(statement.getIdentifier().getName(), statement.getType());
        }

        if (!duplicateQuestionLabels(statement)) {
            questionLabels.add(statement.getLabel());
        }
        return null;
    }

    private boolean duplicateQuestionIdentifiers(SimpleQuestion question) {
        String questionIdentifierName = question.getIdentifier().getName();

        if (identifierToTypeMap.get(questionIdentifierName) != null) {
            if (isEqual(identifierToTypeMap.get(questionIdentifierName), question.getType())) {
                messages.addWarning(new DuplicateIdentifierWarning(question.getLineNumber(), question.getIdentifier()));
                return true;
            } else {
                messages.addError(new DuplicateIdentifierError(question.getLineNumber(), question.getIdentifier()));
                return true;
            }
        } else {
            if (identifierToTypeMap.containsKey(questionIdentifierName)) {
                System.out.println("Duplicate question identifier with null type!");
                return true;
            } else {
                return false;
            }
        }
    }

    private boolean duplicateQuestionLabels(SimpleQuestion question) {
        for (String label : questionLabels) {
            if (label.equals(question.getLabel())) {
                messages.addWarning(new DuplicateLabelWarning(question.getLineNumber(), question.getIdentifier(),
                        question.getLabel()));
                return true;
            }
        }
        return false;
    }

    private static boolean isEqual(Object o1, Object o2) {
        return o1 == o2 || (o1 != null && o1.equals(o2));
    }
}
