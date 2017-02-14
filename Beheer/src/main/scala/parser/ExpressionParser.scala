package parser

import java.text.SimpleDateFormat

import parser.ast._

import scala.util.matching.Regex
import scala.util.parsing.combinator.JavaTokenParsers

trait ExpressionParser extends JavaTokenParsers {
  //We do one date: yyyy-mm-dd.
  private val dateFormat = new SimpleDateFormat("yyyy-mm-dd")

  def expr: Parser[ExpressionNode] = infixOperationParser(comp, """\&\&|\|\|""".r)

  def comp: Parser[ExpressionNode] = infixOperationParser(subAdd, """>|<|>=|<=|!=|==""".r)

  def subAdd: Parser[ExpressionNode] = infixOperationParser(mulDiv, """-|\+""".r)

  def mulDiv: Parser[ExpressionNode] = infixOperationParser(factor, """\*|/""".r)

  def factor: Parser[ExpressionNode] = (
    prefix
    | (bool | integer | decimal | money | date)
    | identifier
    | "(" ~> expr <~ ")"
  )

  def prefix: Parser[PrefixOperation] =
    """\+|-|!""".r ~ factor ^^ {
      case op ~ value => PrefixOperation(op, value)
    }

  //Ident taken from JavaTokenParsers, equals Java Identifier.
  def identifier: Parser[Identifier] = ident ^^ (s => Identifier(s))

  def bool: Parser[BooleanValue] = ("True" | "False") ^^ (x => BooleanValue(x.toBoolean))

  def money: Parser[MoneyValue] = "$" ~> decimal ^^ (x => MoneyValue(x.value))

  def decimal: Parser[DecimalValue] = """\d*.\d+""".r ^^ (x => DecimalValue(BigDecimal(x)))

  def integer: Parser[DecimalValue] = """\d+""".r ^^ (x => DecimalValue(BigDecimal(x).setScale(0)))

  def date: Parser[DateValue] = """\d\d\d\d-\d\d-\d\d""".r ^^ (x => DateValue(dateFormat.parse(x)))

  private def infixOperationParser(child: Parser[ExpressionNode], ops: Regex) = {
    child ~ rep(ops ~ child) ^^ {
      case head ~ tail => tail.foldLeft(head) {
        case (lhs, operation ~ rhs) => InfixOperation(lhs, operation, rhs)
      }
    }
  }
}
