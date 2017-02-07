package ql;

import ql.parser.QLLexer;
import ql.parser.QLParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

/**
 * Created by Erik on 7-2-2017.
 */
public class Main {
    public static void main(String[] args){
        Reader reader = null;
        try {
            reader = new FileReader("C:\\Users\\Erik\\Documents\\uva\\SC\\QL\\myriad-ql\\TBD\\test.txt");
            QLLexer lexer = new QLLexer(reader);
            lexer.nextToken();

            System.out.println("Lexer done");

            QLParser parser = new QLParser(lexer);
            parser.parse();

            System.out.println("Parser done");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
