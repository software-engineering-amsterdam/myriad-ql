/*
 * Software Construction - University of Amsterdam
 *
 * ./src/qls/astnodes/section/Section.java.
 *
 * Gerben van der Huizen    -   10460748
 * Vincent Erich            -   10384081
 *
 * March, 2017
 */

package qls.astnodes.sections;

import ql.astnodes.LineNumber;
import qls.visitorinterfaces.StyleSheetVisitor;

import java.util.List;

public class Section extends AbstractSection {

    private final String sectionName;
    private final List<Section> sections;
    private final List<DefaultStyle> defaultStyles;
    private final List<StyleQuestion> questions;

    public Section(String name, List<Section> sections, List<DefaultStyle> defaultStyles, List<StyleQuestion> questions,
                   LineNumber lineNumber) {
        super(lineNumber);
        this.sectionName = name;
        this.sections = sections;
        this.defaultStyles = defaultStyles;
        this.questions = questions;
    }

    public List<StyleQuestion> getQuestions() {
        return this.questions;
    }

    public String getName() {
        return this.sectionName;
    }

    public List<Section> getSections() {
        return this.sections;
    }

    public List<DefaultStyle> getDefaultStyles() {
        return this.defaultStyles;
    }

    public <T> T accept(StyleSheetVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
