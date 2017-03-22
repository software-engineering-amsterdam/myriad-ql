package com.matthewchapman.ql.environment.observers;

import com.matthewchapman.ql.environment.FormEnvironment;
import com.matthewchapman.ql.environment.values.Value;

/**
 * Created by matt on 20/03/2017.
 */
public class QuestionInputObserver {

    public FormEnvironment environment;

    public QuestionInputObserver(FormEnvironment env) {
        this.environment = env;
    }

    public void updateValue(String id, Value value) {
        System.out.println(id + " updated to " + value);
        environment.updateValueByName(id, value);
    }

}
