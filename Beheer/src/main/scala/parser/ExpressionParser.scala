package parser

import java.text.SimpleDateFormat

import parser.ast._

import scala.util.matching.Regex
import scala.util.parsing.combinator.JavaTokenParsers

trait ExpressionParser extends JavaTokenParsers {
  type InfixMatcher = (ExpressionNode, ~[String, ExpressionNode]) => ExpressionNode

  //We do one date: yyyy-mm-dd.
  private val dateFormat = new SimpleDateFormat("yyyy-mm-dd")

  def expr: Parser[ExpressionNode] = infixOperationParser(comp, """\&\&|\|\|""".r) {
    case (lhs, "&&" ~ rhs) => AND(lhs, rhs)
    case (lhs, "||" ~ rhs) => OR(lhs, rhs)
  }

  def comp: Parser[ExpressionNode] = infixOperationParser(subAdd, """>|<|>=|<=|!=|==""".r) {
    case (lhs, ">" ~ rhs) => GT(lhs, rhs)
    case (lhs, "<" ~ rhs) => LT(lhs, rhs)
    case (lhs, ">=" ~ rhs) => GEQ(lhs, rhs)
    case (lhs, "<=" ~ rhs) => LEQ(lhs, rhs)
    case (lhs, "!=" ~ rhs) => NEQ(lhs, rhs)
    case (lhs, "==" ~ rhs) => EQ(lhs, rhs)
  }

  def subAdd: Parser[ExpressionNode] = infixOperationParser(mulDiv, """-|\+""".r) {
    case (lhs, "-" ~ rhs) => SUB(lhs, rhs)
    case (lhs, "+" ~ rhs) => ADD(lhs, rhs)
  }

  def mulDiv: Parser[ExpressionNode] = infixOperationParser(factor, """\*|/""".r) {
    case (lhs, "*" ~ rhs) => MUL(lhs, rhs)
    case (lhs, "/" ~ rhs) => DIV(lhs, rhs)
  }

  def factor: Parser[ExpressionNode] = (
    prefix
    | (bool | integer | decimal | money | date | string)
    | identifier
    | "(" ~> expr <~ ")"
  )

  def prefix: Parser[ExpressionNode] =
    """-|!""".r ~ factor ^^ {
      case op ~ rhs => op match {
        case "-" => NEG(rhs)
        case "!" => NOT(rhs)
      }
    }

  //Ident taken from JavaTokenParsers, equals Java Identifier.
  def identifier: Parser[Identifier] = ident ^^ (s => Identifier(s))

  def string: Parser[StringLiteral] = stringLiteral ^^ (s => StringLiteral(s))

  def bool: Parser[BooleanLiteral] = ("True" | "False") ^^ (x => BooleanLiteral(x.toBoolean))

  def money: Parser[MoneyLiteral] = "€" ~> decimal ^^ (x => MoneyLiteral(x.value))

  def decimal: Parser[DecimalLiteral] = """\d*.\d+""".r ^^ (x => DecimalLiteral(BigDecimal(x)))

  def integer: Parser[IntegerLiteral] = """\d+""".r ^^ (x => IntegerLiteral(BigDecimal(x).setScale(0)))

  def date: Parser[DateLiteral] = """\d\d\d\d-\d\d-\d\d""".r ^^ (x => DateLiteral(dateFormat.parse(x)))

  private def infixOperationParser(child: Parser[ExpressionNode], ops: Regex)(f: InfixMatcher): Parser[ExpressionNode] = {
    child ~ rep(ops ~ child) ^^ { case head ~ tail => tail.foldLeft(head)(f) }
  }
}
