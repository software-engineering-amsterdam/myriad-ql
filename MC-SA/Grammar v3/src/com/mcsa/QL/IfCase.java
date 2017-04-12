package com.mcsa.QL;

import java.util.ArrayList;

/**
 * Created by sotos on 21/2/2017.
 */
public class IfCase {
    IfCase left;
    IfCase right;
    Object token;

    public IfCase() {
        this.left = null;
        this.right = null;
        this.token = null;
    }

    public void addLeft (IfCase item) {
        this.left = item;
    }

    public void addRight(IfCase item) {
        this.right = item;
    }

    public void addToken(String item) {
        this.token = item;
    }

    public IfCase getRight() {
        return this.right;
    }
    public IfCase getLeft() {
        return this.left;
    }
    public Object getToken() {
        return this.token;
    }
}