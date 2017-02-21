package ql;

import ql.ast.Expr;
import ql.ast.expressions.binop.Add;
import ql.ast.expressions.binop.Div;
import ql.ast.literals.QLFloat;
import ql.ast.literals.QLIdent;
import ql.ast.expressions.binop.Sub;
import ql.ast.expressions.monop.Neg;
import ql.ast.literals.QLInt;
import ql.ast.types.FloatType;
import ql.ast.types.IntType;
import ql.ast.types.Type;
import ql.ast.values.Value;
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
    public static void main(String[] args) {
        Reader reader = null;
        try {
            reader = new FileReader("C:\\Users\\Erik\\Documents\\uva\\SC\\QL\\myriad-ql\\TBD\\QL\\test.txt");
            //reader = new FileReader("/home/rico/Desktop/test.txt");
            QLLexer lexer = new QLLexer(reader);
            lexer.nextToken();

            System.out.println("Lexer done");

            Parser parser = new Parser(lexer);
            parser.parse();

            System.out.println("Parser done");

            ASTVisitor<Void> printVisitor = new PrintASTVisitor();
            printVisitor.visit(parser.getResult());

            TypeASTVisitor typeVisitor = new TypeASTVisitor();
            typeVisitor.startVisitor(parser.getResult());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Div exprtest = new Div(new QLInt(2, 1), new QLFloat(0.1f, 2), 3);

        ASTVisitor<Value> evalASTVisitor = new EvalASTVisitor();
        System.out.println(evalASTVisitor.visit(exprtest));

    }
}
