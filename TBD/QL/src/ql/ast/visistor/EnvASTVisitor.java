package ql.ast.visistor;

import ql.ast.*;
import ql.ast.visistor.environment.Environment;
import ql.ast.visistor.environment.EnvironmentVariable;
import ql.logger.ErrorHandler;
import ql.logger.Error;


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

    public Void visit(Question node) {
        if (environment.contains(node.getId())) {
            errorHandler.addError(new Error("Identifier " + node.getId() + " already exist!", node.getRowNumber()));
        }

        EnvironmentVariable envVar = new EnvironmentVariable(node.getType());
        environment.addVariable(node.getId(), envVar);
        return null;
    }



    public Void visit(QuestionExpr node) {
        if (environment.contains(node.getId())) {
            errorHandler.addError(new Error("Identifier " + node.getId() + " already exist!", node.getRowNumber()));
        }

        EnvironmentVariable envVar = new EnvironmentVariable(node.getType(), node.getExpr());
        environment.addVariable(node.getId(), envVar);

        node.getExpr().accept(this);
        return null;
    }


}
