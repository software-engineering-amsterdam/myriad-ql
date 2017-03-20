package ql.visistor;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import ql.ast.*;
import ql.ast.expressions.binop.*;
import ql.ast.expressions.monop.Neg;
import ql.ast.expressions.monop.Not;
import ql.ast.expressions.monop.Pos;
import ql.ast.literals.*;
import ql.ast.types.*;
import ql.gui.fields.*;
import ql.visistor.environment.Env;
import ql.visistor.interfaces.BaseVisitor;
import ql.gui.*;
import ql.visistor.interfaces.ExpressionVisitor;
import ql.visistor.interfaces.TypeVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erik on 28-2-2017.
 */
public class ViewASTVisitor implements BaseVisitor<VBox>, ExpressionVisitor<GUIExpr>, TypeVisitor<QLField> {
    private final Env env;
    private GUIEvaluator evaluator;

    public ViewASTVisitor(Env env) {
        this.env = env;
    }

    public Scene startVisitor(Form node) {
        evaluator = new GUIEvaluator();
        return new Scene(node.accept(this));
    }

    public VBox visit(Form node) {
        VBox statements = node.getStatements().accept(this);
        GUIForm formBox = new GUIForm(node.getName(), statements);
        evaluator.setGUIForm(formBox);
        return formBox;
    }


    public VBox visit(Statements node) {
        List<Statement> statements = node.getItems();
        List<VBox> statementBoxes = new ArrayList<>();
        for (Statement statement: statements) {
            VBox stat = statement.accept(this);
            if(stat != null)
                statementBoxes.add(stat);
        }

        return new GUIStatements(statementBoxes.toArray(new VBox[statementBoxes.size()]));
    }

    public VBox visit(If node) {
        GUIExpr condition = node.getCondition().accept(this);
        VBox statements = node.getIfBlock().accept(this);
        return new GUIIf(env, condition, statements);
    }

    public VBox visit(IfElse node) {
        GUIExpr condition = node.getCondition().accept(this);
        VBox ifStatements = node.getIfBlock().accept(this);
        VBox elseStatements = node.getElseBlock().accept(this);
        return new GUIIfElse(condition, ifStatements, elseStatements);
    }


    public VBox visit(Question node) {
        return new GUIQuestion(node.getQuestion(), node.getId(), node.getType().accept(this));
    }

    public VBox visit(QuestionExpr node) {
        return new GUIQuestion(node.getQuestion(), node.getId(), node.getType().accept(this));
    }

    @Override
    public QLField visit(BooleanType node) {
        return new BooleanField(evaluator);
    }

    @Override
    public QLField visit(IntType node) {
        return new IntField(evaluator);
    }

    @Override
    public QLField visit(FloatType node) {
        return new FloatField(evaluator);
    }

    @Override
    public QLField visit(StringType node) {
        return new StringField(evaluator);
    }

    @Override
    public QLField visit(ErrorType node) {
        return null;
    }

    @Override
    public GUIExpr visit(QLIdent node) {
        return null;
    }

    @Override
    public GUIExpr visit(QLBoolean node) {
        return null;
    }

    @Override
    public GUIExpr visit(QLInt node) {
        return null;
    }

    @Override
    public GUIExpr visit(QLString node) {
        return null;
    }

    @Override
    public GUIExpr visit(QLFloat node) {
        return null;
    }

    @Override
    public GUIExpr visit(Add node) {
        return null;
    }

    @Override
    public GUIExpr visit(Div node) {
        return null;
    }

    @Override
    public GUIExpr visit(Eq node) {
        return null;
    }

    @Override
    public GUIExpr visit(GEq node) {
        return null;
    }

    @Override
    public GUIExpr visit(GT node) {
        return null;
    }

    @Override
    public GUIExpr visit(LEq node) {
        return null;
    }

    @Override
    public GUIExpr visit(LT node) {
        return null;
    }

    @Override
    public GUIExpr visit(Mul node) {
        return null;
    }

    @Override
    public GUIExpr visit(NEq node) {
        return null;
    }

    @Override
    public GUIExpr visit(Sub node) {
        return null;
    }

    @Override
    public GUIExpr visit(And node) {
        return null;
    }

    @Override
    public GUIExpr visit(Or node) {
        return null;
    }

    @Override
    public GUIExpr visit(Pos node) {
        return null;
    }

    @Override
    public GUIExpr visit(Neg node) {
        return null;
    }

    @Override
    public GUIExpr visit(Not node) {
        return null;
    }
}
