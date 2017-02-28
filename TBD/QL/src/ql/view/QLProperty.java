package ql.view;

import javafx.beans.property.SimpleObjectProperty;

/**
 * Created by Erik on 27-2-2017.
 */
public class QLProperty<T> extends SimpleObjectProperty<T> {
    private final String key;

    public QLProperty(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
