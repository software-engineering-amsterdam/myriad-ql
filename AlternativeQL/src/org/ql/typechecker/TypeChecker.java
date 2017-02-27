package org.ql.typechecker;

import org.ql.ast.Expression;
import org.ql.ast.Form;
import org.ql.ast.Statement;
import org.ql.ast.form.FormVisitor;
import org.ql.ast.statement.IfThen;
import org.ql.ast.statement.IfThenElse;
import org.ql.ast.statement.Question;
import org.ql.ast.statement.StatementVisitor;
import org.ql.ast.type.BooleanType;
import org.ql.ast.type.Type;
import org.ql.collector.QuestionCollector;
import org.ql.symbol_table.HashMapSymbolTable;
import org.ql.symbol_table.SymbolTable;
import org.ql.typechecker.expression.ExpressionTypeChecker;
import org.ql.typechecker.statement.FormQuestionCollector;
import org.ql.typechecker.statement.QuestionVisitor;

import java.util.List;

public class TypeChecker implements FormVisitor<Void>, StatementVisitor<Void> {

    private final QuestionCollector<Form> questionCollector;

    private ExpressionTypeChecker expressionTypeChecker;

    public TypeChecker(QuestionCollector<Form> questionCollector) {
        this.questionCollector = questionCollector;
    }

    @Override
    public Void visit(Form form) {
        if(form.getName().toString().isEmpty()) {
            // TODO: Collect error
        }

        expressionTypeChecker = new ExpressionTypeChecker(createSymbolTable(form));

        checkStatements(form.getStatements());

        return null;
    }

    @Override
    public Void visit(IfThen ifThen) {
        checkIfCondition(ifThen.getCondition());
        checkStatements(ifThen.getThenStatements());

        return null;
    }

    private void checkIfCondition(Expression condition) {
        try {
            if (!(condition.accept(expressionTypeChecker) instanceof BooleanType)) {
                // TODO: Collect the errors
            }
        } catch (Throwable throwable) {
            // TODO: Collect the errors
        }
    }

    @Override
    public Void visit(IfThenElse ifThenElse) {
        checkIfCondition(ifThenElse.getCondition());

        checkStatements(ifThenElse.getThenStatements());
        checkStatements(ifThenElse.getElseStatements());

        return null;
    }

    @Override
    public Void visit(Question question) {
        if(question.getQuestionText().toString().isEmpty()) {
            // TODO: Collect the errors
        }

        if(question.getDefaultValue() != null) {
            try {
                question.getDefaultValue().accept(expressionTypeChecker);
            } catch (Throwable throwable) {
                // TODO: Collect the errors
            }
        }

        question.accept(new QuestionVisitor());

        return null;
    }

    private SymbolTable createSymbolTable(Form form) {
        List<Question> questions = questionCollector.collect(form);
        SymbolTable symbolTable = new HashMapSymbolTable();
        for(Question question : questions) {
            symbolTable.put(question.getId(), question.getType());
        }

        return symbolTable;
    }

    private void checkStatements(List<Statement> statements) {
        for(Statement statement : statements) {
            statement.accept(this);
        }
    }
}
