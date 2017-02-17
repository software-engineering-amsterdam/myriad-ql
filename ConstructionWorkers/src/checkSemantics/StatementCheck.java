package checkSemantics;

import ASTnodes.Form;
import ASTnodes.expressions.literals.Identifier;
import ASTnodes.statements.ComputedQuestion;
import ASTnodes.statements.IfStatement;
import ASTnodes.statements.SimpleQuestion;
import ASTnodes.statements.Statement;
import ASTnodes.types.Type;
import ASTnodes.visitors.FormAndStatementVisitor;
import checkSemantics.messageHandling.MessageData;
import checkSemantics.messageHandling.errors.DuplicateIdentifierError;
import checkSemantics.messageHandling.warnings.DuplicateIdentifierWarning;
import checkSemantics.messageHandling.warnings.DuplicateLabelWarning;

import java.util.HashMap;
import java.util.HashSet;

import java.util.Set;

/**
 * Created by LGGX on 16-Feb-17.
 */
public class StatementCheck implements FormAndStatementVisitor<Identifier> {

    public HashMap<String, Type> identifierMap;
    private Set<String> questionLabels;
    private MessageData messageLists;

    public StatementCheck(Form ast, HashMap identifierMap, MessageData messageLists) {

        this.messageLists = messageLists;
        this.identifierMap = identifierMap;
        this.questionLabels = new HashSet<String>();

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
    public Identifier visit(SimpleQuestion statement) {
        if (!duplicateQuestionIdentifiers(statement)) {
            identifierMap.put(statement.getIdentifier().getName(), statement.getType());
        }
        if (!duplicateQuestionLabels(statement)) {
            questionLabels.add(statement.getText());
        }
        return null;
    }

    @Override
    public Identifier visit(ComputedQuestion statement) {
        if (!duplicateQuestionIdentifiers(statement)) {
            identifierMap.put(statement.getIdentifier().getName(), statement.getType());
        }
        if (!duplicateQuestionLabels(statement)) {
            questionLabels.add(statement.getText());
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

    public boolean duplicateQuestionIdentifiers(SimpleQuestion question) {

        if (identifierMap.get(question.getIdentifier().getName()) != null) {

            if ((identifierMap.get(question.getIdentifier().getName())).getClass()
                    .equals(question.getType().getClass())) {

                messageLists.addError(new DuplicateIdentifierError(question.getIdentifier()));
                //System.out.println("Duplicate question identifier with the same Type!");
                return true;
            } else {
                messageLists.addWarning(new DuplicateIdentifierWarning(question.getIdentifier()));
                return true;
            }
        } else {
            if (identifierMap.containsKey(question.getIdentifier().getName())) {
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
                messageLists.addWarning(new DuplicateLabelWarning(question.getIdentifier(), question.getText()));
                //System.out.println("Duplicate question labels found!");
                return true;
            }
        }
        return false;
    }
}
