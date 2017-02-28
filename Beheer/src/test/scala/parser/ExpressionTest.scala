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
          case ADD(_, _) => true
          case _ => false
        })
    }
  }
  property("Minus operator match") {
    forAll(expressions) {
      e: String =>
        operatorCount(e, "-") == nodeCount(parser.parseExpression(e), {
          case NEG(_) => true
          case SUB(_, _) => true
          case _ => false
        })
    }
  }
  property("Div operator match") {
    forAll(expressions) {
      e: String =>
        operatorCount(e, """/""") == nodeCount(parser.parseExpression(e), {
          case DIV(_, _) => true
          case _ => false
        })
    }
  }

  property("Mul operator match)") {
    forAll(expressions) {
      e: String =>
        operatorCount(e, """\*""") == nodeCount(parser.parseExpression(e), {
          case MUL(_, _) => true
          case _ => false
        })
    }
  }
  property("Not operator match") {
    forAll(expressions) {
      e: String =>
        operatorCount(e, "!") - operatorCount(e, "!=") == nodeCount(parser.parseExpression(e), {
          case NOT(_) => true
          case _ => false
        })
    }
  }
  property("And operator match") {
    forAll(expressions) {
      e: String =>
        operatorCount(e, """\&\&""") == nodeCount(parser.parseExpression(e), {
          case AND(_, _) => true
          case _ => false
        })
    }
  }
  property("Or operator match") {
    forAll(expressions) {
      e: String =>
        operatorCount(e, """\|\|""") == nodeCount(parser.parseExpression(e), {
          case OR(_, _) => true
          case _ => false
        })
    }
  }
  property("G/GE operator match") {
    forAll(expressions) {
      e: String =>
        operatorCount(e, ">") == nodeCount(parser.parseExpression(e), {
          case GT(_, _) => true
          case GEQ(_, _) => true
          case _ => false
        })
    }
  }
  property("L/LE operator match") {
    forAll(expressions) {
      e: String =>
        operatorCount(e, "<") == nodeCount(parser.parseExpression(e), {
          case LT(_, _) => true
          case LEQ(_, _) => true
          case _ => false
        })
    }
  }
  property("Equals operator match") {
    forAll(expressions) {
      e: String =>
        operatorCount(e, "==") == nodeCount(parser.parseExpression(e), {
          case EQ(_, _) => true
          case _ => false
        })
    }
  }
  property("Not equals operator match") {
    forAll(expressions) {
      e: String =>
        operatorCount(e, "!=") == nodeCount(parser.parseExpression(e), {
          case NEQ(_, _) => true
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
      case p: PrefixNode => nodeCount(p.rhs, matcher)
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
