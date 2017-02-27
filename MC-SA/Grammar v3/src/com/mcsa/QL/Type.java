package com.mcsa.QL;

/**
 * Created by matt on 21/02/2017.
 */
public class Type {

    Type left;
    Type right;
    Object token;

    public Type() {
        this.left = null;
        this.right = null;
        this.token = null;
    }

    public void addLeft (Type item) {
        this.left = item;
    }

    public void addRight(Type item) {
        this.right = item;
    }

    public void addToken(Object item) {
        this.token = item;
    }

    public Type getRight() {
        return this.right;
    }
    public Type getLeft() {
        return this.left;
    }
    public Object getToken() {
        return this.token;
    }

}
