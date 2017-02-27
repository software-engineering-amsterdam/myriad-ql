package parser.generators

import org.scalacheck.Gen

/**
 * Created by jasper on 13/02/17.
 */
object ExpressionGenerator extends ValueGenerator {
  def genExpression: Gen[String] = Gen.sized(size => sizedExpression(size))

  private def sizedExpression(maxDepth: Int): Gen[String] = maxDepth match {
    case 0 => literalValue
    case x => for {
      newDepth <- Gen.choose(0, Math.max(x - 1, 0))
      expression <- Gen.oneOf(literalValue, prefixExpression(newDepth), infixExpression(newDepth))
    } yield expression
  }

  private def prefixExpression(maxDepth: Int): Gen[String] = for {
    newDepth <- Gen.choose(0, Math.max(maxDepth - 1, 0))
    operator <- Gen.oneOf("-", "!")
    expression <- sizedExpression(newDepth)
  } yield operator + "(" + expression + ")"

  private def infixExpression(maxDepth: Int): Gen[String] = {
    val operators = Seq(" + ", " - ", " / ", " * ", " || ", " && ", " == ", " != ", " >= ", " > ", " < ", " <= ")
    def infixExpressionTail(length: Int, depth: Int): Gen[String] = length match {
      case 0 => ""
      case l => for {
        expression <- sizedExpression(depth)
        operator <- Gen.oneOf(operators)
        tail <- infixExpressionTail(l - 1, depth)
      } yield operator + expression + tail
    }

    for {
      newDepth <- Gen.choose(0, Math.max(maxDepth - 1, 0))
      numOperators <- Gen.choose(1, 10)
      expression <- sizedExpression(newDepth)
      tail <- infixExpressionTail(numOperators, newDepth)
    } yield expression + tail
  }

  private def literalValue: Gen[String] = Gen.oneOf(identifier, integer, decimal, money, date, stringLiteral, boolean)
}

