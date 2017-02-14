package ql;

import ql.ast.Type;
import ql.ast.visistor.ASTVisitor;
import ql.ast.visistor.PrintASTVisitor;
import ql.ast.visistor.TypeASTVisitor;
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
            reader = new FileReader("C:\\Users\\Erik\\Documents\\uva\\SC\\QL\\myriad-ql\\TBD\\QL\\test.txt");
            QLLexer lexer = new QLLexer(reader);
            lexer.nextToken();

            System.out.println("Lexer done");

            QLParser parser = new QLParser(lexer);
            parser.parse();

            System.out.println("Parser done");

            ASTVisitor<Void> printVisitor = new PrintASTVisitor();
            printVisitor.visit(parser.getResult());

            ASTVisitor<Type> typeVisitor = new TypeASTVisitor();
            typeVisitor.visit(parser.getResult());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
