package com.mcsa.QL;

/**
 * Created by matt on 21/02/2017.
 */
public class Statement implements Node  {

    private Question quContent;
    private IfStatement stContent;

    public Statement() {}

    public Statement(Question qu) {
        this.quContent = qu;
    }

    public Statement(IfStatement st) {
        this.stContent = st;
    }

}
