package visitor;

public abstract class Value {
    public void accept(VisitorBase v){
        v.visit(this);
    }

    public abstract double asDouble();
    public abstract int asIntgeger();
}
