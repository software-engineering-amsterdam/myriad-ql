package UvA.Gamma;

import UvA.Gamma.Antlr.QL.QLBaseVisitor;
import UvA.Gamma.Antlr.QL.QLParser;
import UvA.Gamma.Models.QLForm;
import UvA.Gamma.Models.Input;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tjarco on 08-02-17.
 */

public class QLVisitor extends QLBaseVisitor<Object> {
    Map<String, String> ids;
    private QLForm form;

    QLVisitor() {
        ids = new HashMap<>();
        form = new QLForm("");
    }

    public QLForm getForm(){
        return this.form;
    }

    @Override
    public Object visit(ParseTree tree) {
        return super.visit(tree);
    }

    @Override
    public QLForm visitForm(QLParser.FormContext ctx) {
        for (QLParser.FormItemContext formItemContext: ctx.formItem()){
            visit(formItemContext);
        }
        return form;
    }

    @Override
    public Input visitInput(QLParser.InputContext ctx) {
        String id = ctx.ID().getText();
        String question = ctx.QUESTION().getText();
        String type = (String) visit(ctx.type());
        Input input = new Input(id, question, type);
        form.addInput(input);
        return input;
    }

    @Override
    public Object visitType(QLParser.TypeContext ctx) {
        if (ctx.intExpr() != null) {
            return String.valueOf(visit(ctx.intExpr()));
        }
        return ctx.getText();
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
