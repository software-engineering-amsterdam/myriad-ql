package org.uva.taxfree.qls;

import org.uva.taxfree.ql.model.SourceInfo;

import java.util.List;

public class Page {

    private final String mName;
    private final List<Section> mSections;
    private final SourceInfo mSourceInfo;

    public Page(String name, List<Section> sections, SourceInfo sourceInfo) {
        mName = name;
        mSections = sections;
        mSourceInfo = sourceInfo;
    }
}
