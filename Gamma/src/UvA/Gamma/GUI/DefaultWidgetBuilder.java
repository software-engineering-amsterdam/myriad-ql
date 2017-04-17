package UvA.Gamma.GUI;

import UvA.Gamma.AST.Computed;
import UvA.Gamma.AST.Expression.Identifier;
import UvA.Gamma.AST.Expression.Values.BooleanValue;
import UvA.Gamma.AST.Expression.Values.DateValue;
import UvA.Gamma.AST.Expression.Values.IdentifierValue;
import UvA.Gamma.AST.Expression.Values.NumberValue;
import UvA.Gamma.AST.Form;
import UvA.Gamma.AST.Question;
import UvA.Gamma.AST.Types.*;
import UvA.Gamma.Validation.TypeChecker;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by casboot on 16-04-17.
 */
public class DefaultWidgetBuilder implements WidgetBuilder {
    private Form form;
    private TypeChecker typeChecker;

    public DefaultWidgetBuilder(Form form) {
        this.form = form;
        this.typeChecker = new TypeChecker();
    }

    @Override
    public List<Node> getWidget(Question ques) {
        List<Node> widgets = new ArrayList<>();
        widgets.add(new Text(ques.getLabel()));
        widgets.add(ques.getType().typeNode(this, new Identifier(ques.getIdentifier())));
        return widgets;
    }

    @Override
    public List<Node> getWidget(Computed com) {
        List<Node> widgets = new ArrayList<>();
        widgets.add(new Text(com.getLabel()));
        Text valueText = new Text();
        valueText.textProperty().bindBidirectional(com.getStringValueProperty());
        widgets.add(valueText);
        return widgets;
    }

    @Override
    public Node getNode(BooleanType type, Identifier identifier) {
        return getCheckbox(identifier);
    }

    @Override
    public Node getNode(DateType type, Identifier identifier) {
        return getDatePicker(identifier);
    }

    @Override
    public Node getNode(IntegerType type, Identifier identifier) {
        return getTextfield(identifier, true);
    }

    @Override
    public Node getNode(DecimalType type, Identifier identifier) {
        return getTextfield(identifier, false);
    }

    @Override
    public Node getNode(MoneyType type, Identifier identifier) {
        return getMoneyBox(identifier);
    }

    private DatePicker getDatePicker(Identifier identifier) {
        DatePicker datePicker = new DatePicker();

        datePicker.setOnAction(t -> {
            LocalDate date = datePicker.getValue();
            if (typeChecker.checkDate(date.toString())) {
                IdentifierUpdatedVisitor identifierUpdatedVisitor =
                        new IdentifierUpdatedVisitor(
                                new IdentifierValue(identifier.toString(), new DateValue(date.toString())));
                form.forEach(formItem -> formItem.accept(identifierUpdatedVisitor));
            } else {
                datePicker.setStyle("-fx-text-fill: red");
            }
        });
        return datePicker;
    }

    private CheckBox getCheckbox(Identifier identifier) {
        CheckBox input = new CheckBox();

        input.selectedProperty().addListener((observable, oldValue, newValue) -> {
            IdentifierUpdatedVisitor identifierUpdatedVisitor =
                    new IdentifierUpdatedVisitor(
                            new IdentifierValue(identifier.toString(), new BooleanValue(String.valueOf(newValue))));
            form.forEach(formItem -> formItem.accept(identifierUpdatedVisitor));
        });

        return input;
    }

    private TextField getTextfield(Identifier identifier, boolean isInteger) {
        TextField textField = new TextField();

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if ((!isInteger && typeChecker.checkDouble(newValue)) || typeChecker.checkInteger(newValue)) {
                textField.setStyle("-fx-text-fill: green");
                IdentifierUpdatedVisitor identifierUpdatedVisitor =
                        new IdentifierUpdatedVisitor(new IdentifierValue(identifier.toString(), new NumberValue(newValue)));
                form.forEach(formItem -> formItem.accept(identifierUpdatedVisitor));
            } else {
                textField.setStyle("-fx-text-fill: red");
            }
        });
        return textField;
    }


    private HBox getMoneyBox(Identifier identifier) {
        Text euroLabel = new Text("€");
        TextField input = new TextField();

        input.textProperty().addListener((observable, oldValue, newValue) -> {
            if (typeChecker.checkMoney(newValue)) {
                input.setStyle("-fx-text-fill: green");
                IdentifierUpdatedVisitor identifierUpdatedVisitor =
                        new IdentifierUpdatedVisitor(new IdentifierValue(identifier.toString(), new NumberValue(newValue)));
                form.forEach(formItem -> formItem.accept(identifierUpdatedVisitor));
            } else {
                input.setStyle("-fx-text-fill: red");
            }
        });

        HBox box = new HBox();
        box.getChildren().addAll(euroLabel, input);
        box.setAlignment(Pos.CENTER_RIGHT);

        return box;
    }
}