package com.mcsa;

import com.mcsa.antlr.QLLexer;
import com.mcsa.antlr.QLListener;
import com.mcsa.antlr.QLParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

/**
 * Created by matt on 14/02/2017.
 */
class MattExperiment {


     static void parsingTheString(String stringToParse) {

       /* //just readin' a file
        File file = new File("src/input/input.QL");
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();

        //stick it in a string
        String inputString = new String(data, "UTF-8");*/

        //parse that shit
        evalExampleExpression(stringToParse);


    }

     static void evalExampleExpression(String input) {

       // String input = in;
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

