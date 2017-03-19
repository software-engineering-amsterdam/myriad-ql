package org.qls.ast.widget.defaultWidget;

import org.ql.ast.type.Type;
import org.qls.ast.widget.Widget;
import org.qls.ast.widget.defaultWidget.style.StyleRule;

import java.util.List;

public class DefaultWidgetWithStyle extends DefaultWidget {
    private List<StyleRule> styleRules;

    public DefaultWidgetWithStyle(Type type, Widget widget, List<StyleRule> styleRules) {
        super(type, widget);
        this.styleRules = styleRules;
    }

    public List<StyleRule> getStyleRules() {
        return styleRules;
    }

    public void setStyleRules(List<StyleRule> styleRules) {
        this.styleRules = styleRules;
    }
}
