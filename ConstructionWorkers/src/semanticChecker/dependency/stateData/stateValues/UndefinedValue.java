package semanticChecker.dependency.stateData.stateValues;

/**
 * Created by LGGX on 22-Feb-17.
 */
public class UndefinedValue extends StateValue{

    private final String value;

    public UndefinedValue() {
        this.value = "Unknown";
    }

    @Override
    public String getValue() {
        return value;
    }

}
