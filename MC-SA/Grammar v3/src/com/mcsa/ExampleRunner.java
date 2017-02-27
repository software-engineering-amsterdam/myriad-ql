package com.mcsa;

import com.mcsa.QL.*;
import com.mcsa.antlr.QLLexer;
import com.mcsa.antlr.QLParser;
import com.mcsa.parsing.mcsaQLVisitor;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.lang.reflect.InvocationTargetException;


/**
 * Created by matt on 20/02/2017.
 */
public class ExampleRunner {

    public static void main(String[] args) {

        ExampleRunner runner = new ExampleRunner();
        String inputContent = null;

        if(args.length > 0)
        {
            if(args[0].equals("-debug"))
            {
                inputContent = "form taxOfficeExample { \n" +
                        "  \"Did you sell a house in 2010?\"\n" +
                        "    hasSoldHouse: boolean\n" +
                        "  \"Did you buy a house in 2010?\"\n" +
                        "    hasBoughtHouse: boolean\n" +
                        "  \"Did you enter a loan?\"\n" +
                        "    hasMaintLoan: boolean\n" +
                        "\n" +
                        "  if (11 OR j AND 12 > W) {\n" +
                        "    \"What was the selling price?\"\n" +
                        "      sellingPrice: money\n" +
                        "    \"Private debts for the sold house:\"\n" +
                        "      privateDebt: money\n" +
                        "    \"Value residue:\"\n" +
                        "      valueResidue: money = \n" +
                        "        (sellingPrice - privateDebt)\n" +
                        "  }\n" +
                        "\n" +
                        "}";
            }
        }
        else {

            try {
                inputContent = new OpenAndReadTheQl().QlRead();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if(inputContent != null)
            runner.buildQLAST(inputContent);

    }

    public Form buildQLAST(String input) {
        // Get our lexer
        QLLexer lexer = new QLLexer(new ANTLRInputStream(input));

        // Get a list of matched tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Pass the tokens to the parser
        QLParser parser = new QLParser(tokens);

        // Specify our entry point
        QLParser.FormDeclarationContext formDeclarationContext = parser.formDeclaration();

        mcsaQLVisitor visitor = new mcsaQLVisitor();
        Form traverseResult = (Form) visitor.visit(formDeclarationContext);
        for(IfStatement ifst : traverseResult.getIfStatementList()) {
            //System.out.println("case " + ifst.getIfCase().toString());
            printIfCheck(ifst.getIfCase());
            for (Question qu : ifst.getQuestionList()) {
                System.out.println("Questions in if statement: " + qu.text + " " + qu.name );
                printTypeCheck(qu.type);
            }
        }
        for(Question ifst : traverseResult.getQuestionList()) {

            System.out.println("Questions outside if statement: " + ifst.text + " " + ifst.name);
            printTypeCheck(ifst.type);

        }
        return traverseResult;

    }

    private void printTypeCheck(Type typeCheck) {
        if (typeCheck.getLeft() == null &&  typeCheck.getRight() == null){
            System.out.println(typeCheck.getToken());
        }else {
            printTypeCheck(typeCheck.getLeft());
            System.out.println(typeCheck.getToken());
            printTypeCheck(typeCheck.getRight());
        }
    }

    private void printIfCheck(IfCase typeCheck) {
        if (typeCheck.getLeft() == null &&  typeCheck.getRight() == null){
            System.out.println(typeCheck.getToken());
        }else {
            printIfCheck(typeCheck.getLeft());
            System.out.println(typeCheck.getToken());
            printIfCheck(typeCheck.getRight());
        }
    }

}
