package values

import ast._
import values.Evaluator.Env

class Evaluator(env: Env) {
  def calculate(expressionNode: ExpressionNode): Value =
    expressionNode match {
      case Identifier(value) => env.getOrElse(value, UndefinedValue)
      case IntegerLiteral(value) => IntegerValue(value)
      case DecimalLiteral(value) => DecimalValue(value)
      case MoneyLiteral(value) => MoneyValue(value)
      case BooleanLiteral(value) => BooleanValue(value)
      case StringLiteral(value) => StringValue(value)
      case DateLiteral(value) => DateValue(value)
      case i: InfixNode => calculate(i)
      case p: PrefixNode => calculate(p)
    }

  private def calculate(prefixNode: PrefixNode): Value =
    calculate(prefixNode.operand) match {
      case UndefinedValue => UndefinedValue
      case operator => (prefixNode, operator) match {
        case (_: Neg, IntegerValue(i)) => IntegerValue(-i)
        case (_: Neg, DecimalValue(d)) => DecimalValue(-d)
        case (_: Neg, MoneyValue(m)) => MoneyValue(-m)
        case (_: Not, BooleanValue(b)) => BooleanValue(!b)
        case (op, input) => sys.error(s"Attempt to evaluate operator ${op.getClass.getName} with invalid operand $input")
      }
    }

  private def calculate(infixNode: InfixNode): Value =
    (calculate(infixNode.lhs), calculate(infixNode.rhs)) match {
      case (UndefinedValue, _) => UndefinedValue
      case (_, UndefinedValue) => UndefinedValue
      case (leftResult, rightResult) => (infixNode, leftResult, rightResult) match {
        case (_: Mul, lhs: NumericValue, rhs: NumericValue) => mostGeneric(lhs, rhs, lhs.value * rhs.value)
        case (_: Div, lhs: NumericValue, rhs: NumericValue) => mostGeneric(lhs, rhs, lhs.value / rhs.value)
        case (_: Add, lhs: NumericValue, rhs: NumericValue) => mostGeneric(lhs, rhs, lhs.value + rhs.value)
        case (_: Sub, lhs: NumericValue, rhs: NumericValue) => mostGeneric(lhs, rhs, lhs.value - rhs.value)
        case (_: Gt, lhs: NumericValue, rhs: NumericValue) => BooleanValue(lhs.value > rhs.value)
        case (_: Lt, lhs: NumericValue, rhs: NumericValue) => BooleanValue(lhs.value < rhs.value)
        case (_: Geq, lhs: NumericValue, rhs: NumericValue) => BooleanValue(lhs.value >= rhs.value)
        case (_: Leq, lhs: NumericValue, rhs: NumericValue) => BooleanValue(lhs.value <= rhs.value)
        case (_: And, BooleanValue(lhs), BooleanValue(rhs)) => BooleanValue(lhs && rhs)
        case (_: Or, BooleanValue(lhs), BooleanValue(rhs)) => BooleanValue(lhs || rhs)
        case (_: Eq, lhs: NumericValue, rhs: NumericValue) => BooleanValue(lhs.value == rhs.value)
        case (_: Neq, lhs: NumericValue, rhs: NumericValue) => BooleanValue(lhs.value != rhs.value)
        case (_: Eq, lhs, rhs) => BooleanValue(lhs == rhs)
        case (_: Neq, lhs, rhs) => BooleanValue(lhs != rhs)
        case (op, lhs, rhs) => sys.error(s"Attempt to evaluate operator ${op.getClass.getName} with invalid operands $lhs, $rhs")
      }
    }

  private def mostGeneric(lhs: NumericValue, rhs: NumericValue, value: BigDecimal): NumericValue =
    (lhs, rhs) match {
      case (_: MoneyValue, _) => MoneyValue(value)
      case (_, _: MoneyValue) => MoneyValue(value)
      case (_: DecimalValue, _) => DecimalValue(value)
      case (_, _: DecimalValue) => DecimalValue(value)
      case (_: IntegerValue, _: IntegerValue) => IntegerValue(value)
    }
}

object Evaluator {
  type Env = Map[String, Value]

  def apply(env: Env, expressionNode: ExpressionNode): Value = new Evaluator(env).calculate(expressionNode)
}