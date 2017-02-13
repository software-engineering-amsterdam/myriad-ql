package parser

import parser.ast._

import scala.util.matching.Regex
import scala.util.parsing.combinator.JavaTokenParsers

trait ExpressionParser extends JavaTokenParsers {
  def expr: Parser[ExpressionNode] = infixOperationParser(comp, """\&\&|\|\|""".r)

  def comp: Parser[ExpressionNode] = infixOperationParser(subAdd, """>|<|>=|<=|!=|==""".r)

  def subAdd: Parser[ExpressionNode] = infixOperationParser(mulDiv, """-|\+""".r)

  def mulDiv: Parser[ExpressionNode] = infixOperationParser(factor, """\*|/""".r)

  def factor: Parser[ExpressionNode] = (
    prefix
    | integer
    | identifier
    | "(" ~> expr <~ ")"
  )

  def prefix: Parser[PrefixOperation] =
    """\+|-|!""".r ~ factor ^^ {
      case op ~ value => PrefixOperation(op, value)
    }

  //Ident taken from JavaTokenParsers, equals Java Identifier.
  def identifier: Parser[Identifier] = ident ^^ (s => Identifier(s))

  def integer: Parser[Value] = """\d+""".r ^^ (x => Value(x.toInt))

  private def infixOperationParser(child: Parser[ExpressionNode], ops: Regex) = {
    child ~ rep(ops ~ child) ^^ {
      case head ~ tail => tail.foldLeft(head) {
        case (lhs, operation ~ rhs) => InfixOperation(lhs, operation, rhs)
      }
    }
  }
}
