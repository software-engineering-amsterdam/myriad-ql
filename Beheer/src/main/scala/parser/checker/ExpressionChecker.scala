package parser.checker

import model.FormModel
import parser.ast._

class ExpressionChecker(db: FormModel, expression: ExpressionNode, expectedType: Type) extends Checker {

  def check: Issues = checkExpression(expression) match {
    case (Some(expressionType), Nil) => (expressionType, expectedType) match {
      case (detected: NumericType, expected: NumericType) if isSubType(detected, expected) => Nil
      case (detected: Type, expected: Type) if detected == expected => Nil
      case (detected, expected) => Seq(Error(s"Found $detected, expected $expected in $expression"))
    }
    case (_, errors) => errors
  }

  def checkExpression(expressionNode: ExpressionNode): (Option[Type], Issues) = expressionNode match {
    case identifier: Identifier => identifierType(identifier)
    case _: DecimalLiteral => (Some(Decimal), Nil)
    case _: IntegerLiteral => (Some(Integer), Nil)
    case _: BooleanLiteral => (Some(Boolean), Nil)
    case _: DateLiteral => (Some(Date), Nil)
    case _: MoneyLiteral => (Some(Money), Nil)
    case _: StringLiteral => (Some(String), Nil)

    case prefixNode: PrefixNode => checkPrefixExpression(prefixNode)
    case infixNode: InfixNode => checkInfixExpression(infixNode)
  }
  private def checkInfixExpression(infixNode: InfixNode): (Option[Type], Issues) = {
    val (left, errorsLeft) = checkExpression(infixNode.lhs)
    val (right, errorsRight) = checkExpression(infixNode.rhs)
    val errors = errorsLeft ++ errorsRight

    (infixNode, left, right) match {
      case (_, None, _) => (None, errors)
      case (_, _, None) => (None, errors)
      case (_: Arithmetic, Some(l: NumericType), Some(r: NumericType)) => (Some(mostGeneric(l, r)), errors)
      case (_: Logic, Some(Boolean), Some(Boolean)) => (Some(Boolean), errors)
      case (_: Comparison, Some(_: NumericType), Some(_: NumericType)) => (Some(Boolean), errors)
      case (_: Comparison, Some(l: Type), Some(r: Type)) if l == r => (Some(Boolean), errors)
      case (_: Relation, Some(_: NumericType), Some(_: NumericType)) => (Some(Boolean), errors)
      case (o: Arithmetic, Some(l: Type), Some(r: Type)) => (None, errors :+ Error(s"Invalid types for operator $o: $l, $r"))
      case (o: Logic, Some(l: Type), Some(r: Type)) => (Some(Boolean), errors :+ Error(s"Invalid types for operator $o: $l, $r"))
      case (o: Comparison, Some(l: Type), Some(r: Type)) => (Some(Boolean), errors :+ Error(s"Invalid types for operator $o: $l, $r"))
      case (o: Relation, Some(l: Type), Some(r: Type)) => (Some(Boolean), errors :+ Error(s"Invalid types for operator $o: $l, $r"))
    }
  }

  private def checkPrefixExpression(prefixNode: PrefixNode): (Option[Type], Issues) =
    (prefixNode, checkExpression(prefixNode.rhs)) match {
      case (_, (None, errors)) => (None, errors)
      case (_: NEG, (Some(t: NumericType), errors)) => (Some(t), errors)
      case (_: NOT, (Some(Boolean), errors)) => (Some(Boolean), errors)
      case (_: NEG, (Some(t: Type), errors)) => (None, errors :+ Error(s"Wrong operand type $t for operator NEG"))
      case (_: NOT, (Some(t: Type), errors)) => (Some(Boolean), errors :+ Error(s"Wrong operand type $t for operator NOT"))
    }
  private def isSubType(candidate: NumericType, expectedType: NumericType): Boolean =
    mostGeneric(candidate, expectedType) == expectedType

  private def mostGeneric(left: NumericType, right: NumericType): NumericType =
    (left, right) match {
      case (Money, _) => Money
      case (_, Money) => Money
      case (Decimal, _) => Decimal
      case (_, Decimal) => Decimal
      case _ => Integer
    }

  private def identifierType(identifier: Identifier): (Option[Type], Issues) = {
    db.identifiersWithType.filter { case (i, _) => identifier.value == i } match {
      case (_, identifierType) :: Nil => (Some(identifierType), Nil)
      case _ => (None, Seq(Error(s"Duplicate identifier found in expression check: ${identifier.value}")))
    }
  }
}

object ExpressionChecker {
  def apply(db: FormModel, expressionNode: ExpressionNode, expectedType: Type): Seq[Issue] =
    new ExpressionChecker(db, expressionNode, expectedType).check
}