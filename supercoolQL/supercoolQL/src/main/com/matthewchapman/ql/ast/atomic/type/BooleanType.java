package com.matthewchapman.ql.ast.atomic.type;

import com.matthewchapman.ql.ast.atomic.Type;
import com.matthewchapman.ql.visitors.TypeVisitor;

/**
 * Created by matt on 08/03/2017.
 *
 * Concrete type class for booleans
 */
public class BooleanType extends Type {

    @Override
    public boolean isCompatible(Type type) {
        return "boolean".equals(type.toString());
    }

    @Override
    public String toString() {
        return "boolean";
    }

    @Override
    public <T, C> T accept(TypeVisitor<T, C> visitor, C context) {
        return visitor.visit(this, context);
    }
}
