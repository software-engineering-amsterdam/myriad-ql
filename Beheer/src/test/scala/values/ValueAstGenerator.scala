package values

import ast._
import org.scalacheck.Gen

trait ValueAstGenerator {
  protected val numericIdentifiers: Seq[String]
  protected val boolIdentifiers: Seq[String]

  def numericIdentifier: Gen[Identifier] = for {
    id <- Gen.oneOf(numericIdentifiers)
  } yield Identifier(id)

  def boolIdentifier: Gen[Identifier] = for {
    id <- Gen.oneOf(boolIdentifiers)
  } yield Identifier(id)

  def integerLiteral: Gen[IntegerLiteral] = for {
    number <- Gen.choose[Int](0, 99999)
  } yield IntegerLiteral(BigDecimal(number).setScale(0))

  def decimalLiteral: Gen[DecimalLiteral] = for {
    number <- decimal
  } yield DecimalLiteral(number)

  def moneyLiteral: Gen[MoneyLiteral] = for {
    number <- decimal
  } yield MoneyLiteral(number)

  private def decimal: Gen[BigDecimal] = for {
    number <- Gen.choose[Double](0.0, 99999.0)
  } yield BigDecimal(number)

  def booleanLiteral: Gen[BooleanLiteral] = for {
    bool <- Gen.oneOf(false, true)
  } yield BooleanLiteral(bool)

}
