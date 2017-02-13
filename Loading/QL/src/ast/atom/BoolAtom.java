package ast.atom;

import java.lang.Boolean;

public class BoolAtom extends Atom {
    private Boolean value;

    public BoolAtom(Boolean value) {
        this.value = value;
    }

    public Boolean getValue() {
        return this.value;
    }
}