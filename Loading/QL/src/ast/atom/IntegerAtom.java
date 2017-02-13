package ast.atom;

public class IntegerAtom extends Atom {

    public IntegerAtom(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }
    
	private final Integer value;
}