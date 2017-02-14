package ql.ast.literals;

import ql.ast.ASTNode;
import ql.ast.QLLiteral;
import ql.ast.visistor.ASTVisitor;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by rico on 14-2-17.
 */
public class QLDate implements ASTNode, QLLiteral {
    private Calendar qlDate;

    public QLDate(int day, int month, int year) {
        this.qlDate = new GregorianCalendar(year, month, day);
        //System.out.println(this.qlDate);
    }

    public <T> T visitThis(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
