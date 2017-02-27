package ql.ast.visistor;

import ql.ast.*;
import ql.ast.visistor.environment.Environment;
import ql.ast.visistor.environment.EnvironmentVariable;


/**
 * Created by Erik on 27-2-2017.
 */
public class EnvASTVisitor extends ASTVisitor<Void> {
    private Environment environment;

    public Environment startVisitor(ASTNode node){
        environment = new Environment();
        node.accept(this);
        return environment;
    }

    public Void visit(Question node) {
        EnvironmentVariable envVar = new EnvironmentVariable(node.getType());
        environment.addVariable(node.getId().getValue(), envVar);
        return null;
    }



    public Void visit(QuestionExpr node) {
        EnvironmentVariable envVar = new EnvironmentVariable(node.getType(), node.getExpr());
        environment.addVariable(node.getId().getValue(), envVar);

        node.getExpr().accept(this);
        return null;
    }


}
