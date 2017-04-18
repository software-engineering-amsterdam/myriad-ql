package org.lemonade;

import java.text.ParseException;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.lemonade.gui.values.GuiBooleanValue;
import org.lemonade.gui.values.GuiDateValue;
import org.lemonade.gui.values.GuiDecimalValue;
import org.lemonade.gui.values.GuiIntegerValue;
import org.lemonade.gui.values.GuiMoneyValue;
import org.lemonade.gui.values.GuiNumericalValue;
import org.lemonade.gui.values.GuiStringValue;
import org.lemonade.gui.values.GuiUndefinedValue;
import org.lemonade.gui.values.GuiValue;

import static org.assertj.core.api.Assertions.assertThat;

public class EvaluateTest {

    private GuiIntegerValue zero;
    private GuiIntegerValue two;
    private GuiIntegerValue one;
    private GuiDecimalValue zeroPointZero;
    private GuiDecimalValue zeroPointFive;
    private GuiDecimalValue onePointZero;
    private GuiDecimalValue onePointFive;
    private GuiDecimalValue twoPointZero;
    private GuiMoneyValue zeroZero;
    private GuiMoneyValue oneFifty;
    private GuiMoneyValue twoFifty;
    private GuiNumericalValue<?> onePointTwo;

    private GuiBooleanValue boolTrue;
    private GuiBooleanValue boolFalse;

    private GuiUndefinedValue undefined;

    private GuiDateValue date;
    private GuiDateValue dateTwo;

    private GuiStringValue string;
    private GuiStringValue stringTwo;

    @Before
    public void setUp() throws ParseException {
        zero = new GuiIntegerValue(0);
        two = new GuiIntegerValue(2);
        one = new GuiIntegerValue(1);

        zeroPointZero = new GuiDecimalValue(0.0);
        zeroPointFive = new GuiDecimalValue(0.5);
        onePointFive = new GuiDecimalValue(1.5);
        onePointZero = new GuiDecimalValue(1.0);
        twoPointZero = new GuiDecimalValue(2.0);

        zeroZero = new GuiMoneyValue(0.0);
        oneFifty = new GuiMoneyValue(1.50);
        twoFifty = new GuiMoneyValue(2.50);

        onePointTwo = new GuiDecimalValue(1.2);

        date = new GuiDateValue(LocalDate.of(2010, 1, 1));
        dateTwo = new GuiDateValue(LocalDate.of(2012, 1, 1));

        undefined = new GuiUndefinedValue();

        boolTrue = new GuiBooleanValue(true);
        boolFalse = new GuiBooleanValue(false);

        string = new GuiStringValue("aaa");
        stringTwo = new GuiStringValue("abb");
    }

    @Test
    public void testBooleanValue() {
        GuiBooleanValue boolTrue = new GuiBooleanValue(true);
        GuiBooleanValue boolFalse = new GuiBooleanValue(false);

        assertThat(boolTrue.getValue()).isInstanceOf(Boolean.class);
        assertThat((Boolean) boolTrue.or(boolFalse).getValue()).isTrue();
        assertThat((Boolean) boolTrue.or(boolTrue).getValue()).isTrue();
        assertThat((Boolean) boolTrue.and(boolFalse).getValue()).isFalse();
        assertThat((Boolean) boolTrue.and(boolTrue).getValue()).isTrue();
    }

    @Test
    public void testIntegerValue() {
        assertThat(one.compareTo(one)).isEqualTo(0);
    }

    @Test
    public void testNumericPlus() {
        GuiValue<?> onePlusTwo = one.plus(two);
        GuiValue<?> onePlusOnePointFive = one.plus(onePointFive);
        GuiValue<?> onePlusOneFifty = one.plus(oneFifty);
        GuiValue<?> onePlusOnePointTwo = one.plus(onePointTwo);

        assertThat(onePlusTwo.getValue()).isEqualTo(3);
        assertThat(onePlusTwo).isInstanceOf(GuiIntegerValue.class);

        assertThat(onePlusOnePointFive.getValue()).isEqualTo(2.5);
        assertThat(onePlusOnePointFive).isInstanceOf(GuiDecimalValue.class);

        assertThat(onePlusOneFifty.getValue()).isEqualTo(2.50);
        assertThat(onePlusOneFifty).isInstanceOf(GuiMoneyValue.class);

        assertThat(onePlusOnePointTwo.getValue()).isEqualTo(2.2);
        assertThat(onePlusOnePointTwo).isInstanceOf(GuiDecimalValue.class);
    }

