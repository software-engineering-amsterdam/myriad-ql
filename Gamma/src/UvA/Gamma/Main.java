package UvA.Gamma;

import UvA.Gamma.Antlr.QL.QLBaseVisitor;
import UvA.Gamma.Antlr.QL.QLLexer;
import UvA.Gamma.Antlr.QL.QLParser;
import UvA.Gamma.Antlr.calculator.CalculatorBaseVisitor;
import UvA.Gamma.Antlr.calculator.CalculatorParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static String test = "";

    public static void main(String[] args) throws IOException {
        String test = "form test { first: \"how old are you?\" (1+1) }";
        InputStream is = new ByteArrayInputStream(test.getBytes());
        ANTLRInputStream input = new ANTLRInputStream(is);
        QLLexer lexer = new QLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokens);
        ParseTree parseTree = parser.form();
        QLVisitor visitor = new QLVisitor();
        visitor.visit(parseTree);
    }

    private static class QLVisitor extends QLBaseVisitor<Object>{
        Map<String, String> ids;

        QLVisitor(){
            ids = new HashMap<>();
        }

        @Override
        public Object visitForm(QLParser.FormContext ctx) {
            visit(ctx.formItem(0));
            return super.visitForm(ctx);
        }

        @Override
        public Object visitInput(QLParser.InputContext ctx) {
            String id = ctx.ID().getText();
            String question = ctx.QUESTION().getText();
            String type = (String) visit(ctx.type());
            System.out.println(id + ": " + question + " " + type + "\n");
            return id + ": " + question + " " + type + "\n";
        }

        @Override
        public Object visitType(QLParser.TypeContext ctx) {
            if (ctx.intExpr() != null) {
                return String.valueOf(visit(ctx.intExpr()));
            }
            return ctx.getText();
        }

        @Override
        public Object visitIntId(QLParser.IntIdContext ctx) {
            return Double.valueOf(ids.get(ctx.ID().getText()));
        }

        //Expressions

        @Override
        public Object visitAdd(QLParser.AddContext ctx) {
            double left = (double)visit(ctx.intExpr(0));
            double right  = (double)visit(ctx.intExpr(1));
            return ctx.op.getType() == QLParser.ADD ? left + right : left - right;
        }

        @Override
        public Object visitInt(QLParser.IntContext ctx) {
            return Double.valueOf(ctx.NUMBER().getText());
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
