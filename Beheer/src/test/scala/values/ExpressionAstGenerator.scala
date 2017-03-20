package values

import ast._
import org.scalacheck.Gen

trait ExpressionAstGenerator extends ValueAstGenerator {
  private type InfixNodeGenerator = (ExpressionNode, ExpressionNode) => Gen[ExpressionNode]
  private type PrefixNodeGenerator = ExpressionNode => Gen[ExpressionNode]
  private type SizedChildGenerator = Int => Gen[ExpressionNode]

  def genNumeric: Gen[ExpressionNode] = Gen.sized(genSizedNumeric)

  def genBoolean: Gen[ExpressionNode] = Gen.sized(genSizedBoolean)

  private def genSizedBoolean(size: Int): Gen[ExpressionNode] = size match {
    case 0 => genBooleanLiteral
    case s => Gen.oneOf(genSizedBooleanInfix(s), genSizedBooleanPrefix(s), genSizedRelationalComparison(s), genSizedEquality(s))
  }

  private def genSizedNumeric(size: Int): Gen[ExpressionNode] = size match {
    case 0 => genNumericLiteral
    case s => Gen.oneOf(genSizedNumericInfix(s), genSizedNumericPrefix(s))
  }

  // 6/7 literals, 1/7 identifiers, this assures enough cases are generated for the 'contains at least 1 identifier' case
  // and also enough for the 'contains no identifiers' case.
  private def genBooleanLiteral: Gen[ExpressionNode] =
    Gen.frequency((1, boolIdentifier), (6, booleanLiteral))

  private def genNumericLiteral: Gen[ExpressionNode] =
    Gen.frequency((2, integerLiteral), (2, decimalLiteral), (2, moneyLiteral), (1, numericIdentifier))

  private def genSizedBooleanInfix(size: Int): Gen[ExpressionNode] =
    genSizedInfix(size, genSizedBoolean, (lhs, rhs) => Gen.oneOf(And(lhs, rhs), Or(lhs, rhs)))

  private def genSizedBooleanPrefix(size: Int): Gen[ExpressionNode] =
    genSizedPrefix(size, genSizedBoolean, op => Not(op))

  private def genSizedEquality(size: Int): Gen[ExpressionNode] =
    genSizedInfix(size, genSizedNumeric, (lhs, rhs) => Gen.oneOf(Eq(lhs, rhs), Neq(lhs, rhs)))

  private def genSizedRelationalComparison(size: Int): Gen[ExpressionNode] =
    genSizedInfix(size, genSizedNumeric, (lhs, rhs) => Gen.oneOf(Gt(lhs, rhs), Lt(lhs, rhs), Geq(lhs, rhs), Leq(lhs, rhs)))

  private def genSizedNumericPrefix(size: Int): Gen[ExpressionNode] =
    genSizedPrefix(size, genSizedNumeric, op => Neg(op))

  private def genSizedNumericInfix(size: Int): Gen[ExpressionNode] =
    genSizedInfix(size, genSizedNumeric, (lhs, rhs) => Gen.oneOf(Add(lhs, rhs), Sub(lhs, rhs), Mul(rhs, lhs), Div(rhs, lhs)))

  private def genSizedPrefix(size: Int, childGenerator: SizedChildGenerator, nodeGenerator: PrefixNodeGenerator): Gen[ExpressionNode] = for {
    newSize <- reduceSize(size)
    operand <- childGenerator(newSize)
    expr <- nodeGenerator(operand)
  } yield expr

  private def genSizedInfix(size: Int, childGenerator: SizedChildGenerator, nodeGenerator: InfixNodeGenerator): Gen[ExpressionNode] = for {
    newSize <- reduceSize(size)
    lhs <- childGenerator(newSize)
    rhs <- childGenerator(newSize)
    expr <- nodeGenerator(lhs, rhs)
  } yield expr

  private def reduceSize(oldSize: Int): Gen[Int] = Gen.choose(0, math.max(oldSize - 1, 0))
}
