package qls.astnodes.sections;

import ql.astnodes.LineNumber;
import qls.astnodes.visitors.StyleSheetVisitor;

import java.util.List;

/**
 * Created by LGGX on 02-Mar-17.
 */
public class Section extends AbstractSection {

    private final String sectionName;
    private final List<Section> sections;
    private final List<DefaultStyle> defaultStyles;
    private final List<StyleQuestion> questions;

    public Section(String name,
                   List<Section> sections,
                   List<DefaultStyle> defaultStyles,
                   List<StyleQuestion> questions,
                   LineNumber lineNumber)
    {
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

    public void addDefaultStyle(DefaultStyle defaultStyle) {
        this.defaultStyles.add(defaultStyle);
    }

    public <T> T accept(StyleSheetVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
