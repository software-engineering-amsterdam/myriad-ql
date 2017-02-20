package parser

import parser.ast._

class ExpressionChecker(db: AstFacts, expression: ExpressionNode, expectedType: Type) extends Checker {

  def check: Issues = checkExpression(expression) match {
    case Left(error) => Seq(error)
    case Right(expressionType) => (expressionType, expectedType) match {
      case (detected: NumericType, expected: NumericType) =>
        if (!isSubType(detected, expected)) {
          Seq(Error(s"Found $expressionType, expected $expectedType"))
        } else {
          Nil
        }
      case (detected, expected) =>
        if (detected != expected) {
          Seq(Error(s"Found $expressionType, expected $expectedType"))
        } else {
          Nil
        }
    }
  }

  def checkExpression(expressionNode: ExpressionNode): Either[Error, Type] = expressionNode match {
    case identifier: Identifier => identifierType(identifier)
    case _: DecimalLiteral => Right(Decimal)
    case _: IntegerLiteral => Right(Integer)
    case _: BooleanLiteral => Right(Boolean)
    case _: DateLiteral => Right(Date)
    case _: MoneyLiteral => Right(Money)
    case _: StringLiteral => Right(String)

    case prefixOperation: PrefixOperation => checkPrefixExpression(prefixOperation)
    case infixOperation: InfixOperation => checkInfixExpression(infixOperation)
  }

  private def checkInfixExpression(operation: InfixOperation): Either[Error, Type] =
    (checkExpression(operation.lhs), operation.operator, checkExpression(operation.rhs)) match {
      case (Left(error), _, _) => Left(error)
      case (_, _, Left(error)) => Left(error)
      case (Right(Boolean), "&&", Right(Boolean)) => Right(Boolean)
      case (Right(Boolean), "||", Right(Boolean)) => Right(Boolean)
      case (Right(_: NumericType), ">=", Right(_: NumericType)) => Right(Boolean)
      case (Right(_: NumericType), "<=", Right(_: NumericType)) => Right(Boolean)
      case (Right(_: NumericType), "!=", Right(_: NumericType)) => Right(Boolean)
      case (Right(_: NumericType), "==", Right(_: NumericType)) => Right(Boolean)
      case (Right(_: NumericType), ">", Right(_: NumericType)) => Right(Boolean)
      case (Right(_: NumericType), "<", Right(_: NumericType)) => Right(Boolean)
      case (Right(lhs: NumericType), "+", Right(rhs: NumericType)) => Right(mostGeneric(lhs, rhs))
      case (Right(lhs: NumericType), "-", Right(rhs: NumericType)) => Right(mostGeneric(lhs, rhs))
      case (Right(lhs), op, Right(rhs)) => Left(Error(s"Wrong operands $lhs, $rhs for operator $op"))
    }

  private def checkPrefixExpression(operation: PrefixOperation): Either[Error, Type] =
    (operation.operator, checkExpression(operation.rhs)) match {
      case (_, Left(error)) => Left(error)
      case ("!", Right(Boolean)) => Right(Boolean)
      case ("+", Right(n: NumericType)) => Right(n)
      case ("-", Right(n: NumericType)) => Right(n)
      case (op, Right(otherType)) => Left(Error(s"Wrong operand type $otherType for operator $op"))
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

  private def identifierType(identifier: Identifier): Either[Error, Type] = {
    db.identifiersWithType.filter { case (i, _) => identifier.value == i } match {
      case (_, identifierType) :: Nil => Right(identifierType)
      case _ => Left(Error(s"Cannot determine type of identifier ${identifier.value}"))
    }
  }
}

object ExpressionChecker {
  def apply(db: AstFacts, expressionNode: ExpressionNode, expectedType: Type): Seq[Issue] =
    new ExpressionChecker(db, expressionNode, expectedType).check
}