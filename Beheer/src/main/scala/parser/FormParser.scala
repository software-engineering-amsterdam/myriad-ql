package parser

import java.io.{ Reader, StringReader }

import ast._
import model.FormModel

import scala.util.parsing.combinator.JavaTokenParsers

class FormParser extends JavaTokenParsers with ExpressionParser {
  def parseForm(input: Reader): Form = {
    parseAll(form, input) match {
      case Success(result, _) => result
      case failure: NoSuccess => scala.sys.error(failure.msg)
    }
  }

  private def typeName: Parser[Type] = (
    "boolean" ^^^ BooleanType
    | "string" ^^^ StringType
    | "integer" ^^^ IntegerType
    | "date" ^^^ DateType
    | "decimal" ^^^ DecimalType
    | "money" ^^^ MoneyType
  )

  private def label: Parser[String] = stringLiteral ^^ (x => x.stripPrefix("\"").stripSuffix("\""))

  private def question: Parser[Question] =
    ident ~ ":" ~ label ~ typeName ~ opt("(" ~> expression <~ ")") ^^ {
      case identifier ~ ":" ~ label ~ typeName ~ expr =>
        Question(identifier, label, typeName, expr)
    }

  private def conditional: Parser[Conditional] =
    "if" ~> "(" ~ expression ~ ")" ~ block ^^ {
      case "(" ~ expression ~ ")" ~ block => Conditional(expression, block)
    }

  private def statement: Parser[Statement] = conditional | question

  private def block: Parser[Block] = "{" ~> rep(statement) <~ "}" ^^ (xs => Block(xs))

  private def form: Parser[Form] =
    "form" ~> ident ~ block ^^ {
      case ident ~ block => Form(ident, block)
    }
}

object FormParser {
  def apply(input: String): FormModel = apply(new StringReader(input))

  def apply(input: Reader): FormModel = new FormModel(new FormParser().parseForm(input))
}
