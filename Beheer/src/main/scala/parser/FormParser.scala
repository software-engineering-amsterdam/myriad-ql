package parser

import java.io.InputStreamReader

import parser.ast._

import scala.util.parsing.combinator.JavaTokenParsers

/**
  * Created by jasper on 07/02/17.
  */
class FormParser extends JavaTokenParsers with ExpressionParser {
  def typeName: Parser[String] = (
    "boolean"
      | "string"
      | "integer"
      | "date"
      | "decimal"
      | "money"
    )

  def label: Parser[String] = stringLiteral

  def typeDeclaration: Parser[TypeDeclaration] =
    typeName ~ opt("(" ~> expr <~ ")") ^^ {
      case name ~ None => BareType(name)
      case name ~ Some(value) => ValueType(name, value)
    }

  def question: Parser[Question] =
    ident ~ ":" ~ label ~ typeDeclaration ^^ {
      case identifier ~ ":" ~ label ~ typeDeclaration =>
        Question(identifier, label, typeDeclaration)
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
}

object FormParser extends FormParser {
  def apply(input: InputStreamReader): Form = parseAll(form, input) match {
    case Success(result, _) => result
    case failure: NoSuccess => scala.sys.error(failure.msg)
  }

  def apply(input: String): Form = parseAll(form, input) match {
    case Success(result, _) => result
    case failure: NoSuccess => scala.sys.error(failure.msg)
  }
}
