package parser.ast

/**
  * Created by jasper on 07/02/17.
  */
sealed trait FormNode

case class Form(identifier: String, block: Block) extends FormNode

case class Block(statements: List[Statement]) extends FormNode


sealed trait Statement extends FormNode

case class Conditional(condition: ExpressionNode, block: Block) extends Statement

case class Question(identifier: String, label: String, typedecl: TypeDeclaration) extends Statement


sealed trait TypeDeclaration extends FormNode

case class BareType(typeName: String) extends TypeDeclaration

case class ValueType(typeName: String, value: ExpressionNode) extends TypeDeclaration