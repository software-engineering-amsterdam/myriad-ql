package memberaccess;

import sun.java2d.pipe.SpanShapeRenderer;

public class SimpleValue {
    private final int mValue;

    public SimpleValue(int value) {
        mValue = value;
    }

    public SimpleValue(SimpleValue copyFromMe) {
        this(copyFromMe.mValue);
    }


    public boolean isSameAs(SimpleValue b) {
        return mValue == b.mValue;
    }
}
