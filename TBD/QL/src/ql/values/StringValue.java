package ql.values;

/**
 * Created by Erik on 21-2-2017.
 */
public class StringValue extends Value<String>{
    private final String value;

    public StringValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StringValue that = (StringValue) o;

        return value != null ? value.equals(that.value) : that.value == null;
    }
}
