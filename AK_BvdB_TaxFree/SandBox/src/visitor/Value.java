package visitor;

public abstract class Value {
    public void accept(VisitorBase v){
        v.visit(this);
    }
}
