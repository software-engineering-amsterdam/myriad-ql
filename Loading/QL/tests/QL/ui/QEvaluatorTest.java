package QL.ui;

import QL.Faults;
import QL.QLLexer;
import QL.QLParser;
import QL.ast.Form;
import QL.evaluation.Environment;
import QL.semantic.Analyzer;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;


class QEvaluatorTest {
    @Test
    public void evaluator() {

        // TODO how to create QEvaluator

//        try {
//            InputStream src = new FileInputStream("QL/tests/assets/evaluator.ql");
//            ANTLRInputStream input = new ANTLRInputStream(src);
//
//            QLLexer lexer = new QLLexer(input);
//
//            CommonTokenStream tokens = new CommonTokenStream(lexer);
//
//            QLParser parser = new QLParser(tokens);
//            Form form = parser.form().result;
//
//            Analyzer analyzer = new Analyzer();
//
//            Faults faults = analyzer.analyze(form);
//
//            Environment env = new Environment(analyzer.getVariableTypes());
//
//            QEvaluator qVisitor = new QEvaluator(env);
//            qVisitor.visit(form);
//
//
//            assertEquals(0, faults.getErrors().size());
//            assertEquals(0, faults.getWarnings().size());
//
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
    }
}