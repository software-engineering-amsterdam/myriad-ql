package org.ql.ast.type;

import org.ql.ast.Node;
import org.ql.ast.Visitor;

public enum Type implements Node {
    BOOLEAN {
        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }, FLOAT {
        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }, INTEGER {
        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }, STRING {
        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }, MONEY {
        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }, DATE {
        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }
}
