package parser

import java.io.{Reader, StringReader}

import model.FormModel
import parser.ast._

import scala.util.parsing.combinator.JavaTokenParsers

class FormParser extends JavaTokenParsers with ExpressionParser {
  def typeName: Parser[Type] = (
    "boolean" ^^^ Boolean
    | "string" ^^^ String
    | "integer" ^^^ Integer
    | "date" ^^^ Date
    | "decimal" ^^^ Decimal
    | "money" ^^^ Money
  )

  def label: Parser[String] = stringLiteral ^^ (x => x.stripPrefix("\"").stripSuffix("\""))

  def question: Parser[Question] =
    ident ~ ":" ~ label ~ typeName ~ opt("(" ~> expr <~ ")") ^^ {
      case identifier ~ ":" ~ label ~ typeName ~ expr =>
        Question(identifier, label, typeName, expr)
    }

  def conditional: Parser[Conditional] =
    "if" ~> "(" ~ expr ~ ")" ~ block ^^ {
      case "(" ~ expr ~ ")" ~ block => Conditional(expr, block)
    }

  def statement: Parser[Statement] = conditional | question

  def block: Parser[Block] = "{" ~> rep(statement) <~ "}" ^^ (xs => Block(xs))

  def form: Parser[Form] =
    "form" ~> ident ~ block ^^ {
      case ident ~ block => Form(ident, block)
    }

  def parseForm(input: Reader): Form = {
    parseAll(form, input) match {
      case Success(result, _) => result
      case failure: NoSuccess => scala.sys.error(failure.msg)
    }
  }
}

object FormParser {
  def apply(input: String): FormModel = apply(new StringReader(input))

  def apply(input: Reader): FormModel = new FormModel(new FormParser().parseForm(input))
}
