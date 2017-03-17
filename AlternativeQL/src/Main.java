import org.ql.QLInterpreter;
import org.ql.io.QLFile;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            new QLInterpreter().interpret(new QLFile(args[0]));
        } catch (IOException e) {
            System.out.println("Cannot load file: "  + e.getMessage());
            System.exit(1);
        }

        System.exit(0);
    }
}
