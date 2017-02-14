package com.mcsa;

import com.mcsa.antlr.QLLexer;
import com.mcsa.antlr.QLListener;
import com.mcsa.antlr.QLParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by matt on 14/02/2017.
 */
public class MattExperiment {


    public static void main(String[] args) throws IOException {

        //just readin' a file
        File file = new File("/Users/matt/Repos/myriad-ql/MC-SA/Grammar v2/mcsaQL/src/input/input.QL");
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();

        //stick it in a string
        String inputString = new String(data, "UTF-8");

        //parse that shit
        evalExampleExpression(inputString);


    }

    public static void evalExampleExpression(String in) {

        String input = in;
        // Get our lexer
        QLLexer lexer = new QLLexer(new ANTLRInputStream(input));

        // Get a list of matched tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Pass the tokens to the parser
        QLParser parser = new QLParser(tokens);

        // Specify our entry point
        QLParser.StartContext qlContext = parser.start();

        // Walk it and attach our listener
        ParseTreeWalker walker = new ParseTreeWalker();
        QLListener listener = new McsaListener();
        walker.walk(listener, qlContext);


    }
}

