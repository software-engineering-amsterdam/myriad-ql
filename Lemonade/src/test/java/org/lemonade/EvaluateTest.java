package org.lemonade;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;
import org.lemonade.nodes.expressions.value.BooleanValue;
import org.lemonade.nodes.expressions.value.DateValue;
import org.lemonade.nodes.expressions.value.DecimalValue;
import org.lemonade.nodes.expressions.value.IntegerValue;
import org.lemonade.nodes.expressions.value.MoneyValue;
import org.lemonade.nodes.expressions.value.NumericValue;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 */
public class EvaluateTest {

    private IntegerValue zero;
    private IntegerValue two;
    private IntegerValue one;
    private DecimalValue zeroPointFive;
    private DecimalValue onePointZero;
    private DecimalValue onePointFive;
    private DecimalValue twoPointZero;
    private MoneyValue oneFifty;
    private NumericValue<?> onePointTwo;


    DateValue date;
    DateValue dateTwo;

    @Before
    public void setUp() throws ParseException {
        zero = new IntegerValue(0);
        two = new IntegerValue(2);
        one = new IntegerValue(1);
        zeroPointFive = new DecimalValue(0.5);
        onePointFive = new DecimalValue(1.5);
        onePointZero = new DecimalValue(1.0);
        twoPointZero = new DecimalValue(2.0);
        oneFifty = new MoneyValue(1.50);
        onePointTwo = new DecimalValue(1.2);

//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//        date = new DateValue(sdf.parse("21/12/2012"));
//        dateTwo = new DateValue(sdf.parse("22/07/1991"));

    }

    @Test
    public void testBooleanValue() {
        BooleanValue boolTrue = new BooleanValue(true);
        BooleanValue boolFalse = new BooleanValue(false);

        assertThat(boolTrue.getValue()).isInstanceOf(Boolean.class);
        assertThat(boolTrue.or(boolFalse).getValue()).isTrue();
        assertThat(boolTrue.or(boolTrue).getValue()).isTrue();
        assertThat(boolTrue.and(boolFalse).getValue()).isFalse();
        assertThat(boolTrue.and(boolTrue).getValue()).isTrue();
    }

    @Test
    public void testIntegerValue() {
        assertThat(one.compareTo(one)).isEqualTo(0);
    }

    @Test
    public void testNumericPlus() {
        IntegerValue onePlusTwo = one.plus(two);
        DecimalValue onePlusOnePointFive = one.plus(onePointFive);
        MoneyValue onePlusOneFifty = one.plus(oneFifty);
        NumericValue<?> onePlusOnePointTwo = one.plus(onePointTwo);

        assertThat(onePlusTwo.getValue()).isEqualTo(3);
        assertThat(onePlusTwo).isInstanceOf(IntegerValue.class);

        assertThat(onePlusOnePointFive.getValue()).isEqualTo(2.5);
        assertThat(onePlusOnePointFive).isInstanceOf(DecimalValue.class);

        assertThat(onePlusOneFifty.getValue()).isEqualTo(2.50);
        assertThat(onePlusOneFifty).isInstanceOf(MoneyValue.class);

        assertThat(onePlusOnePointTwo.getValue()).isEqualTo(2.2);
        assertThat(onePlusOnePointTwo).isInstanceOf(DecimalValue.class);
    }

    @Test
    public void testNumericMinus() {
        IntegerValue twoMinusOne = two.minus(one);
        DecimalValue twoMinusOnePointFive = two.minus(onePointFive);
        MoneyValue twoMinusOneFifty = two.minus(oneFifty);
        NumericValue<?> twoMinusOnePointTwo = two.minus(onePointTwo);

        assertThat(twoMinusOne.getValue()).isEqualTo(1);
        assertThat(twoMinusOne).isInstanceOf(IntegerValue.class);

        assertThat(twoMinusOnePointFive.getValue()).isEqualTo(0.5);
        assertThat(twoMinusOnePointFive).isInstanceOf(DecimalValue.class);

        assertThat(twoMinusOneFifty.getValue()).isEqualTo(0.5);
        assertThat(twoMinusOneFifty).isInstanceOf(MoneyValue.class);

        assertThat(twoMinusOnePointTwo.getValue()).isEqualTo(0.8);
        assertThat(twoMinusOnePointTwo).isInstanceOf(DecimalValue.class);
    }

    @Test
    public void testNumericProduct() {
        NumericValue<?> result = two.product(onePointTwo);
        assertThat(result).isInstanceOf(DecimalValue.class);

        NumericValue<?> result2 = result.product(two);
        assertThat(result2).isInstanceOf(DecimalValue.class);
        assertThat(result2.getValue()).isEqualTo(4.8);
    }

//    @Test
//    public void testDateValue() {
//        assertThat(date.compareTo(dateTwo)).isEqualTo(1);
//        assertThat(date.compareTo(date)).isEqualTo(0);
//        assertThat(date.equals(dateTwo)).isEqualTo(false);
//    }

    @Test
    public void testDivision() {
        assertThat(one.divide(two)).isEqualTo(zero);
        assertThat(one.divide(twoPointZero).getValue()).isEqualTo(zeroPointFive.getValue());
        assertThat(onePointTwo.divide(onePointTwo).getValue()).isEqualTo(onePointZero.getValue());
    }

    @Test
    public void testNeg(){
        assertThat(one.neg().getValue()).isEqualTo(-1);
    }
}
