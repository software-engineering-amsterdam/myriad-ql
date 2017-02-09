package UvA.Gamma.Models;

import java.util.ArrayList;

/**
 * Created by Tjarco on 08-02-17.
 */

public class Form {
    private ArrayList<Input> inputs;

    public Form(){
        inputs = new ArrayList<>();
    }

    public void addInput(Input input){
        inputs.add(input);
    }

    public ArrayList<Input> getInputs() {
        return inputs;
    }
}
