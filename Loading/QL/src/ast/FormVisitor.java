package ast;

public interface FormVisitor {

    void visit(Form form);
    void visit(Block block);
    void visit(BlockItem blockItem);
    void visit(Question question);
    void visit(Statement statement);
    void visit(ComputedQuestion question);
}
