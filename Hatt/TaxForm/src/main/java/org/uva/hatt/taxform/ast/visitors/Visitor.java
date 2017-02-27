package org.uva.hatt.taxform.ast.visitors;

import org.uva.hatt.taxform.ast.nodes.*;
import org.uva.hatt.taxform.ast.nodes.items.*;
import org.uva.hatt.taxform.ast.nodes.types.*;
import org.uva.hatt.taxform.ast.nodes.types.Boolean;
import org.uva.hatt.taxform.ast.nodes.types.Integer;
import org.uva.hatt.taxform.ast.nodes.types.String;

public interface Visitor {
    public Form visit(Form node);
    public FormId visit(FormId node);
    public Question visit(Question node);
    public Conditional visit(Conditional node);
    public Boolean visit(Boolean node);
    public Integer visit(Integer node);
    public Money visit(Money node);
    public String visit(String node);
    public ValueType visit(ValueType node);
}
