package parser

import org.scalacheck.Gen

/**
 * Created by jasper on 13/02/17.
 */
trait ExpressionStringGenerator extends ValueStringGenerator {
  def genExpression: Gen[String] = Gen.sized(size => sizedExpression(size)) suchThat (_.nonEmpty)

  def genInfixExpression: Gen[String] = Gen.sized(size => sizedInfixExpression(size)) suchThat (_.nonEmpty)

  def literalValue: Gen[String] = Gen.oneOf(identifier, integer, decimal, money, date, stringLiteral, boolean)

  private def sizedInfixExpression(maxDepth: Int): Gen[String] = maxDepth match {
    case 0 => literalValue
    case x => for {
      newDepth <- Gen.choose(0, Math.max(x - 1, 0))
      expression <- Gen.oneOf(literalValue, infixExpression(sizedInfixExpression, newDepth))
    } yield expression
  }

  private def sizedExpression(maxDepth: Int): Gen[String] = maxDepth match {
    case 0 => literalValue
    case x => for {
      newDepth <- Gen.choose(0, Math.max(x - 1, 0))
      expression <- Gen.oneOf(literalValue, prefixExpression(sizedExpression, newDepth), infixExpression(sizedExpression, newDepth))
    } yield expression
  }

  private def prefixExpression(childGenerator: Int => Gen[String], maxDepth: Int): Gen[String] = for {
    newDepth <- Gen.choose(0, Math.max(maxDepth - 1, 0))
    operator <- Gen.oneOf("-", "!")
    expression <- childGenerator(newDepth)
  } yield operator + "(" + expression + ")"

  private def infixExpression(childGenerator: Int => Gen[String], maxDepth: Int): Gen[String] = {
    val operators = Seq(" + ", " - ", " / ", " * ", " || ", " && ", " == ", " != ", " >= ", " > ", " < ", " <= ")

    def infixExpressionTail(length: Int, depth: Int): Gen[String] = length match {
      case 0 => ""
      case l => for {
        expression <- childGenerator(depth)
        operator <- Gen.oneOf(operators)
        tail <- infixExpressionTail(l - 1, depth)
      } yield operator + expression + tail
    }

    for {
      newDepth <- Gen.choose(0, Math.max(maxDepth - 1, 0))
      numOperators <- Gen.choose(1, 10)
      expression <- childGenerator(newDepth)
      tail <- infixExpressionTail(numOperators, newDepth)
    } yield expression + tail
  }
}

