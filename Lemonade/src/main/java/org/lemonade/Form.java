package org.lemonade;

//Maybe everything inherits from the org.lemonade.ASTNode class so we can easily walk through
//the constructed tree?
public class Form extends ASTNode{
    private String identifier;

    public Form(String identifier, int lineNo){
        super(lineNo, null);
        this.identifier = identifier;
    }

    @Override
    public String toString(){
        return this.identifier;
    }
}

//Huh? Waarom maak je nog een losse form body class
//class FormBody extends org.lemonade.Form {
//    private final org.lemonade.Form questions;
//
//    public FormBody(org.lemonade.Form questions) {
//        this.questions = questions;
//    }
//
//    @Override
//    public String value() {
//        return this.questions.value();
//    }
//}

//class Questions implements org.lemonade.Form {
//    private final List<org.lemonade.Form> questionList;
//
//    public Questions(List<org.lemonade.Form> questionList) {
//        this.questionList = questionList;
//    }
//
//    @Override
//    public String value() {
//        StringBuilder builder = new StringBuilder();
//        for (org.lemonade.Form question : this.questionList) {
//            builder.append(question.value() + "\n");
//        }
//
//        return builder.toString();
//    }
//}

//class org.lemonade.Question implements org.lemonade.Form {
//    private final String identifier;
//    private final String label;
//    private final String type_specifier;
//
//    public org.lemonade.Question(String identifier, String label, String type_specifier) {
//        this.identifier = identifier;
//        this.label = label;
//        this.type_specifier = type_specifier;
//    }
//
//    @Override public String value() {
//        return this.identifier + " : " + this.label + " " + this.type_specifier;
//    }
//}

