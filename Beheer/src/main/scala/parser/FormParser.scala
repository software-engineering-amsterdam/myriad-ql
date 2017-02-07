package parser

import scala.util.parsing.combinator.JavaTokenParsers

/**
  * Created by jasper on 07/02/17.
  */
object formParser extends JavaTokenParsers with ExpressionParser {
  def typeName: Parser[Any] = (
    "boolean"
      | "string"
      | "integer"
      | "date"
      | "decimal"
      | "money"
    )

  def label: Parser[Any] = stringLiteral

  def typeDecl: Parser[Any] = typeName ~ opt("(" ~ expr ~ ")")

  def question: Parser[Any] = ident ~ ":" ~ label ~ typeDecl

  def conditional: Parser[Any] = "if" ~ "(" ~ expr ~ ")" ~ block

  def statement: Parser[Any] = conditional | question

  def block: Parser[Any] = "{" ~> rep(statement) <~ "}"

  def form: Parser[Any] = "form" ~ ident ~ block

  def apply(input: String): Any = parseAll(form, input) match {
    case Success(result, _) => result
    case failure: NoSuccess => scala.sys.error(failure.msg)
  }

  def main(args: Array[String]) = {
    val input =
      """
        form Box1HouseOwning {
          hasSoldHouse: "Did you sell a house in 2010?" boolean
          hasBoughtHouse: "Did you by a house in 2010?" boolean
          hasMaintLoan: "Did you enter a loan for maintenance/reconstruction?" boolean
          if (hasSoldHouse) {
            sellingPrice: "Price the house was sold for:" money(20+-(-1++33*-4))
            privateDebt: "Private debts for the sold house:" money(200)
            valueResidue: "Value residue:" money(sellingPrice - privateDebt-5)
          }
        }
      """
    val input1 =
      """
      form ident {
      }
      """

    println(apply(input))
  }
}

// boolean, string, integer, date and decimal and money/currency