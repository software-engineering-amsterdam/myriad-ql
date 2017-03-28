package org.uva.taxfree.qls;

import java.util.List;

public class Page {

    private final String mName;
    private final List<Section> mSections;

    public Page(String name, List<Section> sections) {
        mName = name;
        mSections = sections;
    }
}
