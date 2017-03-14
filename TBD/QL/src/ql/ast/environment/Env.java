package ql.ast.environment;

import ql.ast.ASTNode;
import ql.ast.Expr;
import ql.ast.Question;
import ql.ast.QuestionExpr;
import ql.ast.types.Type;
import ql.ast.values.Value;
import ql.ast.visistor.EvalASTVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Erik on 14-3-2017.
 */
public class Env {
    private final HashMap<ASTNode, Scope> scopes = new HashMap<>();
    private final HashMap<String, EnvQuestion> questions = new HashMap<>();
    private final List<EnvironmentEventListener> eventListeners = new ArrayList<>();
    private final EvalASTVisitor evalASTVisitor = new EvalASTVisitor(this);

    public void addScope(ASTNode node, Scope scope) {
        scopes.put(node, scope);
    }

    public Scope getScope(ASTNode node) {
        return scopes.get(node);
    }

    public void addQuestion(Scope scope, Question node) {
        questions.put(node.getId(), new EnvQuestion(scope, node.getId(), node.getQuestion(), node.getType()));
    }

    public void addQuestion(Scope scope,QuestionExpr node) {
        questions.put(node.getId(), new EnvQuestion(scope, node.getId(), node.getQuestion(), node.getType(), node.getExpr()));
    }

    public Type getQuestionType(String key) {
        return questions.get(key).getType();
    }

    public Value getQuestionValue(String key) {
        if (questions.containsKey(key)) {
            EnvQuestion question = questions.get(key);
            if(question.hasExpr()) {
                return evalASTVisitor.startVisitor(question.getExpr());
            }
            return question.getValue();
        }
        return null;
    }

    public Scope getQuestionScope(String key) {
        return questions.get(key).getScope();
    }

    public boolean hasQuestionExpr(String key) {
        return questions.get(key).hasExpr();
    }

    public void setQuestionValue(String key, Value value) {
        if (questions.containsKey(key)) {
            questions.get(key).setValue(value);
            updateEvent();
        }
    }

    public boolean contains(String key) {
        return questions.containsKey(key);
    }


    public Value evalExpr(Expr expr) {
        return evalASTVisitor.startVisitor(expr);
    }

    public void addEventListener(EnvironmentEventListener listener) {
        eventListeners.add(listener);
    }

    private void updateEvent(){
        for (EnvironmentEventListener listener : eventListeners) {
            listener.onChange();
        }
    }
}
