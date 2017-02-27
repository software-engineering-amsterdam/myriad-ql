package org.uva.hatt.taxform.ast.visitors;

import org.uva.hatt.taxform.ast.nodes.*;
import org.uva.hatt.taxform.ast.nodes.items.*;
import org.uva.hatt.taxform.ast.nodes.types.*;
import org.uva.hatt.taxform.ast.nodes.types.Boolean;
import org.uva.hatt.taxform.ast.nodes.types.Integer;
import org.uva.hatt.taxform.ast.nodes.types.String;

public interface Visitor {
    public void visit(Form node);
    public void visit(FormId node);
    public void visit(Question node);
    public void visit(Conditional node);
    public void visit(Boolean node);
    public void visit(Integer node);
    public void visit(Money node);
    public void visit(String node);
    public void visit(ValueType node);
}
