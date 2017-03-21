package qls.semantic;

import QL.ast.type.Type;
import QL.message.Error;
import qls.ast.*;

public class VerifyTypes implements StylesheetVisitor {
    private final Environment environment;

    VerifyTypes(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void visit(Stylesheet stylesheet) {
        for (Page page : stylesheet.getPages()) {
            page.accept(this);
        }
    }

    @Override
    public void visit(Page page) {
        for (Section section : page.getSections()) {
            section.accept(this);
        }
    }

    @Override
    public void visit(Section section) {
        for (Question question : section.getQuestions()) {
            question.accept(this);
        }
    }

    @Override
    public void visit(Question question) {
        System.out.println("VISIT QUESTION QLS");
    }

    @Override
    public void visit(QuestionWithWidget question) {
        System.out.println("VISIT QUESTION WITH WIDGET QLS");
        System.out.println(question.getName());
        System.out.println();
        System.out.println();

        check(environment.getType(question.getName()), question.getWidget().getType());
    }

    private void check(Type expected, Type actual) {
        if (!expected.equals(actual)) {
            environment.addMessage(new Error("The type " + actual.getKeyWord()
                    + " is not of the expected type: "
                    + expected.getKeyWord(), actual.getLine()));
        }
    }
}
