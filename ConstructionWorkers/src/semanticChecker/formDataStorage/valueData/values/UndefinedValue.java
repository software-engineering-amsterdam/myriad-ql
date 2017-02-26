package semanticChecker.formDataStorage.valueData.values;

/**
 * Created by LGGX on 22-Feb-17.
 */
public class UndefinedValue extends Value {

    private final String value;

    public UndefinedValue() {
        this.value = "Undefined";
    }

    @Override
    public boolean undefined() {
        return true;
    }

    @Override
    public String getValue() {
        return value;
    }

}
