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
        // Some sample exec's
//        java -jar C:\School\UvA\Projecten\myriad-ql\prototypes\QLJava\lib\antlr-3.4-complete-no-antlrv2.jar QL.g
        // java -jar raw\antlr-4.6-complete.jar raw\Grammar.g
        System.out.println("** Starting TaxFree **");
        System.out.println(executeCommand("java -jar libs\\antlr-4.6-complete.jar src\\org\\uva\\ak_bvdb\\taxfree\\raw\\Grammar.g"));
        System.out.println("** Finished TaxFree **");
    }

    private String executeCommand(String command) {
        StringBuffer output = new StringBuffer();
        Process p;
        try {
            p = Runtime.getRuntime().exec(command, new String[0], new File("").getAbsoluteFile());
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}
