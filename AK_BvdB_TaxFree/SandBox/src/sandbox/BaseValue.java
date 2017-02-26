package sandbox;

public class BaseValue {
    void printClassName(){
        System.out.println("-->" + this.getClass().getName() + "<--");
    }

    public String getType(){
        return "base";
    }
}
