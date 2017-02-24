package com.matthewchapman.ql.core;

import com.matthewchapman.ql.ast.Form;
import com.matthewchapman.antlr.QLLexer;
import com.matthewchapman.antlr.QLParser;
import com.matthewchapman.ql.parsing.MCQLErrorListener;
import com.matthewchapman.ql.parsing.MCQLVisitor;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.File;
import java.lang.reflect.InvocationTargetException;


/**
 * Created by matt on 20/02/2017.
 */
public class Launcher {

    public static void main(String[] args) {

        Launcher runner = new Launcher();
        QlFileReader qlFileReader = new QlFileReader();
        String inputContent = null;

        if (args.length > 0 && args[0].equals("-debug")) {
            inputContent = qlFileReader.readFile(new File("res/test.txt"));
        } else {

            try {
                inputContent = new QlFileReader().QlRead();
            } catch (InvocationTargetException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (inputContent != null) {
            if (!inputContent.isEmpty()) {
                Form ast = runner.buildQLAST(inputContent);
                System.out.println();
            } else {
                System.out.println("Error: Input file is empty");
            }
        } else {
            System.out.println("Error: No input file chosen");
        }

    }

    private Form buildQLAST(String input) {

        //new error handler
        MCQLErrorListener errorListener = new MCQLErrorListener();

        // Get our lexer
        QLLexer lexer = new QLLexer(new ANTLRInputStream(input));

        lexer.removeErrorListeners();
        lexer.addErrorListener(errorListener);

        // Get a list of matched tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Pass the tokens to the parser
        QLParser parser = new QLParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);

        // Specify our entry point
        QLParser.FormDeclarationContext formDeclarationContext = parser.formDeclaration();

        MCQLVisitor visitor = new MCQLVisitor();

        return (Form) visitor.visit(formDeclarationContext);

    }

}
