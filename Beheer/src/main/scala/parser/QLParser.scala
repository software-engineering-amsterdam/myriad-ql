package parser

import ast._

import scala.util.parsing.combinator.JavaTokenParsers

trait QLParser extends JavaTokenParsers {
  def block[T](parser: Parser[T]) = "{" ~> parser <~ "}"

  def parentheses[T](parser: Parser[T]) = "(" ~> parser <~ ")"

  def label: Parser[String] = stringLiteral ^^ (s => s.stripPrefix("\"").stripSuffix("\""))

  def typeName: Parser[Type] = (
    "boolean" ^^^ BooleanType
    | "string" ^^^ StringType
    | "integer" ^^^ IntegerType
    | "date" ^^^ DateType
    | "decimal" ^^^ DecimalType
    | "money" ^^^ MoneyType
  )
}
