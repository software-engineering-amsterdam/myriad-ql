/*
 * Software Construction - University of Amsterdam
 *
 * ./src/qls/semanticchecker/QLSTypehecker.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package qls.semanticchecker;

import ql.astnodes.LineNumber;
import ql.astnodes.types.Type;
import ql.semanticchecker.messagehandling.MessageData;
import ql.semanticchecker.messagehandling.errors.qlserrors.DuplicateQLSQuestionPlacementError;
import ql.semanticchecker.messagehandling.errors.qlserrors.NotAllQuestionsDefinedError;
import ql.semanticchecker.messagehandling.errors.qlserrors.UndefinedQuestionReferenceError;
import ql.semanticchecker.messagehandling.errors.qlserrors.UnsupportedWidgetTypeError;
import qls.astnodes.sections.StyleQuestion;
import qls.astnodes.StyleSheet;
import qls.astnodes.sections.DefaultStyle;
import qls.astnodes.sections.Section;
import qls.visitorinterfaces.StyleSheetVisitor;
import qls.astnodes.widgets.*;

import java.util.*;

public class QLSTypeChecker implements StyleSheetVisitor {

    private final MessageData messages;
    private final Map<String, Type> identifierToTypeMap;
    private final List<StyleQuestion> qlsQuestions;

    private QLSWidget currentDefaultWidget = new QLSUndefinedWidget(null);

    public QLSTypeChecker(MessageData messages, Map<String, Type> identifierToTypeMap, StyleSheet styleSheet) {
        this.messages = messages;
        this.identifierToTypeMap = identifierToTypeMap;
        this.qlsQuestions = new ArrayList<>();

        styleSheet.accept(this);

        checkWidgetsWithDefinedTypes();
        checkDuplicateQuestionPlacement();
        checkForUndefinedQuestions();
    }

    private void checkWidgetsWithDefinedTypes() {

        for (StyleQuestion question : qlsQuestions) {

            QLSWidget widget = question.getWidget();
            if (!widget.isUndefined()) {
                List<Type> supportedTypes = widget.getSupportedQuestionTypes();
                Type questionType = identifierToTypeMap.get(question.getName());

                if (!supportedTypes.contains(questionType)) {
                    messages.addError(new UnsupportedWidgetTypeError(question.getLineNumber(), question.getName()));
                }
            }
        }
    }

    private void checkDuplicateQuestionPlacement() {
        final Set<StyleQuestion> duplicateQuestions = new HashSet<>();
        final Set<String> uniqueQuestionIdentifierNames = new HashSet<>();

        for (StyleQuestion question : qlsQuestions) {
            if (!uniqueQuestionIdentifierNames.add(question.getName())) {
                duplicateQuestions.add(question);
            }
        }

        if (duplicateQuestions.size() > 0) {
            for( StyleQuestion question :  duplicateQuestions) {
                messages.addError(new DuplicateQLSQuestionPlacementError(question.getLineNumber(), question.getName()));
            }
        }
    }

    private void checkForUndefinedQuestions() {
        List<String> styleQuestionList = new ArrayList<>();

        for (StyleQuestion question : qlsQuestions) {
            styleQuestionList.add(question.getName());
        }

        for (String key : identifierToTypeMap.keySet()) {
            if (!styleQuestionList.contains(key)) {
                messages.addError(new NotAllQuestionsDefinedError(new LineNumber(1), key));
            }
        }
    }

    @Override
    public void visit(StyleSheet styleSheet) {
        for (Section section : styleSheet.getSections()) {
            section.accept(this);
        }

        for (DefaultStyle style : styleSheet.getDefaultStyle()) {
            if (!style.getWidget().isUndefined()) {
                currentDefaultWidget = style.getWidget();
            }
        }
    }

    @Override
    public void visit(Section section) {
        for (Section subSection : section.getSections()) {
            subSection.accept(this);
        }

        for (DefaultStyle style : section.getDefaultStyles()) {
            if (!style.getWidget().isUndefined()) {
                currentDefaultWidget = style.getWidget();
            }
        }

        for (StyleQuestion question : section.getQuestions()) {
            question.accept(this);
        }

    }

    @Override
    public void visit(DefaultStyle section) {
    }

    @Override
    public void visit(StyleQuestion question) {
        qlsQuestions.add(question);
        if (identifierToTypeMap.get(question.getName()) == null) {
            messages.addError(new UndefinedQuestionReferenceError(question.getLineNumber(), question.getName()));
        }

        QLSWidget widget = question.getWidget();

        if (widget.isUndefined()) {
            List<Type> supportedTypes = currentDefaultWidget.getSupportedQuestionTypes();
            Type questionType = identifierToTypeMap.get(question.getName());

            if (!supportedTypes.contains(questionType)) {
                messages.addError(new UnsupportedWidgetTypeError(question.getLineNumber(), question.getName()));
            }
        }
    }
}
