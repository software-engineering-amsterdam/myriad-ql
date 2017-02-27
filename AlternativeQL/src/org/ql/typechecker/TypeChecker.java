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
import org.ql.collection.Questions;
import org.ql.collection.collector.QuestionCollector;
import org.ql.symbol_table.HashMapSymbolTable;
import org.ql.symbol_table.SymbolTable;
import org.ql.typechecker.expression.ExpressionTypeChecker;
import org.ql.typechecker.expression.TypeError;
import org.ql.typechecker.expression.TypeMismatchException;
import org.ql.typechecker.messages.MessageBag;

import java.util.List;

public class TypeChecker implements FormVisitor<Void>, StatementVisitor<Void> {

    private final QuestionCollector<Form> questionCollector;
    private final MessageBag messages;

    private ExpressionTypeChecker expressionTypeChecker;

    public TypeChecker(QuestionCollector<Form> questionCollector, MessageBag messages) {
        this.questionCollector = questionCollector;
        this.messages = messages;
    }

    @Override
    public Void visit(Form form) {
        if (form.getName().toString().isEmpty()) {
            messages.addError("Form name cannot be empty", form.getName());
        }

        Questions questions = questionCollector.collect(form);

        checkQuestionDuplicates(questions);

        checkQuestionLabelsDuplicates(questions);

        expressionTypeChecker = new ExpressionTypeChecker(createSymbolTable(questions));

        checkStatements(form.getStatements());

        return null;
    }

    @Override
    public Void visit(IfThen ifThen) {
        checkIfCondition(ifThen.getCondition());

        checkStatements(ifThen.getThenStatements());

        return null;
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
        checkQuestionText(question);

        checkDefaultValue(question);

        question.accept(this);

        return null;
    }

    private void checkQuestionText(Question question) {
        if (question.getQuestionText().toString().isEmpty()) {
            messages.addError("No question text found", question.getQuestionText());
        }
    }

    private void checkDefaultValue(Question question) {
        if (question.getDefaultValue() != null) {
            try {
                Type value = question.getDefaultValue().accept(expressionTypeChecker);
                if (!question.getType().toString().equals(value.toString())) {
                    messages.addError(new TypeMismatchException(question.getType(), value));
                }
            } catch (Throwable throwable) {
                messages.addError("An error occurred with the default value", question.getDefaultValue());
            }
        }
    }

    private SymbolTable createSymbolTable(List<Question> questions) {
        SymbolTable symbolTable = new HashMapSymbolTable();
        for (Question question : questions) {
            symbolTable.put(question.getId(), question.getType());
        }

        return symbolTable;
    }

    private void checkStatements(List<Statement> statements) {
        for (Statement statement : statements) {
            statement.accept(this);
        }
    }

    private void checkQuestionDuplicates(Questions questions) {
        for (Question question : questions) {
            if (questions.hasDuplicates(question)) {
                messages.addError("Question '" + question.getId() + "' has duplicate(s)", question);
            }
        }
    }

    private void checkQuestionLabelsDuplicates(Questions questions) {
        for(Question question : questions) {
            if (questions.hasLabelDuplicates(question)) {
                messages.addError("Question '" + question.getId() + "' label has duplicate(s)", question);
            }
        }
    }

    private void checkIfCondition(Expression condition) {
        try {
            Type conditionType = condition.accept(expressionTypeChecker);
            if (!(conditionType instanceof BooleanType)) {
                messages.addError(new TypeMismatchException(new BooleanType(), conditionType));
            }
        } catch (TypeError typeError) {
            messages.addError(typeError);
        } catch (Throwable throwable) {
            messages.addError("Unrecognized error occurred: " + throwable, condition);
        }
    }
}
