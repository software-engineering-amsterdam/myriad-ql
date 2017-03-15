package com.matthewchapman.ql.core;

import com.matthewchapman.ql.ast.Form;

import java.io.File;
import java.lang.reflect.InvocationTargetException;


/**
 * Created by matt on 20/02/2017.
 * <p>
 * Main "Launcher" class for the application. Provides entry point & debug logic.
 */
public class Launcher {

    public static void main(String[] args) {
        CoreParser coreParser = new CoreParser();
        FileReader QLFileReader = new FileReader();
        String inputContent = null;

        //TODO This feels messy. Candidate for refactoring
        if (args.length > 0 && args[0].equals("-debug")) {
            inputContent = QLFileReader.readFile(new File("/Users/matt/Repos/myriad-ql/supercoolQL/supercoolQL/res/circular.txt"));
        } else {
            try {
                inputContent = new FileReader().QlRead();
            } catch (InvocationTargetException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (inputContent != null) {
            if (!inputContent.isEmpty()) {
                Form ast = coreParser.buildQLAST(inputContent);
                coreParser.visitAST(ast);
            } else {
                System.out.println("Error: Input file is empty");
            }
        } else {
            System.out.println("Error: No input file chosen");
        }
    }
}
