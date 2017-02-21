package scriptengine;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

class Evaluator {
    public static String calculate(String calculation) throws Exception{
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        return engine.eval(calculation).toString();
    }

}
