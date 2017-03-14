package com.Qlmain;

import com.Qlmain.QL.*;
import com.Qlmain.antlr.QLLexer;
import com.Qlmain.antlr.QLParser;
import com.Qlmain.parsing.QLVisitorBuildAST;
import com.Qlmain.type_check.Expression_Type_Check;
import com.Qlmain.type_check.Type_Checking;
import com.Qlmain.type_check.UndefinedException;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.lang.reflect.InvocationTargetException;


/**
 * Created by matt on 20/02/2017.
 */
public class MainFunc {

    public static void main(String[] args) {

        MainFunc runner = new MainFunc();
        String inputContent = null;

        try {
            inputContent = new OpenAndReadTheQl().QlRead();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(inputContent != null)
            runner.buildQLAST(inputContent);

    }

    public Form buildQLAST(String input) {
        // Get our lexer
        QLLexer lexer = new QLLexer(new ANTLRInputStream(input));
        lexer.removeErrorListeners();
        lexer.addErrorListener(ThrowingErrorListener.INSTANCE);

        // Get a list of matched tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Pass the tokens to the parser
        QLParser parser = new QLParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(ThrowingErrorListener.INSTANCE);

        Form traverseResult = null;
        try {
            // Specify our entry point
            QLParser.FormDeclarationContext formDeclarationContext = parser.formDeclaration();
            QLVisitorBuildAST visitor = new QLVisitorBuildAST();
            traverseResult = (Form) visitor.visit(formDeclarationContext);

        }catch(ParseCancellationException ex){
            System.out.println(ex.getMessage());
            System.exit(1);
        }

        boolean typecheckTree = new Type_Checking().Type_CheckingMethod(traverseResult);
        System.out.println("Typechecking of the tree " + typecheckTree);

        if (typecheckTree) {
            new Frame_Window().Custom_Frame(traverseResult);
        }
        /*for(Statement st :  traverseResult.getStatementList()) {
            if (st.getClass().equals(Question.class)) {
                Question qu = (Question) st;
                System.out.println("Questions outside if statement: " + qu.text + " " + qu.name);
                printTypeCheck(qu.type);
            } else if (st.getClass().equals(IfStatement.class)) {
                IfStatement ifst = (IfStatement) st;
                printTypeCheck(ifst.getIfCase());
                for (Statement quIfSt : ifst.getStatementsList()) {
                    if (quIfSt.getClass().equals(Question.class)) {
                        Question tempQuIfSt = (Question) quIfSt;
                        System.out.println("Questions in if statement: " + tempQuIfSt.text + " " + tempQuIfSt.name);
                        printTypeCheck(tempQuIfSt.type);
                    }
                }
            }
        }*/

        return traverseResult;

    }

    private void printTypeCheck(Expr typeCheck) {
        try {
            System.out.println( Expression_Type_Check.typeCheckExp(typeCheck));
        } catch (UndefinedException e) {
            e.printStackTrace();
        }
        /*if (typeCheck.getLeft() == null &&  typeCheck.getRight() == null){
            System.out.println(typeCheck.getToken());
        }else {
            printTypeCheck(typeCheck.getLeft());
            System.out.println(typeCheck.getToken());
            printTypeCheck(typeCheck.getRight());
        }*/
    }

   /* private void printIfCheck(Expr typeCheck) {
        if (typeCheck.getLeft() == null &&  typeCheck.getRight() == null){
            System.out.println(typeCheck.getToken());
        }else {
            printIfCheck(typeCheck.getLeft());
            System.out.println(typeCheck.getToken());
            printIfCheck(typeCheck.getRight());
        }
    }*/

}
