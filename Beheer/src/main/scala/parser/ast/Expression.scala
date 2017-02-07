package parser.ast

/**
  * Created by jasper on 07/02/17.
  */

sealed trait ExpressionNode

class Number(value: Int) extends ExpressionNode

class Identifier(value: String) extends ExpressionNode

class InfixOperation(lhs :ExpressionNode, operator :String, rhs :ExpressionNode) extends ExpressionNode

class PrefixOperation(operator :String, rhs: ExpressionNode) extends ExpressionNode



