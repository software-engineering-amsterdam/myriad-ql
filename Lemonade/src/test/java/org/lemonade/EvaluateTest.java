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
import org.lemonade.nodes.expressions.value.UndefinedValue;
import org.lemonade.nodes.types.QLBooleanType;
import org.lemonade.nodes.types.QLDateType;
import org.lemonade.nodes.types.QLDecimalType;
import org.lemonade.nodes.types.QLIntegerType;
import org.lemonade.nodes.types.QLMoneyType;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 */
public class EvaluateTest {

<<<<<<< Updated upstream
    private IntegerValue two;
    private IntegerValue one;
    private DecimalValue onePointFive;
    private MoneyValue oneFifty;
    private NumericValue<?> onePointTwo;
=======
    IntegerValue zero;
    IntegerValue two;
    IntegerValue one;
    DecimalValue zeroPointFive;
    DecimalValue onePointFive;
    DecimalValue twoPointZero;
    MoneyValue oneFifty;
    NumericValue onePointTwo;
>>>>>>> Stashed changes

    DateValue date;
    DateValue dateTwo;

    @Before
    public void setUp() throws ParseException {
        zero = new IntegerValue(new QLIntegerType(), 0);
        two = new IntegerValue(new QLIntegerType(), 2);
        one = new IntegerValue(new QLIntegerType(), 1);
        zeroPointFive = new DecimalValue(new QLDecimalType(), 0.5);
        onePointFive = new DecimalValue(new QLDecimalType(), 1.5);
        twoPointZero = new DecimalValue(new QLDecimalType(), 2.0);
        oneFifty = new MoneyValue(new QLMoneyType(), 1.50);
        onePointTwo = new DecimalValue(new QLDecimalType(), 1.2);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        date = new DateValue(new QLDateType(), sdf.parse("21/12/2012"));
        dateTwo = new DateValue(new QLDateType(), sdf.parse("22/07/1991"));

    }

    @Test
    public void testBooleanValue() {
        BooleanValue boolTrue = new BooleanValue(new QLBooleanType(), true);
        BooleanValue boolFalse = new BooleanValue(new QLBooleanType(), false);

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

<<<<<<< Updated upstream
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
=======
    //
    //    @Test
    //    public void testNumericMinus() {
    //        IntegerValue twoMinusOne = two.minus(one);
    //        DecimalValue twoMinusOnePointFive = two.minus(onePointFive);
    //        MoneyValue twoMinusOneFifty = two.minus(oneFifty);
    //        NumericValue twoMinusOnePointTwo = two.minus(onePointTwo);
    //
    //        assertThat(twoMinusOne.getValue()).isEqualTo(1);
    //        assertThat(twoMinusOne).isInstanceOf(IntegerValue.class);
    //
    //        assertThat(twoMinusOnePointFive.getValue()).isEqualTo(0.5);
    //        assertThat(twoMinusOnePointFive).isInstanceOf(DecimalValue.class);
    //
    //        assertThat(twoMinusOneFifty.getValue()).isEqualTo(0.5);
    //        assertThat(twoMinusOneFifty).isInstanceOf(MoneyValue.class);
    //
    //        assertThat(twoMinusOnePointTwo.getValue()).isEqualTo(0.8);
    //        assertThat(twoMinusOnePointTwo).isInstanceOf(DecimalValue.class);
    //    }
>>>>>>> Stashed changes

    @Test
    public void testNumericProduct() {
        NumericValue<?> result = two.product(onePointTwo);
        assertThat(result).isInstanceOf(DecimalValue.class);

        NumericValue<?> result2 = result.product(two);
        assertThat(result2).isInstanceOf(DecimalValue.class);
        assertThat(result2.getValue()).isEqualTo(4.8);
    }

    @Test
    public void testDateValue() {
        assertThat(date.compareTo(dateTwo)).isEqualTo(1);
        assertThat(date.compareTo(date)).isEqualTo(0);
        assertThat(date.equals(dateTwo)).isEqualTo(false);
    }

    @Test
    public void testDivision() {
        assertThat(one.divide(two)).isEqualTo(zero);
        assertThat(one.divide(twoPointZero).getValue()).isEqualTo(zeroPointFive.getValue());
    }
}
