/**
 * IdentifierChecker.java.
 */

package semanticChecker;

import ASTnodes.Form;
import ASTnodes.expressions.binaries.equality.*;
import ASTnodes.expressions.binaries.logic.AND;
import ASTnodes.expressions.binaries.logic.OR;
import ASTnodes.expressions.binaries.numerical.Addition;
import ASTnodes.expressions.binaries.numerical.Division;
import ASTnodes.expressions.binaries.numerical.Multiplication;
import ASTnodes.expressions.binaries.numerical.Subtraction;
import ASTnodes.expressions.literals.*;
import ASTnodes.expressions.unaries.Negation;
import ASTnodes.expressions.unaries.Negative;
import ASTnodes.expressions.unaries.Parenthesis;
import ASTnodes.expressions.unaries.Positive;
import ASTnodes.statements.ComputedQuestion;
import ASTnodes.statements.IfStatement;
import ASTnodes.statements.SimpleQuestion;
import ASTnodes.statements.Statement;
import ASTnodes.types.Type;
import ASTnodes.visitors.ExpressionVisitor;
import ASTnodes.visitors.FormAndStatementVisitor;
import semanticChecker.formDataStorage.valueData.ValueData;
import semanticChecker.messageHandling.MessageData;
import semanticChecker.messageHandling.errors.DuplicateIdentifierError;
import semanticChecker.messageHandling.errors.IfExpressionUndefinedError;
import semanticChecker.messageHandling.warnings.DuplicateIdentifierWarning;
import semanticChecker.messageHandling.warnings.DuplicateLabelWarning;

import java.util.HashSet;

import java.util.Map;
import java.util.Set;

public class IdentifierChecker implements FormAndStatementVisitor<Identifier>, ExpressionVisitor<Identifier>{

    private Map<String, Type> identifierToTypeMap;
    private MessageData messages;
    private Set<String> questionLabels;
    private ValueData questionStates;

    public IdentifierChecker(Form ast, Map<String, Type> identifierToTypeMap, MessageData messages,
                             ValueData questionStates) {

        this.identifierToTypeMap = identifierToTypeMap;
        this.messages = messages;
        this.questionLabels = new HashSet<>();
        this.questionStates = questionStates;

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
            questionStates.addValue(statement.getIdentifier().getName(), statement.getType().getDefaultState());
        }

        if (!duplicateQuestionLabels(statement)) {
            questionLabels.add(statement.getText());
        }
        return null;
    }

    @Override
    public Identifier visit(ComputedQuestion statement) {
        if (!duplicateQuestionIdentifiers(statement)) {
            identifierToTypeMap.put(statement.getIdentifier().getName(), statement.getType());
            questionStates.addValue(statement.getIdentifier().getName(), statement.getType().getDefaultState());
        }

        if (!duplicateQuestionLabels(statement)) {
            questionLabels.add(statement.getText());
        }
        return null;
    }

    public boolean duplicateQuestionIdentifiers(SimpleQuestion question) {
        String questionIdentifierName = question.getIdentifier().getName();

        if (identifierToTypeMap.get(questionIdentifierName) != null) {
            if ((identifierToTypeMap.get(questionIdentifierName)).getClass().equals(question.getType().getClass())) {
                messages.addWarning(new DuplicateIdentifierWarning(question.getLocation(), question.getIdentifier()));
                return true;
            } else {
                messages.addError(new DuplicateIdentifierError(question.getLocation(), question.getIdentifier()));
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
            if (label.equals(question.getText())) {
                messages.addWarning(new DuplicateLabelWarning(question.getLocation(), question.getIdentifier(),
                        question.getText()));
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
            messages.addError(new IfExpressionUndefinedError(literal.getLocation(), literal));
        return null;
    }

    @Override
    public Identifier visit(Money literal) {
        return null;
    }

    @Override
    public Identifier visit(Parenthesis expression) {
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
