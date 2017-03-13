package qls;

import qls.parser.Parser;
import qls.parser.QLSLexer;

import java.io.FileReader;
import java.io.Reader;

/**
 * Created by Erik on 13-3-2017.
 */
public class Main {

    public static void main(String[] args){
        Reader reader = null;
        try{
            reader = new FileReader("D:\\UvA\\SC\\myriad-ql\\TBD\\QL\\qls.txt");
            System.out.println("Lexer");
            QLSLexer lexer = new QLSLexer(reader);
            lexer.nextToken();
            System.out.println("Lexer done");

            Parser parser = new Parser(lexer);
            parser.parse();

            System.out.println("Parser done");
        }catch (Exception e) {
            System.out.println("file error");
        }
    }


}
