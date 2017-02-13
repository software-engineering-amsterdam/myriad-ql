package parser.ast

sealed trait FormNode

sealed trait Statement extends FormNode

case class Form(identifier: String, block: Block) extends Statement

case class Block(statements: List[Statement]) extends Statement

case class Conditional(condition: ExpressionNode, block: Block) extends Statement

case class Question(identifier: String, label: String, typeDeclaration: TypeDeclaration) extends Statement

sealed trait TypeDeclaration extends FormNode

case class BareType(typeName: String) extends TypeDeclaration

case class ValueType(typeName: String, value: ExpressionNode) extends TypeDeclaration
