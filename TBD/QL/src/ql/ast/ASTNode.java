package ql.ast;

import ql.ast.visistor.ASTVisitor;

/**
 * Created by Erik on 6-2-2017.
 */
public interface ASTNode {
    <T> T accept(ASTVisitor<T> visitor);
}
