package UvA.Gamma.AST.Values;

import UvA.Gamma.AST.ASTNode;
import UvA.Gamma.AST.Question;
import UvA.Gamma.GUI.FXMLController;
import UvA.Gamma.Validation.TypeChecker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tjarco, 05-03-17.
 */
public class DateValue extends Value implements ASTNode {
    private Date value;
    private SimpleDateFormat format;

    public DateValue(String value) {
        format = new SimpleDateFormat("yyyy-MM-dd");
        setValue(value);
    }

    @Override
    public Type getType() {
        return Type.DATE;
    }

    @Override
    public boolean conformsToType(Type type) {
        return false; // no operations on dates
    }

    @Override
    public void setValue(String value) {
        if (value.isEmpty()) return; ///The date might be initialized empty
        try {
            this.value = format.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String computableString() {
        return this.toString();
    }

    @Override
    public boolean validate(String value, TypeChecker typeChecker) {
        return typeChecker.checkDate(value);
    }

    @Override
    public void showQuestion(FXMLController screen, Question question) {
        screen.showDateValue(question);
    }

    @Override
    public String toString() {
        if (this.value == null) return ""; //Value can be empty
        return format.format(this.value);
    }
}
