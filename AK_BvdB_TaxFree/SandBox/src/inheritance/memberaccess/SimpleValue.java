package inheritance.memberaccess;

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

    public boolean equals(SimpleValue b) {
        return mValue == b.mValue;
    }

    int getValue(){
        return mValue;
    }

}
