package org.qls.gui;

import org.ql.QLInterpreter;
import org.ql.io.QLFile;
import org.qls.QLSInterpreter;
import org.qls.io.QLSFile;

import java.io.IOException;

public class Debug {
    public static void main(String[] args) {

        args = new String[]{"drafts/ExampleForm.aql", "drafts/ExampleStyle.aqls"};

        try {
            if (args.length == 1) {
                new QLInterpreter().interpret(new QLFile(args[0]));
            } else if (args.length == 2) {
                new QLSInterpreter().interpret(new QLFile(args[0]), new QLSFile(args[1]));
            } else {
                System.out.println("No match found for amount of source input arguments.");
            }
        } catch (IOException e) {
            System.out.println("Cannot load file: "  + e.getMessage());
            System.exit(1);
        }
    }
}
