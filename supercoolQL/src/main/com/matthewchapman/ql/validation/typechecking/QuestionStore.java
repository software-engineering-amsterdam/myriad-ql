package com.matthewchapman.ql.validation.typechecking;

import com.matthewchapman.ql.ast.statement.Question;

import java.util.ArrayList;

/**
 * Created by matt on 28/02/2017.
 */
public class QuestionStore extends ArrayList<Question> {

    public void testForDuplicateIDs() {

        ArrayList<String> questionIDs = new ArrayList<>();

        for (Question question : this) {
            if (questionIDs.contains(question.getName())) {
                System.out.println("Error: duplicate question");
            } else {
                questionIDs.add(question.getName());
            }
        }
    }

}
