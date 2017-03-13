/**
 * QuestionData.java.
 */

package ql.gui.formenvironment;

import ql.astnodes.Form;
import ql.astnodes.statements.ComputedQuestion;
import ql.astnodes.statements.IfStatement;
import ql.astnodes.statements.SimpleQuestion;
import ql.astnodes.statements.Statement;
import ql.visitorinterfaces.FormAndStatementVisitor;

import java.util.ArrayList;
import java.util.List;

public class QuestionData implements FormAndStatementVisitor<Void>{

    private final List<SimpleQuestion> simpleQuestions;
    private final List<ComputedQuestion> computedQuestions;
    private final List<IfStatement> ifStatements;
    private final List<SimpleQuestion> allQuestions;

    public QuestionData(Form ast) {
        simpleQuestions = new ArrayList<>();
        computedQuestions = new ArrayList<>();
        ifStatements = new ArrayList<>();
        ast.accept(this);
        allQuestions = setAllQuestions();
    }

    private List<SimpleQuestion> setAllQuestions() {
        List<SimpleQuestion> allQuestions = simpleQuestions;
        allQuestions.addAll(computedQuestions);
        return allQuestions;
    }

    public List<ComputedQuestion> getComputedQuestions(){
        return computedQuestions;
    }

    public List<IfStatement> getIfStatements(){
        return ifStatements;
    }

    public List<SimpleQuestion> getAllQuestions(){
        return allQuestions;
    }

    @Override
    public Void visit(Form form) {
        for (Statement statement : form.getStatements()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(SimpleQuestion simpleQuestion) {
        simpleQuestions.add(simpleQuestion);
        return null;
    }

    @Override
    public Void visit(ComputedQuestion computedQuestion) {
        computedQuestions.add(computedQuestion);
        return null;
    }

    @Override
    public Void visit(IfStatement ifStatement) {
        ifStatements.add(ifStatement);
        for (Statement bodyStatement : ifStatement.getStatements()) {
            bodyStatement.accept(this);
        }
        return null;
    }
}
