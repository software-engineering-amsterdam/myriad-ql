package org.uva.hatt.taxform.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import org.antlr.v4.runtime.tree.ParseTree;
import org.uva.hatt.taxform.ast.ASTGenerator;
import org.uva.hatt.taxform.ast.nodes.Form;
import org.uva.hatt.taxform.ast.visitors.EnvironmentsTable;
import org.uva.hatt.taxform.ast.visitors.QLVisitor;
import org.uva.hatt.taxform.ast.visitors.Visitor;

import java.io.IOException;

public class Taxform extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        String input = "form taxOfficeExample { \"Did you sell a house in 2010?\" hasSoldHouse: boolean \"Did you buy a house in 2010?\" hasBoughtHouse: string \"Did you enter a loan?\" hasMaintLoan: integer \"What was the selling price?\" sellingPrice: money }";
        ParseTree tree = ASTGenerator.getParseTree(input);
        QLVisitor visitor = new QLVisitor();
        visitor.visit(tree);

        Form form = visitor.getForm();

        EnvironmentsTable environmentsTable = new EnvironmentsTable();
        Visitor uiVisitor = new UIVisitor(stage, environmentsTable);
        uiVisitor.visit(form);

        stage.show();
    }
}