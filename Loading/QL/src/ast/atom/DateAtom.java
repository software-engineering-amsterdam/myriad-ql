package ast.atom;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ast.Visitor;

public class DateAtom extends Atom {
    private Date value;

    // TODO consistency with other atoms
    public DateAtom(String value) {
        DateFormat format = new SimpleDateFormat("dd.MM.YY");
        try {
            this.value = format.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Date getValue() {
        return this.value;
    }
    
	@Override
	public void accept(Visitor v) {
		v.visit(this);		
	}
}