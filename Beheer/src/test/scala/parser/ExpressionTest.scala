package parser

import ast._
import org.scalatest.PropSpec
import org.scalatest.prop.PropertyChecks
import parser.generators.ExpressionGenerator

class ExpressionParserTest extends PropSpec with PropertyChecks {
  private val expressions = ExpressionGenerator.genExpression suchThat (_.nonEmpty)
  private val parser = ConcreteExpressionParser

  property("Add operator match") {
    forAll(expressions) {
      e: String =>
        operatorCount(e, """\+""") == nodeCount(parser.parseExpression(e), {
          case Add(_, _) => true
          case _ => false
        })
    }
  }
  property("Minus operator match") {
    forAll(expressions) {
      e: String =>
        operatorCount(e, "-") == nodeCount(parser.parseExpression(e), {
          case Neg(_) => true
          case Sub(_, _) => true
          case _ => false
        })
    }
  }
  property("Div operator match") {
    forAll(expressions) {
      e: String =>
        operatorCount(e, """/""") == nodeCount(parser.parseExpression(e), {
          case Div(_, _) => true
          case _ => false
        })
    }
  }

  property("Mul operator match)") {
    forAll(expressions) {
      e: String =>
        operatorCount(e, """\*""") == nodeCount(parser.parseExpression(e), {
          case Mul(_, _) => true
          case _ => false
        })
    }
  }
  property("Not operator match") {
    forAll(expressions) {
      e: String =>
        operatorCount(e, "!") - operatorCount(e, "!=") == nodeCount(parser.parseExpression(e), {
          case Not(_) => true
          case _ => false
        })
    }
  }
  property("And operator match") {
    forAll(expressions) {
      e: String =>
        operatorCount(e, """\&\&""") == nodeCount(parser.parseExpression(e), {
          case And(_, _) => true
          case _ => false
        })
    }
  }
  property("Or operator match") {
    forAll(expressions) {
      e: String =>
        operatorCount(e, """\|\|""") == nodeCount(parser.parseExpression(e), {
          case Or(_, _) => true
          case _ => false
        })
    }
  }
  property("G/GE operator match") {
    forAll(expressions) {
      e: String =>
        operatorCount(e, ">") == nodeCount(parser.parseExpression(e), {
          case Gt(_, _) => true
          case Geq(_, _) => true
          case _ => false
        })
    }
  }
  property("L/LE operator match") {
    forAll(expressions) {
      e: String =>
        operatorCount(e, "<") == nodeCount(parser.parseExpression(e), {
          case Lt(_, _) => true
          case Leq(_, _) => true
          case _ => false
        })
    }
  }
  property("Equals operator match") {
    forAll(expressions) {
      e: String =>
        operatorCount(e, "==") == nodeCount(parser.parseExpression(e), {
          case Eq(_, _) => true
          case _ => false
        })
    }
  }
  property("Not equals operator match") {
    forAll(expressions) {
      e: String =>
        operatorCount(e, "!=") == nodeCount(parser.parseExpression(e), {
          case Neq(_, _) => true
          case _ => false
        })
    }
  }

  private def operatorCount(input: String, operator: String): Int = input match {
    case "" => 0
    case in => operator.r.findAllIn(in).length
  }

  private def nodeCount(expressionNode: ExpressionNode, matcher: ExpressionNode => Boolean): Int = {
    lazy val childResult = expressionNode match {
      case i: InfixNode => nodeCount(i.lhs, matcher) + nodeCount(i.rhs, matcher)
      case p: PrefixNode => nodeCount(p.operand, matcher)
      case _ => 0
    }
    (if (matcher(expressionNode)) 1 else 0) + childResult
  }
}

object ConcreteExpressionParser extends ExpressionParser {
  def parseExpression(expr: String): ExpressionNode = {
    parseAll(expression, expr) match {
      case Success(expr, _) => expr
      case failure: NoSuccess => sys.error(s"invalid expression passed to parser; $failure")
    }
  }
}
