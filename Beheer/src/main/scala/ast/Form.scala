package ast

case class Form(identifier: String, statements: Seq[Statement])

sealed trait Statement

case class Conditional(condition: ExpressionNode, statements: Seq[Statement]) extends Statement

case class Question(identifier: String, label: String, `type`: Type, expressionNode: Option[ExpressionNode]) extends Statement

sealed trait Type

case object BooleanType extends Type

case object StringType extends Type

case object DateType extends Type

sealed trait NumericType extends Type

case object IntegerType extends NumericType

case object DecimalType extends NumericType

case object MoneyType extends NumericType
