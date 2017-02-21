package types;

import types.Int;
import types.Real;

public class SampleOperation {
    public void add(Int a){
        a.setValue(10);
        System.out.println("Called Int");
    }

    public void add(Real a){
        a.setValue(15.0);
        System.out.println("Called Double");
    }

    public void add(){
        System.out.print("Called Number");
    }
}
