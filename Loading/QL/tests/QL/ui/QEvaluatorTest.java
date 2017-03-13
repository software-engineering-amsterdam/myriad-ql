package QL.ui;

import QL.Faults;
import QL.QLLexer;
import QL.QLParser;
import QL.ast.Form;
import QL.ast.Statement;
import QL.evaluation.Environment;
import QL.evaluation.Evaluator;
import QL.semantic.Analyzer;
import QL.value.BoolValue;
import QL.value.Value;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


class QEvaluatorTest  {

    @Test
    public void evaluator() throws IOException {
        InputStream src = new FileInputStream("QL/tests/assets/validquestionnaire.ql");
        ANTLRInputStream input = new ANTLRInputStream(src);

        QLLexer lexer = new QLLexer(input);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        QLParser parser = new QLParser(tokens);
        Form form = parser.form().result;

        Analyzer analyzer = new Analyzer();

        Faults faults = analyzer.analyze(form);

        Environment env = new Environment(analyzer.getVariableTypes());

        Evaluator qVisitor = new Evaluator(env);
        qVisitor.visit(form);

        System.out.println(env);

        assertEquals(0, faults.getErrors().size());
        assertEquals(0, faults.getWarnings().size());
    }
}