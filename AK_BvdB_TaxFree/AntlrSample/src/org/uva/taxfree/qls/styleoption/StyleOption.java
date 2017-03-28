package org.uva.taxfree.qls.styleoption;

import org.uva.taxfree.ql.model.SourceInfo;

import javax.swing.*;

public abstract class StyleOption {

    private SourceInfo mSourceInfo;

    public StyleOption(SourceInfo sourceInfo) {
        mSourceInfo = sourceInfo;
    }

    public abstract void applyStyle(JComponent component);
}
