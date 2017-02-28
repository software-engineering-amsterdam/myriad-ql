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
import ql.ast.values.IntValue;
import ql.ast.values.Value;
import ql.ast.visistor.*;
import ql.ast.visistor.environment.Environment;
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
            reader = new FileReader("D:\\UvA\\SC\\myriad-ql\\TBD\\QL\\test.txt");
            //reader = new FileReader("/home/rico/Desktop/test.txt");
            QLLexer lexer = new QLLexer(reader);
            lexer.nextToken();

            System.out.println("Lexer done");

            Parser parser = new Parser(lexer);
            parser.parse();

            System.out.println("Parser done");

            ASTVisitor<Void> printVisitor = new PrintASTVisitor();
            printVisitor.visit(parser.getResult());

            EnvASTVisitor envASTVisitor = new EnvASTVisitor();
            Environment env = envASTVisitor.startVisitor(parser.getResult());

            TypeASTVisitor typeVisitor = new TypeASTVisitor(env);
            typeVisitor.startVisitor(parser.getResult());


            env.setVariableValue("hasBoughtHouse", new IntValue(6));
            System.out.println("Value of expr: " + env.getVariableValue("test"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
