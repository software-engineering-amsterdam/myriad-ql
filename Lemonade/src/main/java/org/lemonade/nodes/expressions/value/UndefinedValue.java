package org.lemonade.nodes.expressions.value;

import jdk.nashorn.internal.runtime.Undefined;
import org.lemonade.nodes.expressions.Value;
import org.lemonade.nodes.types.QLType;

/**
 *
 */
public class UndefinedValue extends Value<String> {

    public UndefinedValue(QLType type, String value) {
        super(type, value);
    }
}
