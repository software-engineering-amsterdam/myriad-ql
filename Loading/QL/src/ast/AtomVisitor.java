package ast;

import ast.atom.BoolAtom;
import ast.atom.StringAtom;

public interface AtomVisitor {

    void visit(BoolAtom atom);
    void visit(Integer atom);
    void visit(StringAtom atom);
}
