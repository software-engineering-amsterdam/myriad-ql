package qls.astnodes;

import ql.astnodes.LineNumber;
import ql.astnodes.Node;
import qls.astnodes.sections.DefaultStyle;
import qls.astnodes.sections.Section;
import qls.astnodes.visitors.StyleSheetVisitor;

import java.util.List;

/**
 * Created by LGGX on 02-Mar-17.
 */
public class StyleSheet extends Node {

    private final String styleSheetName;
    private final List<Section> sections;
    private final List<DefaultStyle> defaultStyle;

    public StyleSheet(List<Section> sections, List<DefaultStyle> defaults, String name, LineNumber lineNumber) {
        super(lineNumber);
        this.sections = sections;
        this.defaultStyle = defaults;
        this.styleSheetName = name;
    }

    public String getName() {
        return this.styleSheetName;
    }

    public List<Section> getSections() {
        return this.sections;
    }

    public List<DefaultStyle> getDefaultStyle() {
        return this.defaultStyle;
    }

    public void addDefaultStyle(DefaultStyle defaultStyle) {
        this.defaultStyle.add(defaultStyle);
    }


    public <T> T accept(StyleSheetVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
