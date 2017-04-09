package ql.gui.evaluator;

import org.json.JSONException;
import org.json.JSONObject;
import ql.gui.elements.*;

/**
 * Created by Erik on 9-4-2017.
 */
public class JSONEvaluator implements BaseEvaluator<Void> {

    private JSONObject jsonObject = new JSONObject();

    public JSONObject toJson(GUIForm node) {
        jsonObject = new JSONObject();
        visit(node);
        return jsonObject;
    }

    @Override
    public Void visit(GUIForm node) {
        node.getStatements().accept(this);
        return null;
    }

    @Override
    public Void visit(GUIStatements node) {
        for (GUIElement statement : node.getStatements()) {
            statement.accept(this);
        }
        return null;
    }

    public Void visit(GUIIf node) {
        if(node.getConditionValue()){
            node.getIfStatements().accept(this);
        }
        return null;
    }

    public Void visit(GUIIfElse node) {
        if(node.getConditionValue()) {
            node.getIfStatements().accept(this);
        }else {
            node.getElseStatements().accept(this);
        }
        return null;
    }

    public Void visit(GUIQuestion node) {
        try {
            jsonObject.put(node.getKey() + ": " + node.getQuestion() , node.getValue().getValue());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Void visit(GUIQuestionExpr node) {
        try {
            jsonObject.put(node.getKey() + ": " + node.getQuestion() , node.getValue().getValue());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
