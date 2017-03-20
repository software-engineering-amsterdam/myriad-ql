package ql.gui.evaluator;

import javafx.stage.Stage;
import ql.ast.expressions.binop.*;
import ql.ast.expressions.monop.Neg;
import ql.ast.expressions.monop.Not;
import ql.ast.expressions.monop.Pos;
import ql.ast.literals.*;
import ql.gui.elements.*;
import ql.values.Value;
import ql.visistor.interfaces.ExpressionVisitor;

/**
 * Created by Erik on 20-3-2017.
 */
public class GUIEvaluator implements BaseEvaluator<Void>, ExpressionVisitor<Value<?>>{
    private GUIForm form;
    private final GUIEnv guiEnv = new GUIEnv();
    private final Stage primaryStage;

    public GUIEvaluator(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setGUIForm(GUIForm form){
        this.form = form;
    }

    public void evaluate() {
        visit(form);
        primaryStage.sizeToScene();
    }

    public Void visit(GUIForm node) {
        node.getStatements().accept(this);
        return null;
    }

    public Void visit(GUIStatements node) {
        for (GUIElement statement : node.getStatements()) {
            statement.accept(this);
        }
        return null;
    }

    public Void visit(GUIIf node) {
        node.update(node.getCondition().accept(this));
        node.getIfStatements().accept(this);
        return null;
    }

    public Void visit(GUIIfElse node) {
        node.update(node.getCondition().accept(this));
        node.getIfStatements().accept(this);
        node.getElseStatements().accept(this);
        return null;
    }

    public Void visit(GUIQuestion node) {
        guiEnv.setValue(node.getKey(), node.getValue());
        return null;
    }

    public Void visit(GUIQuestionExpr node) {
        Value<?> value = node.getExpr().accept(this);
        node.update(value);
        guiEnv.setValue(node.getKey(), value);
        return null;
    }

    public Value visit(QLIdent node) {
        return guiEnv.getValue(node.getValue());
    }

    public Value visit(QLBoolean node) {
        return node.toValue();
    }

    public Value visit(QLInt node) {
        return node.toValue();
    }

    public Value visit(QLString node) {
        return node.toValue();
    }

    public Value visit(QLFloat node) {
        return node.toValue();
    }

    @Override
    public Value visit(Add node) {
        Value left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.add(right);
    }

    @Override
    public Value visit(Div node) {
        Value left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.div(right);
    }

    @Override
    public Value visit(Eq node) {
        Value left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.eq(right);
    }

    @Override
    public Value visit(GEq node) {
        Value left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.gEq(right);
    }

    @Override
    public Value visit(GT node) {
        Value left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.gT(right);
    }

    @Override
    public Value visit(LEq node) {
        Value left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.lEq(right);
    }

    @Override
    public Value visit(LT node) {
        Value left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.lT(right);
    }

    @Override
    public Value visit(Mul node) {
        Value left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.mul(right);
    }

    @Override
    public Value visit(NEq node) {
        Value left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.nEq(right);
    }

    @Override
    public Value visit(Sub node) {
        Value left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.sub(right);
    }

    @Override
    public Value visit(And node) {
        Value left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.and(right);
    }

    @Override
    public Value visit(Or node) {
        Value left, right;
        left = node.getLeft().accept(this);
        right = node.getRight().accept(this);

        return left.or(right);
    }

    @Override
    public Value visit(Pos node) {
        Value expr = node.getExpr().accept(this);
        return expr.pos();
    }

    @Override
    public Value visit(Not node) {
        Value expr = node.getExpr().accept(this);
        return expr.not();
    }

    @Override
    public Value visit(Neg node) {
        Value expr = node.getExpr().accept(this);
        return expr.neg();
    }

}
