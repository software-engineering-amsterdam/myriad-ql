package org.uva.taxfree.qls;

import org.uva.taxfree.ql.model.types.Type;
import org.uva.taxfree.qls.styleoption.StyleOption;

import javax.swing.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class QlsStyle {
    private final Map<String, String> mWidgetDeclarations;
    private final Map<Type, List<StyleOption>> mVariableStyleDeclarations;
    private final Map<String, String> mQuestionStyleDeclarations;

    public QlsStyle() {
        mWidgetDeclarations = new HashMap<>();
        mVariableStyleDeclarations = new HashMap<>();
        mQuestionStyleDeclarations = new HashMap<>();
    }

    public void addWidgetDeclaration(String likesToPayTaxes, String widgetType) {
        if (mWidgetDeclarations.containsKey(likesToPayTaxes)) {
            throw new AssertionError("This question has already been declared earlier");
        }
        mWidgetDeclarations.put(likesToPayTaxes, widgetType);
    }

    public void addVariableStyleDeclaration(Type variableType, List<StyleOption> styleOptions) {
        if (mVariableStyleDeclarations.containsKey(variableType)) {
            throw new AssertionError("This variable has already been declared");
        }
        mVariableStyleDeclarations.put(variableType, styleOptions);
    }


    public void applyStyle(Type type, JComponent component) {
        Iterator<Map.Entry<Type, List<StyleOption>>> variableDeclarations = mVariableStyleDeclarations.entrySet().iterator();
        while (variableDeclarations.hasNext()) {
            Map.Entry<Type, List<StyleOption>> thisEntry = variableDeclarations.next();
            Type key = thisEntry.getKey();
            if (key.equals(type)) {
                List<StyleOption> styleOptions = thisEntry.getValue();
                for (StyleOption styleOption : styleOptions) {
                    styleOption.applyStyle(component);
                }
            }
        }
    }
}
