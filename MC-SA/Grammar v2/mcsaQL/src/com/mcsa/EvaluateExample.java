package com.mcsa;

import java.lang.reflect.InvocationTargetException;

public class EvaluateExample {

    public static void main(String[] args) {

        String fileContent = null;
        try {
            fileContent = new OpenAndReadTheQl().QlRead();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MattExperiment.parsingTheString(fileContent);

        new TreeTest();

    }


}
