package com.Qlmain;

import com.Qlmain.QL.*;
import com.Qlmain.evaluation.Evaluation;
import com.Qlmain.antlr.QLLexer;
import com.Qlmain.antlr.QLParser;
import com.Qlmain.parsing.QLVisitorBuildAST;
import com.Qlmain.type_check.TypeChecking;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by matt on 20/02/2017.
 */
public class MainFunc {

    private String resultString = "";

    public static void main(String[] args) {

        MainFunc runner = new MainFunc();
        String inputContent = null;

        try {
            inputContent = runner.QlRead();
        } catch (InvocationTargetException | InterruptedException e) {
            e.printStackTrace();
        }

        if(inputContent != null && inputContent != "")
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

            QLVisitorBuildAST visitor = new QLVisitorBuildAST();
            formAST = (Form) visitor.visit(formDeclarationContext);

        }catch(ParseCancellationException ex){
            System.out.println(ex.getMessage());
            System.exit(1);
        }

        TypeChecking typeCheck = new TypeChecking();
        boolean typeCheckTree = typeCheck.TypeCheckingMethod(formAST);
        System.out.println("Type checking of the tree " + typeCheckTree);

        Map<String, Object> firstEvaluation = new HashMap<>();
        if (typeCheckTree) {
            Evaluation evaluation = new Evaluation();
            firstEvaluation = evaluation.evaluateAST(formAST.getStatementList());
            new Frame_Window().Custom_Frame(formAST, firstEvaluation, typeCheck, evaluation);
        }

        return formAST;

    }

    public String QlRead() throws InvocationTargetException, InterruptedException {

        EventQueue.invokeAndWait(() -> {

            String fileInString = "";

            JFileChooser chooser = new JFileChooser();

            chooser.setDialogTitle("Select QL Input File");
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

            FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
            chooser.setFileFilter(filter);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

                try (BufferedReader br = new BufferedReader(new FileReader(chooser.getSelectedFile()))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        fileInString = fileInString.concat(line+'\n');
                        // process the line.
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                System.out.println("No Selection ");
            }

            resultString = fileInString;
        });

        return resultString;
    }

}
