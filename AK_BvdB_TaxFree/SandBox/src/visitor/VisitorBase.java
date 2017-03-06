package visitor;

interface VisitorBase {
    void visit(Value a);
    void visit(IntNumber a);
    void visit(DoubleNumber b);
}
