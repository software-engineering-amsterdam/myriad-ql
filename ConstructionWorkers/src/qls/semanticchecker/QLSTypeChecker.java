package qls.semanticchecker;

import ql.astnodes.LineNumber;
import ql.astnodes.types.Type;
import ql.semanticchecker.messagehandling.MessageData;
import ql.semanticchecker.messagehandling.errors.qlserrors.DuplicateQLSQuestionPlacementError;
import ql.semanticchecker.messagehandling.errors.qlserrors.NotAllQuestionsDefinedError;
import ql.semanticchecker.messagehandling.errors.qlserrors.UndefinedQuestionReferenceError;
import ql.semanticchecker.messagehandling.errors.qlserrors.UnsupportedWidgetTypeError;
import qls.astnodes.StyleQuestion;
import qls.astnodes.StyleSheet;
import qls.astnodes.sections.DefaultStyle;
import qls.astnodes.sections.Section;
import qls.astnodes.styles.Color;
import qls.astnodes.styles.Font;
import qls.astnodes.styles.FontSize;
import qls.astnodes.styles.Width;
import qls.astnodes.visitors.StyleSheetVisitor;
import qls.astnodes.widgets.*;

import java.util.*;

public class QLSTypeChecker implements StyleSheetVisitor<Void> {

    private final MessageData messages;
    private final Map<String, Type> identifierMap;
    private final List<StyleQuestion> qlsQuestions;

    public QLSTypeChecker(MessageData messages, Map<String, Type> identifierMap, StyleSheet st) {
        this.messages = messages;
        this.identifierMap = identifierMap;
        this.qlsQuestions = new ArrayList<>();

        st.accept(this);

        checkQLSQuestionPlacement();
        checkWidgetTypes();
    }

    private void checkWidgetTypes() {

        for (StyleQuestion question : qlsQuestions) {

            QLSWidget widget = question.getWidget();
            List<Type> supportedTypes = widget.getSupportedQuestionTypes();
            Type questionType = identifierMap.get(question.getName());

            if (questionType != null && !supportedTypes.contains(questionType)) {
                messages.addError(new UnsupportedWidgetTypeError(question.getLineNumber(), question.getName()));
            }
        }
    }
    private void checkQLSQuestionPlacement() {

        final Set<StyleQuestion> duplicateQuestions = new HashSet();
        final Set<StyleQuestion> set1 = new HashSet();

        for (StyleQuestion question : qlsQuestions) {
            if (!set1.add(question)) {
                duplicateQuestions.add(question);
            }
        }

        if (duplicateQuestions.size() > 0) {
            for( StyleQuestion question :  duplicateQuestions) {
                messages.addError(new DuplicateQLSQuestionPlacementError(question.getLineNumber(), question.getName()));
            }
        }

        List<String> styleQuestionList = new ArrayList<>();
        for (StyleQuestion question : qlsQuestions) {
            styleQuestionList.add(question.getName());
        }

        for(String key: identifierMap.keySet()) {
            if (!qlsQuestions.contains(key)) {
                messages.addError(new NotAllQuestionsDefinedError(new LineNumber(1), key));
            }

        }

    }

    @Override
    public Void visit(StyleSheet styleSheet) {
        return null;
    }

    @Override
    public Void visit(Section section) {
        return null;
    }

    @Override
    public Void visit(DefaultStyle section) {
        return null;
    }

    @Override
    public Void visit(StyleQuestion question) {
        qlsQuestions.add(question);
        if (identifierMap.get(question.getName()) == null) {
            messages.addError(new UndefinedQuestionReferenceError(question.getLineNumber(), question.getName()));
        }

        return null;
    }

    @Override
    public Void visit(Color style) {
        return null;
    }

    @Override
    public Void visit(Width style) {
        return null;
    }

    @Override
    public Void visit(Font style) {
        return null;
    }

    @Override
    public Void visit(FontSize style) {
        return null;
    }

    @Override
    public Void visit(QLSCheckBox widget) {
        return null;
    }

    @Override
    public Void visit(QLSRadio widget) {
        return null;
    }

    @Override
    public Void visit(QLSSlider widget) {
        return null;
    }

    @Override
    public Void visit(QLSSpinBox widget) {
        return null;
    }

    @Override
    public Void visit(QLSTextBox widget) {
        return null;
    }

    @Override
    public Void visit(QLSDropdown widget) {
        return null;
    }

    @Override
    public Void visit(QLSUndefinedWidget widget) {
        return null;
    }
}
