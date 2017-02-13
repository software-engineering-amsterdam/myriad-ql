package ast.atom;

public class MoneyAtom extends Atom {
    private Float value;

    public MoneyAtom(Float value) {
        this.value = value;
    }

    public Float getValue() {
        return this.value;
    }
}