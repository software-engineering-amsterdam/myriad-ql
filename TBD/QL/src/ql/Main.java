package ql;

import ql.ast.QLLiteral;
import ql.ast.Type;
import ql.ast.expressions.numop.Sub;
import ql.ast.expressions.unop.Neg;
import ql.ast.literals.QLInt;
import ql.ast.visistor.ASTVisitor;
import ql.ast.visistor.EvalASTVisitor;
import ql.ast.visistor.PrintASTVisitor;
import ql.ast.visistor.TypeASTVisitor;
import ql.parser.Parser;
import ql.parser.QLLexer;

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

            Parser parser = new Parser(lexer);
            parser.parse();

            System.out.println("Parser done");

            ASTVisitor<Void> printVisitor = new PrintASTVisitor();
            printVisitor.visit(parser.getResult());

            ASTVisitor<Type> typeVisitor = new TypeASTVisitor();
            typeVisitor.visit(parser.getResult());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        Neg exprtest = new Neg(new Sub(new QLInt(12), new QLInt(4)));

        ASTVisitor<QLLiteral> evalASTVisitor = new EvalASTVisitor();
        System.out.println(evalASTVisitor.visit(exprtest));

    }
}
