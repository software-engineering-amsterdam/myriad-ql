/*
 * Software Construction - University of Amsterdam
 *
 * ./src/ql/semanticchecker/IdentifierChecker.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package ql.semanticchecker;

import ql.astnodes.Form;
import ql.astnodes.expressions.literals.*;
import ql.astnodes.statements.ComputedQuestion;
import ql.astnodes.statements.IfStatement;
import ql.astnodes.statements.SimpleQuestion;
import ql.astnodes.statements.Statement;
import ql.astnodes.types.Type;
import ql.visitorinterfaces.FormAndStatementVisitor;
import ql.semanticchecker.messagehandling.MessageData;
import ql.semanticchecker.messagehandling.errors.qlerrors.DuplicateIdentifierError;
import ql.semanticchecker.messagehandling.errors.qlerrors.IfExpressionUndefinedError;
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
    public void visit(Form form) {
        for (Statement statement : form.getStatements()) {
            statement.accept(this);
        }
    }

    @Override
    public Identifier visit(IfStatement ifStatement) {
        IfStatementVisitor visitor = new IfStatementVisitor(ifStatement);

        if(visitor.getIdentifierDefinedInBody()) {
            messages.addError(new IfExpressionUndefinedError(ifStatement.getLineNumber()));
        }

        for (Statement subStatement : ifStatement.getStatements()) {
            subStatement.accept(this);
        }
        return null;
    }

    @Override
    public Identifier visit(SimpleQuestion simpleQuestion) {
        if (!duplicateQuestionIdentifiers(simpleQuestion)) {
            identifierToTypeMap.put(simpleQuestion.getIdentifier().getName(), simpleQuestion.getType());
        }

        if (!duplicateQuestionLabels(simpleQuestion)) {
            questionLabels.add(simpleQuestion.getLabel());
        }
        return null;
    }

    @Override
    public Identifier visit(ComputedQuestion computedQuestion) {
        if (!duplicateQuestionIdentifiers(computedQuestion)) {
            identifierToTypeMap.put(computedQuestion.getIdentifier().getName(), computedQuestion.getType());
        }

        if (!duplicateQuestionLabels(computedQuestion)) {
            questionLabels.add(computedQuestion.getLabel());
        }
        return null;
    }

    private boolean duplicateQuestionIdentifiers(SimpleQuestion question) {
        String questionIdentifierName = question.getIdentifier().getName();

        if (identifierToTypeMap.get(questionIdentifierName) != null) {
//            TODO: decide.
//            if (isEqual(identifierToTypeMap.get(questionIdentifierName), question.getType())) {
            if ((identifierToTypeMap.get(questionIdentifierName)).equals(question.getType())) {
                messages.addWarning(new DuplicateIdentifierWarning(question.getLineNumber(), question.getIdentifier()));
                return true;
            }
            messages.addError(new DuplicateIdentifierError(question.getLineNumber(), question.getIdentifier()));
            return true;
        }
        return false;
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

//    private static boolean isEqual(Object o1, Object o2) {
//        return o1 == o2 || (o1 != null && o1.equals(o2));
//    }
}
