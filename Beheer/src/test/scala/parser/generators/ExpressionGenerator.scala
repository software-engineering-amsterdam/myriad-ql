package parser.generators

import org.scalacheck.Gen

/**
  * Created by jasper on 13/02/17.
  */
object ExpressionGenerator {
  def genExpression: Gen[String] = Gen.sized(size => sizedExpression(size))

  private def unaryExpression(maxDepth: Int): Gen[String] = for {
    newDepth <- Gen.choose(0, Math.max(maxDepth - 1, 0))
    operator <- prefixOperator
    expr <- sizedExpression(newDepth)
  } yield operator + "(" + expr + ")"

  private def sizedExpression(maxDepth: Int): Gen[String] = maxDepth match {
    case 0 => identOrNumber
    case x => for {
      newDepth <- Gen.choose(0, Math.max(x - 1, 0))
      expression <- Gen.oneOf(identOrNumber, unaryExpression(newDepth), multiExpression(newDepth))
    } yield expression
  }

  private def multiExpression(maxDepth: Int): Gen[String] = maxDepth match {
    case 0 => identOrNumber
    case x => for {
      newDepth <- Gen.choose(0, Math.max(x - 1, 0))
      numOperators <- Gen.choose(1, 10)
      expression <- sizedExpression(newDepth)
      tail <- multiExpressionTail(numOperators, newDepth)
    } yield "("+expression + tail+")"
  }

  private def multiExpressionTail(length: Int, depth: Int): Gen[String] = length match {
    case 0 => for {
      expression <- sizedExpression(depth)
      operator <- infixOperator
    } yield operator + expression
    case l => for {
      expression <- sizedExpression(depth)
      operator <- infixOperator
      tail <- multiExpressionTail(l - 1, depth)
    } yield operator + expression + tail
  }

  private def prefixOperator: Gen[String] = Gen.oneOf("+", "-", "!")

  private def infixOperator: Gen[String] = Gen.oneOf("+", "-", "/", "*", "||", "&&")

  private def identOrNumber: Gen[String] = Gen.oneOf(identifier, number)

  private def identifier: Gen[String] = for {
    size <- Gen.choose(1, 10)
    value <- Gen.resize(size, Gen.alphaStr)
  } yield value

  private def number: Gen[String] = Gen.choose(0, 10000).map(_.toString)
}

