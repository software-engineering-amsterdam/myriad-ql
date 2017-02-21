package org.uva.taxfree.util;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class Evaluator {
    public static String calculate(String calculation) throws Exception {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        return engine.eval(calculation).toString();
    }
}