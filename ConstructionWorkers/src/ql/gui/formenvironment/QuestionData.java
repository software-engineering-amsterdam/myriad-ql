/**
 * QuestionData.java.
 */

package ql.gui.formenvironment;

import ql.astnodes.Form;
import ql.astnodes.statements.ComputedQuestion;
import ql.astnodes.statements.IfStatement;
import ql.astnodes.statements.SimpleQuestion;
import ql.astnodes.statements.Statement;
import ql.astnodes.visitors.FormAndStatementVisitor;

import java.util.ArrayList;
import java.util.List;

public class QuestionData implements FormAndStatementVisitor<Void>{

    private final List<SimpleQuestion> simpleQuestions;
    private final List<ComputedQuestion> computedQuestions;
    private final List<IfStatement> ifStatements;

    private final List<SimpleQuestion> allQuestions;

    public QuestionData(Form ast) {
        this.simpleQuestions = new ArrayList<>();
        this.computedQuestions = new ArrayList<>();
        this.ifStatements = new ArrayList<>();
        ast.accept(this);
        this.allQuestions = setAllQuestions();
    }

    public List<ComputedQuestion> getComputedQuestions(){
        return this.computedQuestions;
    }

    public List<SimpleQuestion> getSimpleQuestions(){
        return this.simpleQuestions;
    }

    public List<IfStatement> getIfStatements(){
        return this.ifStatements;
    }

    public List<SimpleQuestion> getAllQuestions(){
        return this.allQuestions;
    }

    public List<SimpleQuestion> setAllQuestions(){
        List<SimpleQuestion> allQuestions = this.getSimpleQuestions();

        List<ComputedQuestion> computedQuestions = this.getComputedQuestions();

        allQuestions.addAll(computedQuestions);
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
    public Void visit(SimpleQuestion statement) {
        simpleQuestions.add(statement);
        return null;
    }

    @Override
    public Void visit(ComputedQuestion statement) {
        computedQuestions.add(statement);
        return null;
    }

    @Override
    public Void visit(IfStatement statement) {
        ifStatements.add(statement);
        for (Statement subStatement : statement.getStatements()) {
            subStatement.accept(this);
        }
        return null;
    }
}
