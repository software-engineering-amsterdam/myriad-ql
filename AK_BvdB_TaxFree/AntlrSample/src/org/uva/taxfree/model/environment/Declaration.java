package org.uva.taxfree.model.environment;

public class Declaration {
    private String mValue;
    private String mId;
    private String mLabel;

    public Declaration(String label, String id) {
        mLabel = label;
        mId = id;
    }

    public void setValue(String value) {
        mValue = value;
    }

    public String getValue() {
        return mValue;
    }

    @Override
    public boolean equals(Object obj) {
        return obj.toString().equals(toString());
    }

    @Override
    public String toString() {
        return getId();
    }

    public String getId() {
        return mId;
    }

    public String getLabel() {
        return mLabel;
    }
}
