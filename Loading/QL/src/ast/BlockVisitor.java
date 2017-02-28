package ast;

import org.antlr.runtime.tree.CommonTree;

public interface BlockVisitor {

    void visit(Block block);
    void visit(Question question);
    void visit(Statement statement);
    void visit(ComputedQuestion question);
}
