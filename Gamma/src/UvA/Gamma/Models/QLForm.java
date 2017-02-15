package UvA.Gamma.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tjarco on 08-02-17.
 */

public class QLForm {
    private List<QLInput> inputs;
    private String identifier;

    public QLForm(String identifier){
        inputs = new ArrayList<>();
        this.identifier = identifier;
    }

    public void addInput(QLInput input){
        inputs.add(input);
    }

    public List<QLInput> getInputs() {
        return inputs;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
