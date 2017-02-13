package ast.atom;

public class DecimalAtom extends Atom {
    private Float value;

    public DecimalAtom(Float value) {
        this.value = value;
    }

    public Float getValue() {
        return this.value;
    }
}