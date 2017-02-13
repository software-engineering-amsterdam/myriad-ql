package UvA.Gamma.Models;

import java.util.ArrayList;

/**
 * Created by Tjarco on 08-02-17.
 */

public class QLForm {
    private ArrayList<Input> inputs;
    private String identifier;

    public QLForm(String identifier){
        inputs = new ArrayList<>();
        this.identifier = identifier;
    }

    public void addInput(Input input){
        inputs.add(input);
    }

    public ArrayList<Input> getInputs() {
        return inputs;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
