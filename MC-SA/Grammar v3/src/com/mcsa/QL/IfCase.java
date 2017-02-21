package com.mcsa.QL;

import java.util.ArrayList;

/**
 * Created by sotos on 21/2/2017.
 */
public class IfCase {
    Object left;
    Object right;
    Object token;

    public IfCase() {
        this.left = null;
        this.right = null;
        this.token = null;
    }

    public void addLeft (Object item) {
        this.left = item;
    }

    public void addRight(Object item) {
        this.right = item;
    }

    public void addToken(Object item) {
        this.token = item;
    }

    public Object getRight() {
        return this.right;
    }
}
