/*
 *
 *  Copyright 2012-2016 Eurocommercial Properties NV
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

import java.util.List;

public interface Form {
    public String value();
}

class FormBody implements Form {
    private final Form questions;

    public FormBody(Form questions) {
        this.questions = questions;
    }

    @Override
    public String value() {
        return this.questions.value();
    }
}

class Questions implements Form {
    private final List<Form> questionList;

    public Questions(List<Form> questionList) {
        this.questionList = questionList;
    }

    @Override
    public String value() {
        StringBuilder builder = new StringBuilder();
        for (Form question : this.questionList) {
            builder.append(question.value() + "\n");
        }

        return builder.toString();
    }
}

class Question implements Form {
    private final String identifier;
    private final String label;
    private final String type_specifier;

    public Question(String identifier, String label, String type_specifier) {
        this.identifier = identifier;
        this.label = label;
        this.type_specifier = type_specifier;
    }

    @Override public String value() {
        return this.identifier + " : " + this.label + " " + this.type_specifier;
    }
}
