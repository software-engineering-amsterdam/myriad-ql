package UvA.Gamma;

import UvA.Gamma.Antlr.QLLexer;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;

import java.io.IOException;

public class Main {

    static String test = "";

    public static void main(String[] args) {
        QLLexer lexer = new QLLexer(new ANTLRStringStream(Main.test));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

    }
}
