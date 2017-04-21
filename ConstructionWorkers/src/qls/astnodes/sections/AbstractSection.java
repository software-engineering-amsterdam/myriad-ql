/*
 * Software Construction - University of Amsterdam
 *
 * ./src/qls/astnodes/sections/AbstractSection.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package qls.astnodes.sections;

import ql.astnodes.LineNumber;
import ql.astnodes.Node;
import qls.visitorinterfaces.StyleSheetVisitor;

import java.util.List;

public abstract class AbstractSection extends Node {

    private final String sectionName;
    private final List<Section> sections;
    private final List<DefaultStyle> defaultStyles;

    AbstractSection(String sectionName, List<Section> sections, List<DefaultStyle> defaultStyles,
                    LineNumber lineNumber) {
        super(lineNumber);
        this.sectionName = sectionName;
        this.sections = sections;
        this.defaultStyles = defaultStyles;
    }

    public String getName() {
        return sectionName;
    }

    public List<Section> getSections() {
        return sections;
    }

    public List<DefaultStyle> getDefaultStyle() {
        return defaultStyles;
    }

    public abstract void accept(StyleSheetVisitor visitor);
}
