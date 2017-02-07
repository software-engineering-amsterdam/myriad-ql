package parser

import parser.ast._

import scala.util.matching.Regex
import scala.util.parsing.combinator.JavaTokenParsers

/**
  * Created by jasper on 07/02/17.
  */
trait ExpressionParser extends JavaTokenParsers {
  def expr: Parser[ExpressionNode] = buildParser(comp, """\&\&|\|\|""".r)

  def comp: Parser[ExpressionNode] = buildParser(subAdd, """>|<|>=|<=|!=|==""".r)

  def subAdd: Parser[ExpressionNode] = buildParser(mulDiv,"""-|\+""".r)

  def mulDiv: Parser[ExpressionNode] = buildParser(factor, """\*|/""".r)

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

  private def buildParser(child: Parser[ExpressionNode], ops: Regex): Parser[ExpressionNode] = {
    child ~ rep(ops ~ child) ^^ {
      case head ~ tail => tail.foldLeft(head) {
        case (lhs, operation ~ rhs) => InfixOperation(lhs, operation, rhs)
      }
    }
  }
}
