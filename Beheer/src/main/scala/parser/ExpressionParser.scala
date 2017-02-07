package parser

import parser.ast._

import scala.util.matching.Regex
import scala.util.parsing.combinator.JavaTokenParsers

/**
  * Created by jasper on 07/02/17.
  */
trait ExpressionParser extends JavaTokenParsers {
  //Infix operators:
  def expr: Parser[ExpressionNode] = buildParser(comp, """\&\&|\|\|""".r)

  def comp: Parser[ExpressionNode] = buildParser(addSub, """>|<|>=|<=|!=|==""".r)

  def addSub: Parser[ExpressionNode] = buildParser(term,"""\+|-""".r)

  def term: Parser[ExpressionNode] = buildParser(factor, """\*|/""".r)

  def factor: Parser[ExpressionNode] = prefixOp | integer | identifier | "(" ~> expr <~ ")"

  def prefixOp: Parser[PrefixOperation] =
    """\+|-|!""".r ~ factor ^^ {
      case op ~ value => PrefixOperation(op, value)
    }

  //Ident taken from JavaTokenParsers, equals Java Identifier.
  def identifier: Parser[Identifier] = ident ^^ (s => Identifier(s))

  def integer: Parser[Value] = """\d+""".r ^^ (x => Value(x.toInt))

  private def buildParser(childParser: Parser[ExpressionNode], operationRegex: Regex): Parser[ExpressionNode] = {
    childParser ~ rep(operationRegex ~ childParser) ^^ {
      case head ~ tail => tail.foldLeft(head) {
        case (lhs, operation ~ rhs) => InfixOperation(lhs, operation, rhs)
      }
    }
  }
}
