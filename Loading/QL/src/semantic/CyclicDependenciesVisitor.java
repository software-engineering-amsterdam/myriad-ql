package semantic;

import ast.*;
import ast.atom.BoolAtom;
import ast.atom.IntegerAtom;
import ast.atom.StringAtom;
import ast.expression.*;
import ast.type.*;

import QL.Error;

import java.util.List;

public class CyclicDependenciesVisitor implements FormVisitor, ast.ExpressionVisitor<Type> {
    private final Environment environment;
    private Question current;

    public CyclicDependenciesVisitor(Environment environment) {
        this.environment = environment;
    }

    @Override
        public void visit(Form form) {
        form.getBlock().accept(this);
    }

    @Override
    public void visit(Block block) {
        for (BlockItem blockItem : block.getBlockItems()) {
            blockItem.accept(this);
        }
    }

    @Override
    public void visit(BlockItem blockItem) {
        blockItem.accept(this);
    }

    @Override
    public void visit(Question question) {
    }

    @Override
    public void visit(ComputedQuestion question) {
        current = question;
        question.getComputedQuestion().accept(this);
    }

    @Override
    public void visit(Statement statement) {

    }

    @Override
    public void visit(IfElseStatement statement) {

    }

    @Override
    public Type visit(AddExpression expr) {
        expr.getLhs().accept(this);
        expr.getRhs().accept(this);

        return null;
    }

    @Override
    public Type visit(AndExpression expr) {
        expr.getLhs().accept(this);
        expr.getRhs().accept(this);

        return null;
    }

    @Override
    public Type visit(DivExpression expr) {
        expr.getLhs().accept(this);
        expr.getRhs().accept(this);

        return null;
    }

    @Override
    public Type visit(EqExpression expr) {
        expr.getLhs().accept(this);
        expr.getRhs().accept(this);

        return null;
    }

    @Override
    public Type visit(GEqExpression expr) {
        expr.getLhs().accept(this);
        expr.getRhs().accept(this);

        return null;
    }

    @Override
    public Type visit(GExpression expr) {
        expr.getLhs().accept(this);
        expr.getRhs().accept(this);

        return null;
    }

    @Override
    public Type visit(IdExpression id) {

        environment.addReference(current.getVariable(), id.getName());
        check();

        return null;
    }

    @Override
    public Type visit(LEqExpression expr) {
        expr.getLhs().accept(this);
        expr.getRhs().accept(this);

        return null;
    }

    @Override
    public Type visit(LExpression expr) {
        expr.getLhs().accept(this);
        expr.getRhs().accept(this);

        return null;
    }

    @Override
    public Type visit(MinusExpression expr) {
        expr.getLhs().accept(this);

        return null;
    }


    @Override
    public Type visit(MulExpression expr) {
        expr.getLhs().accept(this);
        expr.getRhs().accept(this);

        return null;
    }

    @Override
    public Type visit(NEqExpression expr) {
        expr.getLhs().accept(this);
        expr.getRhs().accept(this);

        return null;
    }

    @Override
    public Type visit(NotExpression expr) {
        expr.getLhs().accept(this);

        return null;
    }

    @Override
    public Type visit(OrExpression expr) {
        expr.getLhs().accept(this);
        expr.getRhs().accept(this);

        return null;
    }

    @Override
    public Type visit(PlusExpression expr) {
        expr.getLhs().accept(this);

        return null;
    }

    @Override
    public Type visit(SubExpression expr) {
        expr.getLhs().accept(this);
        expr.getRhs().accept(this);

        return null;
    }

    @Override
    public Type visit(BoolAtom expr) {
        return null;
    }

    @Override
    public Type visit(IntegerAtom expr) {
        return null;
    }

    @Override
    public Type visit(StringAtom expr) {
        return null;
    }

    private void check() {
        List<String> references = environment.getReferences(current.getVariable());

        for (String reference: references) {
            List<String> cycleReferences = environment.getReferences(reference);
            if (cycleReferences != null && cycleReferences.contains(current.getVariable())) {
                // TODO add error!
            	environment.getFaults().add(new Error("There is a cyclic dependency in "
            			+ "the computed questions " + current.getVariable() + " and " + reference, 
            			current.getLine())); 
            	
                System.out.println("CYCLIC DEPENDENCY FOUND");
            }
        }
    }
}