    @Test
    public void testNumericMinus() {
        GuiValue<?> twoMinusOne = two.min(one);
        GuiValue<?> twoMinusOnePointFive = two.min(onePointFive);
        GuiValue<?> twoMinusOneFifty = two.min(oneFifty);
        GuiValue<?> twoMinusOnePointTwo = (GuiValue<?>) two.min(onePointTwo);

        assertThat(twoMinusOne.getValue()).isEqualTo(1);
        assertThat(twoMinusOne).isInstanceOf(GuiIntegerValue.class);

        assertThat(twoMinusOnePointFive.getValue()).isEqualTo(0.5);
        assertThat(twoMinusOnePointFive).isInstanceOf(GuiDecimalValue.class);

        assertThat(twoMinusOneFifty.getValue()).isEqualTo(0.5);
        assertThat(twoMinusOneFifty).isInstanceOf(GuiMoneyValue.class);

        assertThat(twoMinusOnePointTwo.getValue()).isEqualTo(0.8);
        assertThat(twoMinusOnePointTwo).isInstanceOf(GuiDecimalValue.class);
    }

    @Test
    public void testNumericProduct() {
        GuiValue<?> result = two.prod(onePointTwo);
        assertThat(result).isInstanceOf(GuiDecimalValue.class);

        GuiNumericalValue<?> result2;

        result2 = (GuiNumericalValue<?>) result.prod(two);

        assertThat(result2).isInstanceOf(GuiDecimalValue.class);
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
        assertThat(one.div(two)).isEqualTo(zero);
        assertThat(one.div(twoPointZero).getValue()).isEqualTo(zeroPointFive.getValue());
        assertThat(onePointTwo.div(onePointTwo).getValue()).isEqualTo(onePointZero.getValue());

        assertThat(one.div(zero).isDefined()).isFalse();
        assertThat(onePointTwo.div(zero).isDefined()).isFalse();
        assertThat(onePointFive.div(zero).isDefined()).isFalse();
        assertThat(oneFifty.div(zero).isDefined()).isFalse();

        assertThat(one.div(zeroPointZero).isDefined()).isFalse();
        assertThat(onePointTwo.div(zeroPointZero).isDefined()).isFalse();
        assertThat(onePointFive.div(zeroPointZero).isDefined()).isFalse();
        assertThat(oneFifty.div(zeroPointZero).isDefined()).isFalse();

        assertThat(one.div(zeroZero).isDefined()).isFalse();
        assertThat(onePointTwo.div(zeroZero).isDefined()).isFalse();
        assertThat(onePointFive.div(zeroZero).isDefined()).isFalse();
        assertThat(oneFifty.div(zeroZero).isDefined()).isFalse();

        assertThat(zero.div(zero).isDefined()).isFalse();
        assertThat(zero.div(zero).isDefined()).isFalse();
        assertThat(zero.div(zero).isDefined()).isFalse();
        assertThat(zero.div(zero).isDefined()).isFalse();
    }

    @Test
    public void testGreaterThan() {
        assertThat((Boolean) one.gT(two).getValue()).isFalse();
        assertThat((Boolean) two.gT(one).getValue()).isTrue();
        assertThat((Boolean) one.gT(one).getValue()).isFalse();

        assertThat((Boolean) zeroPointFive.gT(onePointFive).getValue()).isFalse();
        assertThat((Boolean) onePointFive.gT(zeroPointFive).getValue()).isTrue();
        assertThat((Boolean) zeroPointFive.gT(zeroPointFive).getValue()).isFalse();

        assertThat((Boolean) oneFifty.gT(twoFifty).getValue()).isFalse();
        assertThat((Boolean) twoFifty.gT(oneFifty).getValue()).isTrue();
        assertThat((Boolean) oneFifty.gT(oneFifty).getValue()).isFalse();

        assertThat((Boolean) date.gT(dateTwo).getValue()).isFalse();
        assertThat((Boolean) dateTwo.gT(date).getValue()).isTrue();
        assertThat((Boolean) date.gT(date).getValue()).isFalse();

        assertThat((Boolean) string.gT(stringTwo).getValue()).isFalse();
        assertThat((Boolean) stringTwo.gT(string).getValue()).isTrue();
        assertThat((Boolean) string.gT(string).getValue()).isFalse();
    }

