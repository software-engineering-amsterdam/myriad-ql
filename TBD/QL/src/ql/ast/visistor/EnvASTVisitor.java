package ql.ast.visistor;

import ql.ast.*;
import ql.ast.environment.Env;
import ql.ast.environment.Scope;
import ql.ast.visistor.interfaces.BaseVisitor;
import ql.logger.Error;
import ql.logger.ErrorHandler;

import java.util.List;


/**
 * Created by Erik on 27-2-2017.
 */
public class EnvASTVisitor implements BaseVisitor<Void> {
    private Env env;
    private Scope currentScope = null;
    private ErrorHandler errorHandler;


    public Env startVisitor(ErrorHandler errorHandler, Form node){
        this.env = new Env();
        this.errorHandler = errorHandler;

        node.accept(this);
        errorHandler.showErrors();
        return env;
    }


    @Override
    public Void visit(Form node) {
        node.getStatements().accept(this);
        return null;
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

    @Override
    public Void visit(If node) {
        node.getIfBlock().accept(this);
        return null;
    }

    public Void visit(IfElse node) {
        node.getIfBlock().accept(this);
        node.getElseBlock().accept(this);
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
        return null;
    }


}
