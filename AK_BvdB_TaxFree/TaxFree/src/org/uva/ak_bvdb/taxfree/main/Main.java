package org.uva.ak_bvdb.taxfree.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Main {

    private static Main mInstance;

    public static void main(String[] args) {
        mInstance = new Main();
    }

    public Main() {
        System.out.println("** Starting TaxFree **");
        System.out.println(executeCommand("java -jar libs\\antlr-4.6-complete.jar Grammar.g -o generated"));
        System.out.println("** Finished TaxFree **");
    }

    private String executeCommand(String command) {
        StringBuffer output = new StringBuffer();
        Process p;
        try {
            p = Runtime.getRuntime().exec(command, new String[0], new File("").getAbsoluteFile());
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line;
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}
