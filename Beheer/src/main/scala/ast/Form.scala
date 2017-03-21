package ast

import ast.Form.Statements

case class Form(identifier: String, statements: Statements)

sealed trait Statement

case class Conditional(condition: ExpressionNode, ifStatements: Statements, elseStatements: Statements) extends Statement

case class Question(identifier: String, label: String, `type`: Type, expressionNode: Option[ExpressionNode]) extends Statement

sealed trait Type

case object BooleanType extends Type

case object StringType extends Type

case object DateType extends Type

sealed trait NumericType extends Type

case object IntegerType extends NumericType

case object DecimalType extends NumericType

case object MoneyType extends NumericType

object Form {
  type Statements = Seq[Statement]
}