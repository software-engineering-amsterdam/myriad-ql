package sc.ql.test.Value;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import sc.ql.gui.values.BooleanValue;
import sc.ql.gui.values.MoneyValue;

public class MoneyValueTest {
	public final BigDecimal value1 = new BigDecimal("2.15");
	public final BigDecimal value2 = new BigDecimal("4.30");
	public final BigDecimal value3 = new BigDecimal("1.15");
	public final BigDecimal value4 = new BigDecimal("2.15");
    @Test
    public void addValue() {
    	MoneyValue money1 = new MoneyValue(value1);
    	MoneyValue money2 = new MoneyValue(value2);

    	MoneyValue result = money1.add(money2);
    	BigDecimal expected = new BigDecimal("6.45");
    	assertEquals(expected, result.toMoney());
	}
    @Test
    public void substractValue() {
    	MoneyValue money1 = new MoneyValue(value1);
    	MoneyValue money2 = new MoneyValue(value2);

    	MoneyValue result = money1.subtract(money2);
    	BigDecimal expected = new BigDecimal("2.15");
    	assertEquals(expected, result.toMoney());
	}
    @Test
    public void divideValue() {
    	MoneyValue money1 = new MoneyValue(value1);
    	MoneyValue money2 = new MoneyValue(value2);

    	MoneyValue result = money1.divide(money2);
    	
    	BigDecimal expected = new BigDecimal("2.00");
    	assertEquals(expected, result.toMoney());
	}
    @Test
    public void multiplyValue() {
    	MoneyValue money1 = new MoneyValue(value1);
    	MoneyValue money2 = new MoneyValue(value2);

    	MoneyValue result = money1.multiply(money2);
    	BigDecimal expected = new BigDecimal("9.2450");
    	assertEquals(expected, result.toMoney());
	}
    @Test
    public void equalValue() {
    	MoneyValue money1 = new MoneyValue(value1);
    	MoneyValue money2 = new MoneyValue(value4);
    	
    	BooleanValue result = money1.equals(money2);
    	
    	assertTrue(result.toBoolean());
	}
    @Test
    public void notEqualValue() {
    	MoneyValue money1 = new MoneyValue(value1);
    	MoneyValue money2 = new MoneyValue(value2);

    	BooleanValue result = money1.equalsNot(money2);
    	assertTrue(result.toBoolean());
	}
    @Test
    public void greaterThenValue() {
    	MoneyValue money1 = new MoneyValue(value1);
    	MoneyValue money2 = new MoneyValue(value2);

    	BooleanValue result = money2.greaterThen(money1);
    	assertTrue(result.toBoolean());
	}
    @Test
    public void greaterThenEqualValue() {
    	MoneyValue money1 = new MoneyValue(value1);
    	MoneyValue money2 = new MoneyValue(value3);
    	MoneyValue money3 = new MoneyValue(value4);
    	
    	BooleanValue result1 = money1.greaterThenEqual(money2);
    	BooleanValue result2 = money1.greaterThenEqual(money3);
    	assertTrue(result1.toBoolean());
    	assertTrue(result2.toBoolean());	
	}
    @Test
    public void lessThenValue() {
    	MoneyValue money1 = new MoneyValue(value1);
    	MoneyValue money2 = new MoneyValue(value2);
    	
    	BooleanValue result = money1.lessThen(money2);
    	assertTrue(result.toBoolean());
	}
    @Test
    public void lessThenEqualValue() {
    	MoneyValue money1 = new MoneyValue(value1);
    	MoneyValue money2 = new MoneyValue(value2);
    	MoneyValue money3 = new MoneyValue(value4);
    	
    	BooleanValue result1 = money1.lessThenEqual(money2);
    	BooleanValue result2 = money1.lessThenEqual(money3);
    	assertTrue(result1.toBoolean());
    	assertTrue(result2.toBoolean());	
	}
}
