package UvA.Gamma;
import UvA.Gamma.AST.Values.Number;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;

public class MathExpr {

    private String expr;

    public MathExpr(String expr) {
        this.expr = expr;
    }

    public Number evaluate () throws Exception{
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        System.out.println(engine.eval(expr));
        return new Number(engine.eval(expr).toString());
    }
}
