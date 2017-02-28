/**
 * StatementChecker.java.
 */

package semanticChecker;

import ASTnodes.Form;
import ASTnodes.expressions.literals.Identifier;
import ASTnodes.statements.ComputedQuestion;
import ASTnodes.statements.IfStatement;
import ASTnodes.statements.SimpleQuestion;
import ASTnodes.statements.Statement;
import ASTnodes.types.Type;
import ASTnodes.visitors.FormAndStatementVisitor;
import semanticChecker.messageHandling.MessageData;
import semanticChecker.messageHandling.errors.DuplicateIdentifierError;
import semanticChecker.messageHandling.warnings.DuplicateIdentifierWarning;
import semanticChecker.messageHandling.warnings.DuplicateLabelWarning;

import java.util.HashSet;

import java.util.Map;
import java.util.Set;

public class StatementChecker implements FormAndStatementVisitor<Identifier> {

    private Map<String, Type> identifierToTypeMap;
    private MessageData messages;
    private Set<String> questionLabels;

    public StatementChecker(Form ast, Map<String, Type> identifierToTypeMap, MessageData messages) {
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
            questionLabels.add(statement.getText());
        }
        return null;
    }

    @Override
    public Identifier visit(ComputedQuestion statement) {
        if (!duplicateQuestionIdentifiers(statement)) {
            identifierToTypeMap.put(statement.getIdentifier().getName(), statement.getType());
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
}
