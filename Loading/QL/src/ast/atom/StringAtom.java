package ast.atom;

public class StringAtom extends Atom {
    private String value;

    public StringAtom(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}