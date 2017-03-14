package com.matthewchapman.ql.validation;

import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.ql.ast.Statement;
import com.matthewchapman.ql.ast.Type;
import com.matthewchapman.ql.ast.atomic.*;
import com.matthewchapman.ql.ast.expression.Parameter;
import com.matthewchapman.ql.ast.expression.ParameterGroup;
import com.matthewchapman.ql.ast.expression.binary.*;
import com.matthewchapman.ql.ast.expression.unary.Negation;
import com.matthewchapman.ql.ast.statement.CalculatedQuestion;
import com.matthewchapman.ql.ast.statement.IfElseStatement;
import com.matthewchapman.ql.ast.statement.IfStatement;
import com.matthewchapman.ql.ast.statement.Question;

import java.util.*;

/**
 * Created by matt on 03/03/2017.
 *
 * Gathers all of the questions contained within a given Form, allows checking for duplicates
 */
public class QuestionCollection implements QLVisitor<Void> {

    private List<Question> questionList;
    private HashMap<String, Type> typeTable;

    public QuestionCollection() {
        typeTable = new HashMap<>();
        questionList = new ArrayList<>();
    }

    public void gatherQuestions(Form form) {
        for(Statement statement : form.getStatements()) {
            statement.accept(this, null);
        }
    }

    public Map<String, Type> getTypeTable() {
        return this.typeTable;
    }

    public List<Question> getQuestionList() {
        return this.questionList;
    }

    // hooray for O(n) complexity!
    public void findDuplicates() {
        Set<String> questionIDs = new HashSet<>();

        for(Question question : questionList) {
            if(!questionIDs.add(question.getName())) {
                System.err.println("Error: Duplicate Question found");  //TODO: Proper error
            }
        }
    }

    @Override
    public Void visit(Question question, String context) {
        questionList.add(question);
        typeTable.put(question.getName(), question.getType());
        return null;
    }

    @Override
    public Void visit(IfStatement ifStatement, String context) {
        for(Statement statement : ifStatement.getIfCaseStatements()) {
            statement.accept(this, null);
        }

        return null;
    }

    @Override
    public Void visit(IfElseStatement ifElseStatement, String context) {
        for(Statement statement : ifElseStatement.getIfCaseStatements()) {
            statement.accept(this, null);
        }

        for(Statement statement : ifElseStatement.getElseCaseStatements()) {
            statement.accept(this, null);
        }

        return null;
    }

    @Override
    public Void visit(CalculatedQuestion calculatedQuestion, String context) {
        questionList.add(calculatedQuestion);
        typeTable.put(calculatedQuestion.getName(), calculatedQuestion.getType());
        return null;
    }

    @Override
    public Void visit(Parameter parameter, String context) {
        return null;
    }

    @Override
    public Void visit(ParameterGroup parameterGroup, String context) {
        return null;
    }

    @Override
    public Void visit(StringLiteral stringLiteral, String context) {
        return null;
    }

    @Override
    public Void visit(IntegerLiteral integerLiteral, String context) {
        return null;
    }

    @Override
    public Void visit(BooleanLiteral booleanLiteral, String context) {
        return null;
    }

    @Override
    public Void visit(Addition addition, String context) {
        return null;
    }

    @Override
    public Void visit(Division division, String context) {
        return null;
    }

    @Override
    public Void visit(Equal equal, String context) {
        return null;
    }

    @Override
    public Void visit(GreaterThan greaterThan, String context) {
        return null;
    }

    @Override
    public Void visit(GreaterThanEqualTo greaterThanEqualTo, String context) {
        return null;
    }

    @Override
    public Void visit(LessThan lessThan, String context) {
        return null;
    }

    @Override
    public Void visit(LessThanEqualTo lessThanEqualTo, String context) {
        return null;
    }

    @Override
    public Void visit(LogicalAnd logicalAnd, String context) {
        return null;
    }

    @Override
    public Void visit(LogicalOr logicalOr, String context) {
        return null;
    }

    @Override
    public Void visit(Multiplication multiplication, String context) {
        return null;
    }

    @Override
    public Void visit(NotEqual notEqual, String context) {
        return null;
    }

    @Override
    public Void visit(Subtraction subtraction, String context) {
        return null;
    }

    @Override
    public Void visit(Negation negation, String context) {
        return null;
    }

    @Override
    public Void visit(BooleanType booleanType, String context) {
        return null;
    }

    @Override
    public Void visit(IntegerType integerType, String context) {
        return null;
    }

    @Override
    public Void visit(StringType stringType, String context) {
        return null;
    }

}
