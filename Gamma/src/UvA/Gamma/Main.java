package UvA.Gamma;

import UvA.Gamma.Antlr.QL.QLLexer;
import UvA.Gamma.Antlr.QL.QLParser;
import UvA.Gamma.Antlr.calculator.CalculatorBaseVisitor;
import UvA.Gamma.Antlr.calculator.CalculatorParser;
import UvA.Gamma.Models.Form;
import UvA.Gamma.Models.Input;
import UvA.Gamma.Models.QLType;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) throws IOException {

        String test = "form test { first: \"how old are you?\" integer \n" +
                "second: \"That is true!\" boolean }";
        InputStream is = new ByteArrayInputStream(test.getBytes());
        ANTLRInputStream input = new ANTLRInputStream(is);
        QLLexer lexer = new QLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokens);
        ParseTree parseTree = parser.form();
        QLVisitor visitor = new QLVisitor();
        visitor.visit(parseTree);

        Form form = visitor.getForm();
        for(Input i : form.getInputs()){
            if (i.getType() == QLType.BOOLEAN){
                i.setValue(false);
            }
            System.out.println(i);
        }

    }

    private static class CalcVisitor extends CalculatorBaseVisitor<Double>{
        @Override
        public Double visitInt(CalculatorParser.IntContext ctx) {
            return Double.valueOf(ctx.NUMBER().getText());
        }

        @Override
        public Double visitAdd(CalculatorParser.AddContext ctx) {
            double left = visit(ctx.expr(0));
            double right  = visit(ctx.expr(1));
            return ctx.op.getType() == CalculatorParser.ADD ? left + right : left - right;
        }

        @Override
        public Double visitDiv(CalculatorParser.DivContext ctx) {
            return super.visitDiv(ctx);
        }
    }
}
