package org.uva.hatt.taxform.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import org.antlr.v4.runtime.tree.ParseTree;
import org.uva.hatt.taxform.ast.ASTGenerator;
import org.uva.hatt.taxform.ast.nodes.Form;
import org.uva.hatt.taxform.ast.visitors.QLVisitor;
import org.uva.hatt.taxform.ast.visitors.Visitor;

import java.io.IOException;

public class Taxform extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        String input = "form taxOfficeExample { \"Did you sell a house in 2010?\" hasSoldHouse: boolean }";
        ParseTree tree = ASTGenerator.getParseTree(input);
        QLVisitor visitor = new QLVisitor();
        visitor.visit(tree);

        Form form = visitor.getForm();

        Visitor uiVisitor = new UIVisitor(stage);
        uiVisitor.visit(form);

        stage.show();
    }
}