package org.uva.taxfree.qls;

import org.uva.taxfree.ql.model.types.BooleanType;
import org.uva.taxfree.ql.model.types.StringType;
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
        QlsStyle qlsStyle = new QlsStyle();
        List<StyleOption> booleanStyleOptions = new ArrayList<>();
        booleanStyleOptions.add(new FontStyleOption("Arial"));
        booleanStyleOptions.add(new FontSizeStyleOption(20));
        booleanStyleOptions.add(new ColorStyleOption("#FFFF00"));
        booleanStyleOptions.add(new BackgroundColorStyleOption("#CCCC"));
        qlsStyle.addVariableStyleDeclaration(new BooleanType(), booleanStyleOptions);

        List<StyleOption> stringStyleOptions = new ArrayList<>();
        stringStyleOptions.add(new FontStyleOption("Arial"));
        stringStyleOptions.add(new FontSizeStyleOption(14));
        stringStyleOptions.add(new ColorStyleOption("#00FFFF"));
        stringStyleOptions.add(new BackgroundColorStyleOption("#FFAA00"));
        qlsStyle.addVariableStyleDeclaration(new StringType(), stringStyleOptions);
        return qlsStyle;
    }
}
