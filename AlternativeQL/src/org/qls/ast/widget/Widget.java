package org.qls.ast.widget;

import org.ql.ast.Node;
import org.ql.ast.type.Type;

import java.util.ArrayList;
import java.util.List;

public abstract class Widget extends Node {
    private List<Type> supportedTypes = new ArrayList<>();

    public Widget() {
        initializeSupportedTypes();
    }

    public abstract void initializeSupportedTypes();

    public List<Type> getSupportedTypes() {
        return supportedTypes;
    }
}
