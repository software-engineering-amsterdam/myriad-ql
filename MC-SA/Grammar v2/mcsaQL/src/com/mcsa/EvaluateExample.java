package com.mcsa;

import com.mcsa.gen.ExpLexer;
import com.mcsa.gen.ExpParser;
import org.antlr.v4.runtime.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class EvaluateExample {

    public static void main(String[] args) throws IOException {
        String filecontent = new OpenAndReadTheQl().QlRead();
        //OpenAndReadTheQl.QlRead();
        System.out.println(evalExampleExpression(filecontent));

    }

    public static String evalExampleExpression(String in)
    {
        if(!in.isEmpty()) {
            String input = in;
            ANTLRInputStream inputStream = new ANTLRInputStream(input);
            ExpLexer lexer = new ExpLexer(inputStream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ExpParser parser = new ExpParser(tokens);
            return parser.start().getText();
        }
        else
        {
            return "aaaaaa";
        }

    }
}
