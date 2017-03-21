package org.qls.ast.widget.default_widget;

import org.ql.ast.type.Type;
import org.qls.ast.widget.Widget;
import org.qls.ast.widget.default_widget.style.StyleRule;

import java.util.Set;

public class DefaultWidgetWithStyle extends DefaultWidget {
    private final Set<StyleRule> styleRules;

    public DefaultWidgetWithStyle(Type type, Widget widget, Set<StyleRule> styleRules) {
        super(type, widget);
        this.styleRules = styleRules;
    }

    public Set<StyleRule> getStyleRules() {
        return styleRules;
    }
}
