package com.mcsa;

import com.mcsa.antlr.QLLexer;
import com.mcsa.antlr.QLParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by matt on 14/02/2017.
 */
public class MattExperiment {


        public static void main(String[] args) throws IOException {

            File file = new File("/Users/matt/Repos/myriad-ql/MC-SA/Grammar v2/mcsaQL/src/input/input.QL");
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();

            String inputString = new String(data, "UTF-8");

            evalExampleExpression(inputString);

        }

        public static String evalExampleExpression(String in)
        {
            if(!in.isEmpty()) {
                String input = in;
                ANTLRInputStream inputStream = new ANTLRInputStream(input);
                QLLexer lexer = new QLLexer(inputStream);
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                QLParser parser = new QLParser(tokens);
                return parser.start().getText();
            }
            else
            {
                return "aaaaaa";
            }

        }
    }

}
