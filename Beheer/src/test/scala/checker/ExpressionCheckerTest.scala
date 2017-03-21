package checker

import java.util.GregorianCalendar

import ast._
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{ Matchers, PropSpec }

class ExpressionCheckerTest extends PropSpec with Matchers with TableDrivenPropertyChecks {
  private val intLit = IntegerLiteral(BigDecimal(1))
  private val decLit = DecimalLiteral(BigDecimal(1.11))
  private val moneyLit = MoneyLiteral(BigDecimal(1.1))
  private val booleanLit = BooleanLiteral(true)
  private val stringLit = StringLiteral("foo")
  private val dateLit = DateLiteral(new GregorianCalendar(2017, 4, 24).getTime) //java dates... this seems to be the shortest non-deprecated way to get a specific date.

  private val typeEnv: Seq[(String, Type)] = Seq(
    ("intRef", IntegerType),
    ("decRef", DecimalType),
    ("moneyRef", MoneyType),
    ("boolRef", BooleanType),
    ("strRef", StringType)
  )

  private val validExpressions = Table[ExpressionNode, Type](
    ("expr", "expectedType"),
    //Basic Numeric & numeric 'to most generic type'.
    (Add(intLit, intLit), IntegerType),
    (Add(intLit, intLit), DecimalType),
    (Add(intLit, intLit), MoneyType),
    (Sub(intLit, intLit), IntegerType),
    (Neg(decLit), DecimalType),
    (Mul(Add(intLit, decLit), moneyLit), MoneyType),
    (Div(intLit, Identifier("decRef")), DecimalType),

    //Numeric -> Boolean:
    (Lt(intLit, Add(intLit, decLit)), BooleanType),
    (Gt(intLit, Add(intLit, decLit)), BooleanType),
    (Leq(Mul(decLit, intLit), Add(intLit, Identifier("moneyRef"))), BooleanType),
    (Geq(Mul(decLit, intLit), Add(intLit, Identifier("moneyRef"))), BooleanType),

    //Boolean -> Boolean
    (And(booleanLit, booleanLit), BooleanType),
    (Not(booleanLit), BooleanType),
    (Not(And(Or(booleanLit, Lt(intLit, decLit)), booleanLit)), BooleanType),

    //Any -> Boolean (i.e.:Eq)
    (Eq(dateLit, dateLit), BooleanType),
    (Neq(stringLit, stringLit), BooleanType),
    (Eq(intLit, decLit), BooleanType),
    (Neq(intLit, decLit), BooleanType)
  )

  private val invalidExpressions = Table[ExpressionNode, Type](
    ("expr", "expectedType"),
    //Mismatch with expected type:
    (intLit, BooleanType),
    (Eq(intLit, moneyLit), DecimalType),

    //Internal hierarchy error (e.g.: adding booleans)
    (Add(booleanLit, stringLit), MoneyType),
    (Eq(booleanLit, stringLit), BooleanType),
    (Eq(booleanLit, Eq(booleanLit, stringLit)), BooleanType),
    (Neg(stringLit), StringType),
    (Neg(Neg(stringLit)), StringType),

    //Undefined references:
    (Eq(Identifier("undefined"), intLit), BooleanType)
  )

  property("Valid expressions should never have errors") {
    forAll(validExpressions) {
      (expr, expectedType) => ExpressionChecker(typeEnv, expr, expectedType) should be(Nil)
    }
  }

  property("Invalid expressions should have at least 1 error") {
    forAll(invalidExpressions) {
      (expr, expectedType) => ExpressionChecker(typeEnv, expr, expectedType) should not be Nil
    }
  }
}