    @Test
    public void testLessThan() {
        assertThat((Boolean) one.lT(two).getValue()).isTrue();
        assertThat((Boolean) two.lT(one).getValue()).isFalse();
        assertThat((Boolean) one.lT(one).getValue()).isFalse();

        assertThat((Boolean) zeroPointFive.lT(onePointFive).getValue()).isTrue();
        assertThat((Boolean) onePointFive.lT(zeroPointFive).getValue()).isFalse();
        assertThat((Boolean) zeroPointFive.lT(zeroPointFive).getValue()).isFalse();

        assertThat((Boolean) oneFifty.lT(twoFifty).getValue()).isTrue();
        assertThat((Boolean) twoFifty.lT(oneFifty).getValue()).isFalse();
        assertThat((Boolean) oneFifty.lT(oneFifty).getValue()).isFalse();

        assertThat((Boolean) date.lT(dateTwo).getValue()).isTrue();
        assertThat((Boolean) dateTwo.lT(date).getValue()).isFalse();
        assertThat((Boolean) date.lT(date).getValue()).isFalse();

        assertThat((Boolean) string.lT(stringTwo).getValue()).isTrue();
        assertThat((Boolean) stringTwo.lT(string).getValue()).isFalse();
        assertThat((Boolean) string.lT(string).getValue()).isFalse();
    }

    @Test
    public void testGreaterThanOrEqual() {
        assertThat((Boolean) one.gTEq(two).getValue()).isFalse();
        assertThat((Boolean) two.gTEq(one).getValue()).isTrue();
        assertThat((Boolean) one.gTEq(one).getValue()).isTrue();

        assertThat((Boolean) zeroPointFive.gTEq(onePointFive).getValue()).isFalse();
        assertThat((Boolean) onePointFive.gTEq(zeroPointFive).getValue()).isTrue();
        assertThat((Boolean) zeroPointFive.gTEq(zeroPointFive).getValue()).isTrue();

        assertThat((Boolean) oneFifty.gTEq(twoFifty).getValue()).isFalse();
        assertThat((Boolean) twoFifty.gTEq(oneFifty).getValue()).isTrue();
        assertThat((Boolean) oneFifty.gTEq(oneFifty).getValue()).isTrue();

        assertThat((Boolean) date.gTEq(dateTwo).getValue()).isFalse();
        assertThat((Boolean) dateTwo.gTEq(date).getValue()).isTrue();
        assertThat((Boolean) date.gTEq(date).getValue()).isTrue();

        assertThat((Boolean) string.gTEq(stringTwo).getValue()).isFalse();
        assertThat((Boolean) stringTwo.gTEq(string).getValue()).isTrue();
        assertThat((Boolean) string.gTEq(string).getValue()).isTrue();
    }

    @Test
    public void testLessThanOrEqual() {
        assertThat((Boolean) one.lTEq(two).getValue()).isTrue();
        assertThat((Boolean) two.lTEq(one).getValue()).isFalse();
        assertThat((Boolean) one.lTEq(one).getValue()).isTrue();

        assertThat((Boolean) zeroPointFive.lTEq(onePointFive).getValue()).isTrue();
        assertThat((Boolean) onePointFive.lTEq(zeroPointFive).getValue()).isFalse();
        assertThat((Boolean) zeroPointFive.lTEq(zeroPointFive).getValue()).isTrue();

        assertThat((Boolean) oneFifty.lTEq(twoFifty).getValue()).isTrue();
        assertThat((Boolean) twoFifty.lTEq(oneFifty).getValue()).isFalse();
        assertThat((Boolean) oneFifty.lTEq(oneFifty).getValue()).isTrue();

        assertThat((Boolean) date.lTEq(dateTwo).getValue()).isTrue();
        assertThat((Boolean) dateTwo.lTEq(date).getValue()).isFalse();
        assertThat((Boolean) date.lTEq(date).getValue()).isTrue();

        assertThat((Boolean) string.lTEq(stringTwo).getValue()).isTrue();
        assertThat((Boolean) stringTwo.lTEq(string).getValue()).isFalse();
        assertThat((Boolean) string.lTEq(string).getValue()).isTrue();
    }

    @Test
    public void testBoolean() {
        assertThat((Boolean) boolTrue.and(boolFalse).getValue()).isFalse();
        assertThat((Boolean) boolTrue.and(boolTrue).getValue()).isTrue();
        assertThat((Boolean) boolTrue.or(boolFalse).getValue()).isTrue();
        assertThat((Boolean) boolFalse.and(boolFalse).getValue()).isFalse();
        assertThat((Boolean) boolFalse.or(boolFalse).getValue()).isFalse();
        assertThat(boolFalse.bang().getValue()).isTrue();
        assertThat(undefined.bang().isDefined()).isFalse();
    }

