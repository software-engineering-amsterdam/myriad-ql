package org.uva.taxfree.qls;

import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.ql.model.types.BooleanType;
import org.uva.taxfree.qls.styleoption.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class QlsStyleBuilder {
    private final File mInputFile;

    public QlsStyleBuilder(File inputFile) {
        mInputFile = inputFile;
    }

    public QlsStyle generateStyle() {

        // TODO parse and generate the styles.
        SourceInfo tmpSourceInfo = new SourceInfo(0, 0, 0, 0);
        List<StyleOption> booleanStyleOptions = new ArrayList<>();
        booleanStyleOptions.add(new FontStyleOption("Arial", tmpSourceInfo));
        booleanStyleOptions.add(new FontSizeStyleOption(20, tmpSourceInfo));
        booleanStyleOptions.add(new ColorStyleOption("#FFFF00", tmpSourceInfo));
        booleanStyleOptions.add(new BackgroundColorStyleOption("#CCCC", tmpSourceInfo));

        List<StyleOption> stringStyleOptions = new ArrayList<>();
        stringStyleOptions.add(new FontStyleOption("Arial", tmpSourceInfo));
        stringStyleOptions.add(new FontSizeStyleOption(14, tmpSourceInfo));
        stringStyleOptions.add(new ColorStyleOption("#00FFFF", tmpSourceInfo));
        stringStyleOptions.add(new BackgroundColorStyleOption("#FFAA00", tmpSourceInfo));
        List<Page> pages = new ArrayList<>();
        List<Section> sections = new ArrayList<>();

        List<QuestionStyle> questionStyles = new ArrayList<>();
        sections.add(new Section("TaxSection", questionStyles, tmpSourceInfo));
        List<DefaultStyle> defaultStyles = new ArrayList<>();
        defaultStyles.add(new DefaultStyle(new BooleanType(), stringStyleOptions, tmpSourceInfo));
        pages.add(new Page("TaxPage", sections, defaultStyles, tmpSourceInfo));
        return new QlsStyle(pages);
    }
}
