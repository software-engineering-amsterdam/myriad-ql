package org.ql.typechecker;

import org.ql.ast.Expression;
import org.ql.ast.Form;
import org.ql.ast.Identifier;
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
import org.ql.typechecker.messages.TypeCheckMessages;

import java.util.List;

// TODO decouple TypeChecker from StatementVisitor.
public class TypeChecker implements StatementVisitor<MessageBag> {

    private final QuestionCollector<Form> questionCollector;

    private ExpressionTypeChecker expressionTypeChecker;

    public TypeChecker(QuestionCollector<Form> questionCollector) {
        this.questionCollector = questionCollector;
    }

    public MessageBag checkForm(Form form) {
        Questions questions = questionCollector.collect(form);

        // TODO pass the symbol table as a context parameter in the expr visitor
        this.expressionTypeChecker = new ExpressionTypeChecker(createSymbolTable(questions));

        return checkIdentifier(form.getName())
                .merge(checkQuestionDuplicates(questions))
                .merge(checkQuestionLabelsDuplicates(questions))
                .merge(checkStatements(form.getStatements()));
    }

    private MessageBag checkIdentifier(Identifier identifier) {
        MessageBag messages = new TypeCheckMessages();
        if (identifier.toString().isEmpty()) {
            messages.addError("Form name cannot be empty", identifier);
        }

        return messages;
    }

    @Override
    public MessageBag visit(IfThen ifThen) {
        MessageBag ifConditionMessages = checkIfCondition(ifThen.getCondition());
        MessageBag ifThenMessages = checkStatements(ifThen.getThenStatements());

        return ifConditionMessages.merge(ifThenMessages);
    }

    @Override
    public MessageBag visit(IfThenElse ifThenElse) {
        MessageBag ifMessages = checkIfCondition(ifThenElse.getCondition());
        MessageBag ifThenMessages = checkStatements(ifThenElse.getThenStatements());
        MessageBag ifThenElseMessages = checkStatements(ifThenElse.getElseStatements());

        return ifMessages.merge(ifThenMessages).merge(ifThenElseMessages);
    }

    @Override
    public MessageBag visit(Question question) {
        return checkQuestionText(question).merge(checkDefaultValue(question));
    }

    private MessageBag checkQuestionText(Question question) {
        MessageBag messages = new TypeCheckMessages();

        if (question.getQuestionText().toString().isEmpty()) {
            messages.addError("No question text found", question.getQuestionText());
        }

        return messages;
    }

    private MessageBag checkDefaultValue(Question question) {
        MessageBag messages = new TypeCheckMessages();

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

        return messages;
    }

    private SymbolTable createSymbolTable(List<Question> questions) {
        SymbolTable symbolTable = new HashMapSymbolTable();

        for (Question question : questions) {
            symbolTable.put(question.getId(), question.getType());
        }

        return symbolTable;
    }

    private MessageBag checkStatements(List<Statement> statements) {
        MessageBag messages = new TypeCheckMessages();

        for (Statement statement : statements) {
            messages = messages.merge(statement.accept(this));
        }

        return messages;
    }

    private MessageBag checkQuestionDuplicates(Questions questions) {
        MessageBag messages = new TypeCheckMessages();

        for (Question question : questions) {
            if (questions.hasDuplicates(question)) {
                messages.addError("Question '" + question.getId() + "' has duplicate(s)", question);
            }
        }

        return messages;
    }

    private MessageBag checkQuestionLabelsDuplicates(Questions questions) {
        MessageBag messages = new TypeCheckMessages();

        for(Question question : questions) {
            if (questions.hasLabelDuplicates(question)) {
                messages.addError("Question '" + question.getId() + "' label has duplicate(s)", question);
            }
        }

        return messages;
    }

    private MessageBag checkIfCondition(Expression condition) {
        MessageBag messages = new TypeCheckMessages();

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

        return messages;
    }
}
