package Parser

import scala.util.parsing.combinator.JavaTokenParsers

/**
  * Created by jasper on 07/02/17.
  */
class ExpressionParser extends JavaTokenParsers {
  def comp: Parser[Any] = addSub ~
    rep(">" ~ addSub
      | "<" ~ addSub
      | ">=" ~ addSub
      | "<=" ~ addSub
      | "==" ~ addSub
      | "!=" ~ addSub
    )
  def addSub: Parser[Any] = term ~ rep("+" ~ term | "-" ~ term)
  def term: Parser[Any] = factor ~ rep("*" ~ factor | "/" ~ factor)
  def factor: Parser[Any] = value | "+" ~ value | "-" ~ value | "!" ~ value
  def value: Parser[Any] = "(" ~ expr ~ ")" | integer | ident
  def integer: Parser[Any] = "\\d+".r
  def expr: Parser[Any] = comp ~ rep("&&" ~ comp | "||" ~ comp)
}
