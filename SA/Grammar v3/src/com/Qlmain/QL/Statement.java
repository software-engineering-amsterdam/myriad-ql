package com.Qlmain.QL;

/**
 * Created by matt on 21/02/2017.
 */
public interface Statement<T extends Statement>  {

    //private Question quContent;
    //private IfStatement stContent;
    void visitst(T st);
    //public Statement() {}

    /*public Statement(Question qu) {
        this.quContent = qu;
    }*/

    //public Statement(IfStatement st) {this.stContent = st;}

}
