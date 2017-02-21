package com.matthewchapman.QL;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matt on 20/02/2017.
 */
public class Form implements Node{

    public ArrayList<Question> questionList;
    public String name;

    public Form(String name, ArrayList<Question> questionList)
    {
        this.name = name;
        this.questionList = new ArrayList<>();
        this.questionList = questionList;
    }

}
