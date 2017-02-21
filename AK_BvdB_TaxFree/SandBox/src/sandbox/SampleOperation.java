package sandbox;

public class SampleOperation {
    public void add(Int a){
        a.setValue(a.getValue()+10);
        System.out.println("Called Int");
    }

    public void add(Real a){
        a.setValue(); += 15.0;
        System.out.println("Called Double");
    }

    public void add(Number b){
        b = b.intValue() + 5;
        System.out.print("Called Number");
    }
}
