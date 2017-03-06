package parser

import org.scalacheck.Gen

trait ValueGenerator {
  def identifier: Gen[String] = Gen.alphaStr suchThat (!_.isEmpty)

  def money: Gen[String] = for {
    value <- decimal
  } yield s"€$value"

  def decimal: Gen[String] = for {
    intPart <- integer
    fractionPart <- integer
  } yield s"$intPart.$fractionPart"

  def integer: Gen[String] = Gen.numStr suchThat (!_.isEmpty)

  def date: Gen[String] = for {
    year <- Gen.choose(0, 3000)
    month <- Gen.choose(1, 12)
    day <- Gen.choose(1, 28)
  } yield f"D$year%04d-$month%02d-$day%02d"

  def stringLiteral: Gen[String] = for {
    value <- Gen.alphaNumStr
  } yield s""" "$value" """

  def boolean: Gen[String] = Gen.oneOf("True", "False")
}
