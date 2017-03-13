package checker

import ast._
import checker.Issue.Issues

class ExpressionChecker(identifiersWithType: Seq[(String, Type)], expression: ExpressionNode, expectedType: Type) {

  def check: Issues = checkExpression(expression) match {
    case (Some(expressionType), Nil) => (expressionType, expectedType) match {
      case (detected: NumericType, expected: NumericType) if isSubType(detected, expected) => Nil
      case (detected, expected) if detected == expected => Nil
      case (detected, expected) => Seq(Error(s"Found $detected, expected $expected in $expression"))
    }
    case (_, errors) => errors
  }

  private def checkExpression(expressionNode: ExpressionNode): (Option[Type], Issues) = expressionNode match {
    case _: DecimalLiteral => (Some(DecimalType), Nil)
    case _: IntegerLiteral => (Some(IntegerType), Nil)
    case _: BooleanLiteral => (Some(BooleanType), Nil)
    case _: DateLiteral => (Some(DateType), Nil)
    case _: MoneyLiteral => (Some(MoneyType), Nil)
    case _: StringLiteral => (Some(StringType), Nil)

    case identifier: Identifier => identifierType(identifier)
    case prefixNode: PrefixNode => checkPrefixExpression(prefixNode)
    case infixNode: InfixNode => checkInfixExpression(infixNode)
  }

  private def checkInfixExpression(infixNode: InfixNode): (Option[Type], Issues) = {
    val (left, errorsLeft) = checkExpression(infixNode.lhs)
    val (right, errorsRight) = checkExpression(infixNode.rhs)
    val errors = errorsLeft ++ errorsRight

    (left, right) match {
      case (_, None) => (None, errors)
      case (None, _) => (None, errors)
      case (Some(leftType), Some(rightType)) => (infixNode, leftType, rightType) match {
        case (_: Add, l: NumericType, r: NumericType) => (Some(mostGeneric(l, r)), errors)
        case (_: Sub, l: NumericType, r: NumericType) => (Some(mostGeneric(l, r)), errors)
        case (_: Mul, l: NumericType, r: NumericType) => (Some(mostGeneric(l, r)), errors)
        case (_: Div, l: NumericType, r: NumericType) => (Some(mostGeneric(l, r)), errors)
        case (_: Geq, _: NumericType, _: NumericType) => (Some(BooleanType), errors)
        case (_: Leq, _: NumericType, _: NumericType) => (Some(BooleanType), errors)
        case (_: Gt, _: NumericType, _: NumericType) => (Some(BooleanType), errors)
        case (_: Lt, _: NumericType, _: NumericType) => (Some(BooleanType), errors)
        case (_: And, BooleanType, BooleanType) => (Some(BooleanType), errors)
        case (_: Or, BooleanType, BooleanType) => (Some(BooleanType), errors)
        case (n: Neq, t1: Type, t2: Type) => checkEqualityOperator(n, t1, t2, errors)
        case (n: Eq, t1: Type, t2: Type) => checkEqualityOperator(n, t1, t2, errors)
        case (n: InfixNode, l: Type, r: Type) => emitError(n, l, r, errors)
      }
    }
  }

  private def checkPrefixExpression(prefixNode: PrefixNode): (Option[Type], Issues) = {
    val (operandType, errors) = checkExpression(prefixNode.operand)
    operandType match {
      case None => (None, errors)
      case Some(opType) => (prefixNode, opType) match {
        case (_: Neg, t: NumericType) => (Some(t), errors)
        case (_: Not, BooleanType) => (Some(BooleanType), errors)
        case (n: PrefixNode, t: Type) => emitError(n, t, errors)
      }
    }
  }

  // Equality: Among different numbers, ok, otherwise: strict type match.
  private def checkEqualityOperator(node: ExpressionNode, lhs: Type, rhs: Type, errors: Issues): (Option[Type], Issues) =
    (lhs, rhs) match {
      case (_: NumericType, _: NumericType) => (Some(BooleanType), errors)
      case (t1: Type, t2: Type) if t1 == t2 => (Some(BooleanType), errors)
      case (l, r) => emitError(node, l, r, errors)
    }

  private def identifierType(identifier: Identifier): (Option[Type], Issues) =
    identifiersWithType.filter { case (i, _) => identifier.value == i } match {
      case (_, identifierType) :: Nil => (Some(identifierType), Nil)
      case _ => (None, Seq(Error(s"Duplicate identifier found in expression check: ${identifier.value}")))
    }

  private def mostGeneric(left: NumericType, right: NumericType): NumericType =
    (left, right) match {
      case (MoneyType, _) => MoneyType
      case (_, MoneyType) => MoneyType
      case (DecimalType, _) => DecimalType
      case (_, DecimalType) => DecimalType
      case (IntegerType, IntegerType) => IntegerType
    }

  private def isSubType(candidateChild: NumericType, candidateParent: NumericType): Boolean =
    mostGeneric(candidateChild, candidateParent) == candidateParent

  private def emitError(expressionNode: ExpressionNode, left: Type, right: Type, errors: Issues) =
    (None, Error(s"Types $left, $right not supported by operation ${expressionNode.getClass.getName}") +: errors)

  private def emitError(expressionNode: ExpressionNode, operand: Type, errors: Issues) =
    (None, Error(s"Type $operand not supported by operation ${expressionNode.getClass.getName}") +: errors)

}

object ExpressionChecker {
  def apply(identifiersWithType: Seq[(String, Type)], expressionNode: ExpressionNode, expectedType: Type): Seq[Issue] =
    new ExpressionChecker(identifiersWithType, expressionNode, expectedType).check
}