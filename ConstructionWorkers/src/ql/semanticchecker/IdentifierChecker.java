/**
 * IdentifierChecker.java.
 */

package ql.semanticchecker;

import ql.astnodes.Form;
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
import ql.gui.formenvironment.ValueData;
import ql.semanticchecker.messagehandling.MessageData;
import ql.semanticchecker.messagehandling.errors.DuplicateIdentifierError;
import ql.semanticchecker.messagehandling.errors.IfExpressionUndefinedError;
import ql.semanticchecker.messagehandling.warnings.DuplicateIdentifierWarning;
import ql.semanticchecker.messagehandling.warnings.DuplicateLabelWarning;

import java.util.HashSet;

import java.util.Map;
import java.util.Set;

public class IdentifierChecker implements FormAndStatementVisitor<Identifier>, ExpressionVisitor<Identifier>{

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
        statement.getExpression().accept(this);
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

    public boolean duplicateQuestionIdentifiers(SimpleQuestion question) {
        String questionIdentifierName = question.getIdentifier().getName();

        if (identifierToTypeMap.get(questionIdentifierName) != null) {
            if ((identifierToTypeMap.get(questionIdentifierName)).getClass().equals(question.getType().getClass())) {
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

    @Override
    public Identifier visit(MyBoolean literal) {
        return null;
    }

    @Override
    public Identifier visit(MyInteger literal) {
        return null;
    }

    @Override
    public Identifier visit(MyString literal) {
        return null;
    }

    @Override
    public Identifier visit(Identifier literal) {
        if(identifierToTypeMap.get(literal.getName()) == null)
            messages.addError(new IfExpressionUndefinedError(literal.getLineNumber(), literal));
        return null;
    }

    @Override
    public Identifier visit(Money literal) {
        return null;
    }

    @Override
    public Identifier visit(Parentheses expression) {
        return null;
    }

    @Override
    public Identifier visit(Negation expression) {
        return null;
    }

    @Override
    public Identifier visit(Negative expression) {
        return null;
    }

    @Override
    public Identifier visit(Positive expression) {
        return null;
    }

    @Override
    public Identifier visit(AND expression) {
        return null;
    }

    @Override
    public Identifier visit(OR expression) {
        return null;
    }

    @Override
    public Identifier visit(EQ expression) {
        return null;
    }

    @Override
    public Identifier visit(NEQ expression) {
        return null;
    }

    @Override
    public Identifier visit(GT expression) {
        return null;
    }

    @Override
    public Identifier visit(LT expression) {
        return null;
    }

    @Override
    public Identifier visit(GTEQ expression) {
        return null;
    }

    @Override
    public Identifier visit(LTEQ expression) {
        return null;
    }

    @Override
    public Identifier visit(Addition expression) {
        return null;
    }

    @Override
    public Identifier visit(Subtraction expression) {
        return null;
    }

    @Override
    public Identifier visit(Multiplication expression) {
        return null;
    }

    @Override
    public Identifier visit(Division expression) {
        return null;
    }
}
