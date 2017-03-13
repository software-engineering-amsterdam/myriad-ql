package ql.ast.visistor;

import ql.ast.*;
import ql.ast.environment.Environment;
import ql.logger.ErrorHandler;
import ql.logger.Error;

import java.util.List;


/**
 * Created by Erik on 27-2-2017.
 */
public class EnvASTVisitor extends ASTVisitor<Void> {
    private Environment environment;
    private final ErrorHandler errorHandler = new ErrorHandler();


    public Environment startVisitor(ASTNode node){
        environment = new Environment();
        node.accept(this);
        errorHandler.showErrors();
        return environment;
    }


    public Void visit(Statements node) {
        environment.addScope(node);
        environment.setScope(node);
        List<Statement> statements = node.getItems();
        for (Statement statement: statements) {
            statement.accept(this);
        }
        return null;
    }

    public Void visit(Question node) {
        if (environment.currentContains(node.getId())) {
            errorHandler.addError(new Error("Identifier " + node.getId() + " already exist!", node.getRowNumber()));
        }

        environment.addVariable(node.getId(), node.getType());
        return null;
    }



    public Void visit(QuestionExpr node) {
        if (environment.currentContains(node.getId())) {
            errorHandler.addError(new Error("Identifier " + node.getId() + " already exist!", node.getRowNumber()));
        }

        environment.addVariable(node.getId(), node.getType(), node.getExpr());

        node.getExpr().accept(this);
        return null;
    }


}
