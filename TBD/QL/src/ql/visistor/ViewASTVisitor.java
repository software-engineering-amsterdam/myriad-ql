package ql.visistor;

import javafx.scene.Scene;
import javafx.stage.Stage;
import ql.ast.*;
import ql.ast.types.*;
import ql.gui.elements.*;
import ql.gui.evaluator.GUIEvaluator;
import ql.gui.elements.fields.*;
import ql.visistor.interfaces.BaseVisitor;
import ql.visistor.interfaces.TypeVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erik on 28-2-2017.
 */
public class ViewASTVisitor implements BaseVisitor<GUIElement>, TypeVisitor<QLField> {
    private final GUIEvaluator evaluator;

    public ViewASTVisitor(Stage primaryStage) {
        this.evaluator = new GUIEvaluator(primaryStage);
    }

    public Scene startVisitor(Form node) {
        return new Scene(node.accept(this));
    }

    public GUIElement visit(Form node) {
        GUIElement statements = node.getStatements().accept(this);
        GUIForm formBox = new GUIForm(node.getName(), statements);
        evaluator.setGUIForm(formBox);
        return formBox;
    }


    public GUIElement visit(Statements node) {
        List<Statement> statements = node.getItems();
        List<GUIElement> statementBoxes = new ArrayList<>();
        for (Statement statement: statements) {
            GUIElement stat = statement.accept(this);
            if(stat != null)
                statementBoxes.add(stat);
        }

        return new GUIStatements(statementBoxes.toArray(new GUIElement[statementBoxes.size()]));
    }

    public GUIElement visit(If node) {
        GUIElement statements = node.getIfBlock().accept(this);
        return new GUIIf(node.getCondition(), statements);
    }

    public GUIElement visit(IfElse node) {
        GUIElement ifStatements = node.getIfBlock().accept(this);
        GUIElement elseStatements = node.getElseBlock().accept(this);
        return new GUIIfElse(node.getCondition(), ifStatements, elseStatements);
    }


    public GUIElement visit(Question node) {
        return new GUIQuestion(node.getQuestion(), node.getId(), node.getType().accept(this));
    }

    public GUIElement visit(QuestionExpr node) {
        return new GUIQuestionExpr(node.getQuestion(), node.getId(), node.getType().accept(this), node.getExpr());
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
}
