package com.Qlmain;

import com.Qlmain.QL.*;
import com.Qlmain.evaluation.Evaluation;
import com.Qlmain.antlr.QLLexer;
import com.Qlmain.antlr.QLParser;
//import com.Qlmain.parsing.QLVisitorBuildAST;
import com.Qlmain.parsing.QLVisitorBuildAST;
import com.Qlmain.type_check.Type_Checking;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;


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

        Form formAST = null;
        try {
            // Specify our entry point
            QLParser.FormDeclarationContext formDeclarationContext = parser.formDeclaration();
            //QLVisitorBuildAST visitor = new QLVisitorBuildAST();
            QLVisitorBuildAST visitor = new QLVisitorBuildAST();
            formAST = (Form) visitor.visit(formDeclarationContext);

        }catch(ParseCancellationException ex){
            System.out.println(ex.getMessage());
            System.exit(1);
        }

        boolean typecheckTree = new Type_Checking().Type_CheckingMethod(formAST);
        System.out.println("Typechecking of the tree " + typecheckTree);

        Map<String, Object> firstEvaluation = new HashMap<>();
        if (typecheckTree) {
            new Evaluation().initialise();
            firstEvaluation = new Evaluation().evaluateAST(formAST.getStatementList());
        }

        if (typecheckTree) {
            new Frame_Window().Custom_Frame(formAST, firstEvaluation);
        }

        return formAST;

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
