package parser

import parser.ast._

import scala.annotation.tailrec
import scala.util.parsing.combinator.JavaTokenParsers

/**
  * Created by jasper on 07/02/17.
  */
trait ExpressionParser extends JavaTokenParsers {
  def expr: Parser[ExpressionNode] = comp ~ rep("""\&\&|\|\|""" ~ comp) ^^ {
    case lhs ~ tail => parseList(lhs, tail.map(x => (x._1, x._2)))
  }

  def comp: Parser[ExpressionNode] = addSub ~ rep(""">|<|>=|<=|!=|==""".r ~ addSub) ^^ {
    case lhs ~ tail => parseList(lhs, tail.map(x => (x._1, x._2)))
  }

  def addSub: Parser[ExpressionNode] = term ~ rep("""\+|-""".r ~ term) ^^ {
    case lhs ~ tail => parseList(lhs, tail.map(x => (x._1, x._2)))
  }

  def term: Parser[ExpressionNode] = factor ~ rep("""\*|/""".r ~ factor) ^^ {
    case lhs ~ tail => parseList(lhs, tail.map(x => (x._1, x._2)))
  }


  def factor: Parser[ExpressionNode] = (
    value
      | """\+|-|!""".r ~ value ^^ {
      case op ~ value => PrefixOperation(op, value);
    }
    )

  def value: Parser[ExpressionNode] = (
    ident ^^ (s => Identifier(s))
      | "(" ~> expr <~ ")"
      | integer
    )

  def integer: Parser[Value] = """\d+""".r ^^ (x => Value(x.toInt))


  private def parseList(lhs: ExpressionNode, list: List[(String, ExpressionNode)]): ExpressionNode = {
    list match {
      case Nil => lhs
      case head :: tail => InfixOperation(lhs, head._1, parseList(head._2, tail));
    }
  }
}


object ExpressionParser extends ExpressionParser{
  def main(args: Array[String]) = {
    val input = "(-(+1++1)*22-+50/+300) + BLA"
    println(parseAll(expr, input))
  }
}