package com.mcsa;

import com.mcsa.gen.Exp.*;
import org.antlr.v4.runtime.*;

import java.io.IOException;

public class EvaluateExample {

    public static int i;

    public static void main(String[] args) throws IOException {

        System.out.println(evalExampleExpression("12*(6-5)"));

    }

    public static double evalExampleExpression(String in)
    {
        if(!in.isEmpty()) {
            String input = in;
            ANTLRInputStream inputStream = new ANTLRInputStream(input);
            ExpLexer lexer = new ExpLexer(inputStream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ExpParser parser = new ExpParser(tokens);
            return parser.eval().value;
        }
        else
        {
            return 0;
        }

    }
}
