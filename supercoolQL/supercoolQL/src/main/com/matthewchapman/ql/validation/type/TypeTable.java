package com.matthewchapman.ql.validation.type;

import com.matthewchapman.ql.ast.atomic.Type;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by matt on 20/03/2017.
 */
public class TypeTable {

    private final Map<String, Type> table;

    public TypeTable() {
        this.table = new HashMap<>();
    }

    public void addEntry(String name, Type type) {
        this.table.put(name, type);
    }

    Type getEntryByName(String name) {
        return this.table.get(name);
    }

    public boolean containsEntry(String name) {
        return this.table.containsKey(name);
    }

    public int size() {
        return this.table.size();
    }
}
