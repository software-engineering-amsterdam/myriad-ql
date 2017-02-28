package org.ql.typechecker;

import org.ql.ast.Expression;
import org.ql.ast.Form;
import org.ql.ast.Identifier;
import org.ql.ast.Statement;
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
import org.ql.typechecker.statement.StatementProxyVisitor;

import java.util.List;

public class TypeChecker implements ITypeChecker {

    private final QuestionCollector<Form> questionCollector;

    private ExpressionTypeChecker expressionTypeChecker;
    private StatementVisitor<MessageBag> visitor;

    public TypeChecker(QuestionCollector<Form> questionCollector) {
        this.questionCollector = questionCollector;
        visitor = new StatementProxyVisitor(this);
    }

    // TODO pass the message bag as argument
    @Override
    public MessageBag checkForm(Form form) {
        Questions questions = questionCollector.collect(form);

        // TODO pass the symbol table as a context parameter in the expr visitor
        this.expressionTypeChecker = new ExpressionTypeChecker(createSymbolTable(questions));

        return mergeMessages(
                checkIdentifier(form.getName()),
                checkQuestionDuplicates(questions),
                checkQuestionLabelsDuplicates(questions),
                checkStatements(form.getStatements())
        );
    }

    @Override
    public MessageBag checkQuestion(Question question) {
        return mergeMessages(
                checkQuestionText(question),
                checkDefaultValue(question)
        );
    }

    @Override
    public MessageBag checkIfThenElse(IfThenElse ifThenElse) {
        MessageBag ifThenMessages = checkStatements(ifThenElse.getThenStatements());
        MessageBag ifThenElseMessages = checkStatements(ifThenElse.getElseStatements());
        MessageBag ifConditionMessages = checkIfCondition(ifThenElse.getCondition());

        return mergeMessages(
                ifConditionMessages,
                ifThenMessages,
                ifThenElseMessages
        );
    }

    @Override
    public MessageBag checkIfThen(IfThen ifThen) {
        MessageBag ifThenMessages = checkStatements(ifThen.getThenStatements());
        MessageBag ifConditionMessages = checkIfCondition(ifThen.getCondition());

        return mergeMessages(ifConditionMessages, ifThenMessages);
    }

    @Override
    public MessageBag checkQuestionText(Question question) {
        MessageBag messages = new TypeCheckMessages();

        if (question.getQuestionText().toString().isEmpty()) {
            messages.addError("No question text found", question.getQuestionText());
        }

        return messages;
    }

    @Override
    public MessageBag checkDefaultValue(Question question) {
        MessageBag messages = new TypeCheckMessages();

        if (question.getDefaultValue() != null) {
            try {
                Type value = question.getDefaultValue().accept(expressionTypeChecker);
                if (!question.getType().toString().equals(value.toString())) {
                    messages.addError(new TypeMismatchException(question.getType(), value));
                }
            } catch (TypeError typeError) {
                messages.addError(typeError);
            } catch (Throwable throwable) {
                messages.addError("Unrecognized error occurred: " + throwable, question);
            }
        }

        return messages;
    }

    @Override
    public MessageBag checkStatements(List<Statement> statements) {
        MessageBag[] messageBags = new MessageBag[statements.size()];

        for (int i = 0; i < statements.size(); i++) {
            messageBags[i] = statements.get(i).accept(visitor);
        }

        return mergeMessages(messageBags);
    }

    @Override
    public MessageBag checkQuestionDuplicates(Questions questions) {
        MessageBag messages = new TypeCheckMessages();

        for (Question question : questions) {
            if (questions.hasDuplicates(question)) {
                messages.addError("Question '" + question.getId() + "' has duplicate(s)", question);
            }
        }

        return messages;
    }

    @Override
    public MessageBag checkQuestionLabelsDuplicates(Questions questions) {
        MessageBag messages = new TypeCheckMessages();

        for (Question question : questions) {
            if (questions.hasLabelDuplicates(question)) {
                messages.addError("Question '" + question.getId() + "' label has duplicate(s)", question);
            }
        }

        return messages;
    }

    @Override
    public MessageBag checkIfCondition(Expression condition) {
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

    @Override
    public MessageBag checkIdentifier(Identifier identifier) {
        MessageBag messages = new TypeCheckMessages();
        if (identifier.toString().isEmpty()) {
            messages.addError("Form name cannot be empty", identifier);
        }

        return messages;
    }

    private SymbolTable createSymbolTable(List<Question> questions) {
        SymbolTable symbolTable = new HashMapSymbolTable();

        for (Question question : questions) {
            symbolTable.declare(question.getId(), question.getType());
        }

        return symbolTable;
    }

    private MessageBag mergeMessages(MessageBag... messageBags) {
        MessageBag finalBag = new TypeCheckMessages();

        for (MessageBag messageBag : messageBags) {
            finalBag.addErrors(messageBag.getErrors());
        }

        return finalBag;
    }
}
