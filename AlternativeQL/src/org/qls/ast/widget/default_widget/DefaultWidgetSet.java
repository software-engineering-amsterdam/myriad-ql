package org.qls.ast.widget.default_widget;

import org.ql.ast.type.Type;
import org.qls.ast.page.CustomWidgetQuestion;

import java.util.HashSet;
import java.util.Set;

public class DefaultWidgetSet {
    private final Set<DefaultWidget> widgetSet = new HashSet<>();

    public void add(DefaultWidget defaultWidget) {
        widgetSet.add(defaultWidget);
    }

    public void mergeWith(DefaultWidgetSet defaultWidgets) {
        widgetSet.addAll(defaultWidgets.widgetSet);
    }

    public DefaultWidget lookupByType(Type type) {
        for (DefaultWidget widget : widgetSet) {
            if (widget.getType().equals(type)) {
                return widget;
            }
        }

        return null;
    }

    public boolean existsByType(Type type) {
        return lookupByType(type) != null;
    }

    public int size() {
        return widgetSet.size();
    }
}
