package UvA.Gamma.GUI;

import UvA.Gamma.AST.Computed;
import UvA.Gamma.AST.Condition;
import UvA.Gamma.AST.IdentifiableFormItem;
import UvA.Gamma.AST.Question;
import UvA.Gamma.AST.Types.DateType;
import UvA.Gamma.AST.Types.Type;
import UvA.Gamma.Visitors.UIVisitor;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by casboot on 16-04-17.
 */
public class DefaultWidgetBuilder implements WidgetBuilder {

    @Override
    public void getWidget(Question ques) {
        Type type = ques.getType();
        widgetBuilder(type.returnType());
    }

    @Override
    public void getWidget(Computed com) {
        //Zelfde idee als bij Question
    }

    @Override
    public void getWidget(Condition con) {
        //Zelfde idee als bij Question

    }

    public DatePicker widgetBuilder(DateType Date) {
        final DatePicker datePicker = new DatePicker();

        datePicker.setOnAction(t -> {
            LocalDate date = datePicker.getValue();
            System.err.println("Selected date: " + date);
        });
        return datePicker;
    }

    public CheckBox widgetBuilder(Boolean bool) {
    //Deze is wel tricky want hier hebben we dus ook het form en de gridpane voor nodig. Of een deel van deze implementatie moet naar de visitor zelf verhuizen.
        assert rootGrid != null;
        Text questionLabel = new Text(question.getLabel());
        CheckBox input = new CheckBox();

        input.selectedProperty().addListener((observable, oldValue, newValue) ->
                form.stream().forEach(
                        formItem -> formItem.idChanged(form, question, String.valueOf(newValue))));

        rootGrid.addRow(getRowCount(rootGrid) + 1, questionLabel, input);
    }

    public void showMoney(Question question) {
        Text questionLabel = new Text(question.getLabel());

        Text euroLabel = new Text("'");
        TextField input = new TextField();
        questionOnUpdate(question, input);

        HBox box = new HBox();
        box.getChildren().addAll(euroLabel, input);
        box.setAlignment(Pos.CENTER_RIGHT);

        rootGrid.addRow(getRowCount(rootGrid) + 1, questionLabel, box);
        //Moet iets worden van return box.
    }







}

    getWidget(Question q){
        widget = q.type.widget(this, q)
        //blabla make widget label etc.
    }

    getWidget(BooleanType type){
        // Maak bool widget
    }

    getWidget(NumberType type){

    }