import org.ql.QLInterpreter;
import org.ql.io.QLFile;
import org.qls.QLSInterpreter;
import org.qls.io.QLSFile;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
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

        System.exit(0);
    }
}
