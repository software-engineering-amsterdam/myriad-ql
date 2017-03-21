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

        for (DefaultWidget widget : page.getDefaultWidgets()) {
            check(widget.getWidget().getType(), widget.getType());
        }
    }

    @Override
    public void visit(Section section) {
        for (Question question : section.getQuestions()) {
            question.accept(this);
        }

        for (DefaultWidget widget : section.getDefaultWidgets()) {
            check(widget.getWidget().getType(), widget.getType());
        }
    }

    @Override
    public void visit(Question question) {

    }

    @Override
    public void visit(QuestionWithWidget question) {
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
