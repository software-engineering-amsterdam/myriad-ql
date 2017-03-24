package org.ql.gui.widgets.container;

import org.ql.ast.identifier.Identifier;
import org.ql.ast.statement.Question;
import org.ql.gui.ValueReviser;
import org.ql.gui.widgets.GUIWidget;
import org.qls.ast.widget.Widget;

import java.util.HashMap;
import java.util.Map;

public class GUIWidgetContainer {
    private final DefaultWidgetFactory defaultWidgetFactory;
    private final CustomWidgetFactory customWidgetFactory;
    private final Map<Identifier, GUIWidget> widgets = new HashMap<>();

    public GUIWidgetContainer(ValueReviser valueReviser) {
        customWidgetFactory = new CustomWidgetFactory(valueReviser);
        defaultWidgetFactory = new DefaultWidgetFactory(valueReviser);
    }

    public boolean isAlreadyPersisted(Question question) {
        return widgets.containsKey(question.getId());
    }

    public GUIWidget getPersistedWidget(Question question) {
        return widgets.get(question.getId());
    }

    public GUIWidget retrieveWidget(Question question, Widget widget) {
        return getWidgetWithPersistence(question, customWidgetFactory.createWidget(question, widget));
    }

    public GUIWidget retrieveDefaultWidget(Question question) {
        return getWidgetWithPersistence(question, defaultWidgetFactory.createWidget(question));
    }

    private GUIWidget getWidgetWithPersistence(Question question, GUIWidget widget) {
        Identifier questionId = question.getId();
        if (!widgets.containsKey(questionId)) {
            widgets.put(questionId, widget);
        }
        return widgets.get(questionId);
    }
}
