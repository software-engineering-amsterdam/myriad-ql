package com.mcsa;

public class EvaluateExample {

    public static void main(String[] args) {

        String fileContent = new OpenAndReadTheQl().QlRead();
        MattExperiment.parsingTheString(fileContent);

    }

}
