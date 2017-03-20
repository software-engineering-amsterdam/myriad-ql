package org.lemonade;

import java.text.ParseException;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.lemonade.nodes.expressions.literal.BooleanLiteral;
import org.lemonade.nodes.expressions.literal.DateLiteral;
import org.lemonade.nodes.expressions.literal.DecimalLiteral;
import org.lemonade.nodes.expressions.literal.IntegerLiteral;
import org.lemonade.nodes.expressions.literal.MoneyLiteral;
import org.lemonade.nodes.expressions.literal.NumericLiteral;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 *
 */
public class EvaluateTest {

    private IntegerLiteral zero;
    private IntegerLiteral two;
    private IntegerLiteral one;
    private DecimalLiteral zeroPointFive;
    private DecimalLiteral onePointZero;
    private DecimalLiteral onePointFive;
    private DecimalLiteral twoPointZero;
    private MoneyLiteral oneFifty;
    private MoneyLiteral twoFifty;
    private NumericLiteral<?> onePointTwo;

    private DateLiteral date;
    private DateLiteral dateTwo;

    @Before
    public void setUp() throws ParseException {
        zero = new IntegerLiteral(0);
        two = new IntegerLiteral(2);
        one = new IntegerLiteral(1);

        zeroPointFive = new DecimalLiteral(0.5);
        onePointFive = new DecimalLiteral(1.5);
        onePointZero = new DecimalLiteral(1.0);
        twoPointZero = new DecimalLiteral(2.0);

        oneFifty = new MoneyLiteral(1.50);
        twoFifty = new MoneyLiteral(2.50);

        onePointTwo = new DecimalLiteral(1.2);

        date = new DateLiteral(LocalDate.of(2010, 1, 1));
        dateTwo = new DateLiteral(LocalDate.of(2012, 1, 1));
    }

    @Test
    public void testBooleanValue() {
        BooleanLiteral boolTrue = new BooleanLiteral(true);
        BooleanLiteral boolFalse = new BooleanLiteral(false);

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
        IntegerLiteral onePlusTwo = one.plus(two);
        DecimalLiteral onePlusOnePointFive = one.plus(onePointFive);
        MoneyLiteral onePlusOneFifty = one.plus(oneFifty);
        NumericLiteral<?> onePlusOnePointTwo = one.plus(onePointTwo);

        assertThat(onePlusTwo.getValue()).isEqualTo(3);
        assertThat(onePlusTwo).isInstanceOf(IntegerLiteral.class);

        assertThat(onePlusOnePointFive.getValue()).isEqualTo(2.5);
        assertThat(onePlusOnePointFive).isInstanceOf(DecimalLiteral.class);

        assertThat(onePlusOneFifty.getValue()).isEqualTo(2.50);
        assertThat(onePlusOneFifty).isInstanceOf(MoneyLiteral.class);

        assertThat(onePlusOnePointTwo.getValue()).isEqualTo(2.2);
        assertThat(onePlusOnePointTwo).isInstanceOf(DecimalLiteral.class);
    }

    @Test
    public void testNumericMinus() {
        IntegerLiteral twoMinusOne = two.minus(one);
        DecimalLiteral twoMinusOnePointFive = two.minus(onePointFive);
        MoneyLiteral twoMinusOneFifty = two.minus(oneFifty);
        NumericLiteral<?> twoMinusOnePointTwo = two.minus(onePointTwo);

        assertThat(twoMinusOne.getValue()).isEqualTo(1);
        assertThat(twoMinusOne).isInstanceOf(IntegerLiteral.class);

        assertThat(twoMinusOnePointFive.getValue()).isEqualTo(0.5);
        assertThat(twoMinusOnePointFive).isInstanceOf(DecimalLiteral.class);

        assertThat(twoMinusOneFifty.getValue()).isEqualTo(0.5);
        assertThat(twoMinusOneFifty).isInstanceOf(MoneyLiteral.class);

        assertThat(twoMinusOnePointTwo.getValue()).isEqualTo(0.8);
        assertThat(twoMinusOnePointTwo).isInstanceOf(DecimalLiteral.class);
    }

    @Test
    public void testNumericProduct() {
        NumericLiteral<?> result = two.product(onePointTwo);
        assertThat(result).isInstanceOf(DecimalLiteral.class);

        NumericLiteral<?> result2 = result.product(two);
        assertThat(result2).isInstanceOf(DecimalLiteral.class);
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
