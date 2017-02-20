package com.mcsa.Ql;

import java.util.ArrayList;

/**
 * Created by matt on 16/02/2017.
 */
public class Form {
    //TODO implement form base class

    public String name;
    public ArrayList<Question> questionList;

    public Form() {

    }

    public Form(String name)
    {
        this.name = name;
    }
}
