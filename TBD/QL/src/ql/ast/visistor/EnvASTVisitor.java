package ql.ast.visistor;

import ql.ast.*;
import ql.ast.environment.Env;
import ql.ast.environment.Scope;
import ql.logger.ErrorHandler;
import ql.logger.Error;

import java.util.List;


/**
 * Created by Erik on 27-2-2017.
 */
public class EnvASTVisitor extends ASTVisitor<Void> {
    private Env env;
    private Scope currentScope = null;
    private ErrorHandler errorHandler;


    public Env startVisitor(ErrorHandler errorHandler, ASTNode node){
        this.env = new Env();
        this.errorHandler = errorHandler;

        node.accept(this);
        errorHandler.showErrors();
        return env;
    }


    public Void visit(Statements node) {
        currentScope = new Scope(currentScope);
        env.addScope(node, currentScope);
        List<Statement> statements = node.getItems();
        for (Statement statement: statements) {
            statement.accept(this);
        }
        currentScope = currentScope.getParent();
        return null;
    }


    public Void visit(Question node) {
        if (env.contains(node.getId())) {
            errorHandler.addError(new Error("Identifier " + node.getId() + " already exist!", node.getRowNumber()));
        }

        env.addQuestion(currentScope, node);
        return null;
    }



    public Void visit(QuestionExpr node) {
        if (env.contains(node.getId())) {
            errorHandler.addError(new Error("Identifier " + node.getId() + " already exist!", node.getRowNumber()));
        }

        env.addQuestion(currentScope, node);

        node.getExpr().accept(this);
        return null;
    }


}
