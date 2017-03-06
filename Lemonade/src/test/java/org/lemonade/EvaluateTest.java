package org.lemonade;

import java.text.ParseException;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.lemonade.nodes.expressions.value.BooleanValue;
import org.lemonade.nodes.expressions.value.DateValue;
import org.lemonade.nodes.expressions.value.DecimalValue;
import org.lemonade.nodes.expressions.value.IntegerValue;
import org.lemonade.nodes.expressions.value.MoneyValue;
import org.lemonade.nodes.expressions.value.NumericValue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    private MoneyValue twoFifty;
    private NumericValue<?> onePointTwo;

    private DateValue date;
    private DateValue dateTwo;

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
        twoFifty = new MoneyValue(2.50);

        onePointTwo = new DecimalValue(1.2);

        date = new DateValue(LocalDate.of(2010, 1, 1));
        dateTwo = new DateValue(LocalDate.of(2012, 1, 1));
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


    @Test
    public void testDateValue() {
        assertThat(date.compareTo(dateTwo)).isNegative();
        assertThat(date.compareTo(date)).isZero();
        assertThat(date.equals(dateTwo)).isEqualTo(false);
    }


    @Test
    public void testDivision() {
        assertThat(one.divide(two)).isEqualTo(zero);
        assertThat(one.divide(twoPointZero).getValue()).isEqualTo(zeroPointFive.getValue());
        assertThat(onePointTwo.divide(onePointTwo).getValue()).isEqualTo(onePointZero.getValue());
    }

    @Test
    public void testGreaterThan() {
        assertThat(one.gT(two).getValue()).isFalse();
        assertThat(two.gT(one).getValue()).isTrue();
        assertThat(one.gT(one).getValue()).isFalse();

        assertThat(zeroPointFive.gT(onePointFive).getValue()).isFalse();
        assertThat(onePointFive.gT(zeroPointFive).getValue()).isTrue();
        assertThat(zeroPointFive.gT(zeroPointFive).getValue()).isFalse();

        assertThat(oneFifty.gT(twoFifty).getValue()).isFalse();
        assertThat(twoFifty.gT(oneFifty).getValue()).isTrue();
        assertThat(oneFifty.gT(oneFifty).getValue()).isFalse();

        assertThat(date.gT(dateTwo).getValue()).isFalse();
        assertThat(dateTwo.gT(date).getValue()).isTrue();
        assertThat(date.gT(date).getValue()).isFalse();
    }

    @Test
    public void testLessThan() {
        assertThat(one.lT(two).getValue()).isTrue();
        assertThat(two.lT(one).getValue()).isFalse();
        assertThat(one.lT(one).getValue()).isFalse();

        assertThat(zeroPointFive.lT(onePointFive).getValue()).isTrue();
        assertThat(onePointFive.lT(zeroPointFive).getValue()).isFalse();
        assertThat(zeroPointFive.lT(zeroPointFive).getValue()).isFalse();

        assertThat(oneFifty.lT(twoFifty).getValue()).isTrue();
        assertThat(twoFifty.lT(oneFifty).getValue()).isFalse();
        assertThat(oneFifty.lT(oneFifty).getValue()).isFalse();

        assertThat(date.lT(dateTwo).getValue()).isTrue();
        assertThat(dateTwo.lT(date).getValue()).isFalse();
        assertThat(date.lT(date).getValue()).isFalse();
    }

    @Test
    public void testGreaterThanOrEqual() {
        assertThat(one.gTEq(two).getValue()).isFalse();
        assertThat(two.gTEq(one).getValue()).isTrue();
        assertThat(one.gTEq(one).getValue()).isTrue();

        assertThat(zeroPointFive.gTEq(onePointFive).getValue()).isFalse();
        assertThat(onePointFive.gTEq(zeroPointFive).getValue()).isTrue();
        assertThat(zeroPointFive.gTEq(zeroPointFive).getValue()).isTrue();

        assertThat(oneFifty.gTEq(twoFifty).getValue()).isFalse();
        assertThat(twoFifty.gTEq(oneFifty).getValue()).isTrue();
        assertThat(oneFifty.gTEq(oneFifty).getValue()).isTrue();

        assertThat(date.gTEq(dateTwo).getValue()).isFalse();
        assertThat(dateTwo.gTEq(date).getValue()).isTrue();
        assertThat(date.gTEq(date).getValue()).isTrue();
    }

    @Test
    public void testLessThanOrEqual() {
        assertThat(one.lTEq(two).getValue()).isTrue();
        assertThat(two.lTEq(one).getValue()).isFalse();
        assertThat(one.lTEq(one).getValue()).isTrue();

        assertThat(zeroPointFive.lTEq(onePointFive).getValue()).isTrue();
        assertThat(onePointFive.lTEq(zeroPointFive).getValue()).isFalse();
        assertThat(zeroPointFive.lTEq(zeroPointFive).getValue()).isTrue();

        assertThat(oneFifty.lTEq(twoFifty).getValue()).isTrue();
        assertThat(twoFifty.lTEq(oneFifty).getValue()).isFalse();
        assertThat(oneFifty.lTEq(oneFifty).getValue()).isTrue();

        assertThat(date.lTEq(dateTwo).getValue()).isTrue();
        assertThat(dateTwo.lTEq(date).getValue()).isFalse();
        assertThat(date.lTEq(date).getValue()).isTrue();
    }

    @Test
    public void cantCompareDifferentTypes() {
        assertThatThrownBy(() -> one.gT(onePointFive))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot compare integer with decimal");

        assertThatThrownBy(() -> one.gT(oneFifty))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot compare integer with money");

        assertThatThrownBy(() -> one.gT(date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot compare integer with date");

        assertThatThrownBy(() -> zeroPointFive.gT(oneFifty))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot compare decimal with money");

        assertThatThrownBy(() -> zeroPointFive.gT(date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot compare decimal with date");

        assertThatThrownBy(() -> oneFifty.gT(date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot compare money with date");
    }

    @Test
    public void testNeg() {
        assertThat(one.neg().getValue()).isEqualTo(-1);
    }
}
