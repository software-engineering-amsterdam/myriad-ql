package visitor;

import java.util.function.DoubleToLongFunction;

public class IntNumber extends Value {
    @Override
    public void accept(VisitorBase v) {
        v.visit(this);
    }

    public void add(Number n){

    }

}
