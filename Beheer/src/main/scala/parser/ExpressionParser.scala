package parser

import java.text.SimpleDateFormat

import ast._

import scala.util.matching.Regex
import scala.util.parsing.combinator.JavaTokenParsers

trait ExpressionParser extends JavaTokenParsers {
  type InfixMatcher = (ExpressionNode, ~[String, ExpressionNode]) => ExpressionNode

  //We do one date: yyyy-mm-dd.
  private val dateFormat = new SimpleDateFormat("""yyyy-mm-dd""")

  def expression: Parser[ExpressionNode] = logic

  private def logic: Parser[ExpressionNode] = infixOperationParser(comp, """&&|\|\|""".r) {
    case (lhs, "&&" ~ rhs) => AND(lhs, rhs)
    case (lhs, "||" ~ rhs) => OR(lhs, rhs)
  }

  private def comp: Parser[ExpressionNode] = infixOperationParser(subAdd, """>=|<=|>|<|\!=|==""".r) {
    case (lhs, ">=" ~ rhs) => GEQ(lhs, rhs)
    case (lhs, "<=" ~ rhs) => LEQ(lhs, rhs)
    case (lhs, ">" ~ rhs) => GT(lhs, rhs)
    case (lhs, "<" ~ rhs) => LT(lhs, rhs)
    case (lhs, "!=" ~ rhs) => NEQ(lhs, rhs)
    case (lhs, "==" ~ rhs) => EQ(lhs, rhs)
  }

  private def subAdd: Parser[ExpressionNode] = infixOperationParser(mulDiv, """-|\+""".r) {
    case (lhs, "-" ~ rhs) => SUB(lhs, rhs)
    case (lhs, "+" ~ rhs) => ADD(lhs, rhs)
  }

  private def mulDiv: Parser[ExpressionNode] = infixOperationParser(factor, """\*|/""".r) {
    case (lhs, "*" ~ rhs) => MUL(lhs, rhs)
    case (lhs, "/" ~ rhs) => DIV(lhs, rhs)
  }

  private def factor: Parser[ExpressionNode] = literal | prefix | "(" ~> expression <~ ")"

  private def prefix: Parser[ExpressionNode] =
    """-|!""".r ~ factor ^^ {
      case op ~ rhs => op match {
        case "-" => NEG(rhs)
        case "!" => NOT(rhs)
      }
    }

  private def literal: Parser[ExpressionNode] = numeric | bool | date | string | identifier

  //Ident taken from JavaTokenParsers, equals Java Identifier.
  private def identifier: Parser[Identifier] = ident ^^ (s => Identifier(s))

  private def string: Parser[StringLiteral] = stringLiteral ^^ (s => StringLiteral(s))

  private def bool: Parser[BooleanLiteral] = ("True" | "False") ^^ (x => BooleanLiteral(x.toBoolean))

  private def date: Parser[DateLiteral] = "D" ~> """\d\d\d\d-\d\d-\d\d""".r ^^ (x => DateLiteral(dateFormat.parse(x)))

  //Note: ORDER MATTERS HERE.
  private def numeric: Parser[ExpressionNode] = money | decimal | integer

  private def money: Parser[MoneyLiteral] = "€" ~> decimal ^^ (x => MoneyLiteral(x.value))

  private def decimal: Parser[DecimalLiteral] = """\d*\.\d+""".r ^^ (x => DecimalLiteral(BigDecimal(x)))

  private def integer: Parser[IntegerLiteral] = """\d+""".r ^^ (x => IntegerLiteral(BigDecimal(x).setScale(0)))

  private def infixOperationParser(child: Parser[ExpressionNode], operators: Regex)(f: InfixMatcher): Parser[ExpressionNode] = {
    child ~ rep(operators ~ child) ^^ { case head ~ tail => tail.foldLeft(head)(f) }
  }
}
