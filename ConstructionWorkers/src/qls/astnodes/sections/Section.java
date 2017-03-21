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

    public Section(String sectionName, List<Section> sections, List<DefaultStyle> defaultStyles, List<StyleQuestion> questions,
                   LineNumber lineNumber) {
        super(lineNumber);
        this.sectionName = sectionName;
        this.sections = sections;
        this.defaultStyles = defaultStyles;
        this.questions = questions;
    }

    public String getName() {
        return sectionName;
    }

    public List<Section> getSections() {
        return sections;
    }

    public List<DefaultStyle> getDefaultStyles() {
        return defaultStyles;
    }

    public List<StyleQuestion> getQuestions() {
        return questions;
    }

    public void accept(StyleSheetVisitor visitor) {
        visitor.visit(this);
    }
}