    @Test
    public void testUndefined() {
        assertThat(one.plus(undefined).isDefined()).isFalse();
        assertThat(one.min(undefined).isDefined()).isFalse();
        assertThat(one.div(undefined).isDefined()).isFalse();
        assertThat(one.prod(undefined).isDefined()).isFalse();
        assertThat(one.lT(undefined).isDefined()).isFalse();
        assertThat(one.gT(undefined).isDefined()).isFalse();
        assertThat(one.gTEq(undefined).isDefined()).isFalse();
        assertThat(one.lTEq(undefined).isDefined()).isFalse();
        assertThat(one.eq(undefined).getValue()).isFalse();
        assertThat(one.nEq(undefined).getValue()).isTrue();

        assertThat(zeroPointFive.plus(undefined).isDefined()).isFalse();
        assertThat(zeroPointFive.min(undefined).isDefined()).isFalse();
        assertThat(zeroPointFive.div(undefined).isDefined()).isFalse();
        assertThat(zeroPointFive.prod(undefined).isDefined()).isFalse();
        assertThat(zeroPointFive.lT(undefined).isDefined()).isFalse();
        assertThat(zeroPointFive.gT(undefined).isDefined()).isFalse();
        assertThat(zeroPointFive.gTEq(undefined).isDefined()).isFalse();
        assertThat(zeroPointFive.lTEq(undefined).isDefined()).isFalse();
        assertThat(zeroPointFive.eq(undefined).getValue()).isFalse();
        assertThat(zeroPointFive.nEq(undefined).getValue()).isTrue();

        assertThat(oneFifty.plus(undefined).isDefined()).isFalse();
        assertThat(oneFifty.min(undefined).isDefined()).isFalse();
        assertThat(oneFifty.div(undefined).isDefined()).isFalse();
        assertThat(oneFifty.prod(undefined).isDefined()).isFalse();
        assertThat(oneFifty.lT(undefined).isDefined()).isFalse();
        assertThat(oneFifty.gT(undefined).isDefined()).isFalse();
        assertThat(oneFifty.gTEq(undefined).isDefined()).isFalse();
        assertThat(oneFifty.lTEq(undefined).isDefined()).isFalse();
        assertThat(oneFifty.eq(undefined).getValue()).isFalse();
        assertThat(oneFifty.nEq(undefined).getValue()).isTrue();

        assertThat(onePointTwo.plus(undefined).isDefined()).isFalse();
        assertThat(onePointTwo.min(undefined).isDefined()).isFalse();
        assertThat(onePointTwo.div(undefined).isDefined()).isFalse();
        assertThat(onePointTwo.prod(undefined).isDefined()).isFalse();
        assertThat(onePointTwo.lT(undefined).isDefined()).isFalse();
        assertThat(onePointTwo.gT(undefined).isDefined()).isFalse();
        assertThat(onePointTwo.gTEq(undefined).isDefined()).isFalse();
        assertThat(onePointTwo.lTEq(undefined).isDefined()).isFalse();
        assertThat(onePointTwo.eq(undefined).getValue()).isFalse();
        assertThat(onePointTwo.nEq(undefined).getValue()).isTrue();

        assertThat(date.lT(undefined).isDefined()).isFalse();
        assertThat(date.gT(undefined).isDefined()).isFalse();
        assertThat(date.gTEq(undefined).isDefined()).isFalse();
        assertThat(date.lTEq(undefined).isDefined()).isFalse();
        assertThat(date.eq(undefined).getValue()).isFalse();
        assertThat(date.nEq(undefined).getValue()).isTrue();

        assertThat(string.lT(undefined).isDefined()).isFalse();
        assertThat(string.gT(undefined).isDefined()).isFalse();
        assertThat(string.gTEq(undefined).isDefined()).isFalse();
        assertThat(string.lTEq(undefined).isDefined()).isFalse();
        assertThat(string.eq(undefined).getValue()).isFalse();
        assertThat(string.nEq(undefined).getValue()).isTrue();

        assertThat(boolTrue.and(undefined).isDefined()).isFalse();
        assertThat(boolTrue.or(undefined).isDefined()).isFalse();
        assertThat(boolTrue.eq(undefined).getValue()).isFalse();
        assertThat(boolTrue.nEq(undefined).getValue()).isTrue();

    }

    @Test
    public void testNeg() {
        assertThat(one.neg().getValue()).isEqualTo(-1);
        assertThat(undefined.neg().isDefined()).isFalse();
    }
}
