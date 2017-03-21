package org.qls.ast.widget.default_widget;

import org.junit.Test;
import org.ql.ast.type.BooleanType;
import org.qls.ast.widget.CheckboxWidget;
import org.qls.ast.widget.default_widget.style.ColorRule;
import org.qls.ast.widget.default_widget.style.StyleRule;

import java.util.HashSet;

import static org.junit.Assert.*;

public class DefaultWidgetWithStyleTest {
    @Test
    public void shouldPersistUniquenessOfStyleRulesWithinAWidget() {
        HashSet<StyleRule> styleRules = new HashSet<StyleRule>() {{
            add(new ColorRule("#000000"));
            add(new ColorRule("#FFFFFF"));
            add(new ColorRule("#333333"));
        }};

        DefaultWidgetWithStyle widget = new DefaultWidgetWithStyle(new BooleanType(), new CheckboxWidget(), styleRules);
        ColorRule styleRule = (ColorRule) widget.getStyleRules().iterator().next();

        assertSame(1, widget.getStyleRules().size());
        assertEquals("#000000", styleRule.getHexCode());
    }
}