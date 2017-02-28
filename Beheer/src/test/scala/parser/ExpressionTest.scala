package parser

import ast._
import org.scalatest.PropSpec
import org.scalatest.prop.PropertyChecks
import parser.generators.ExpressionGenerator

class ExpressionParserTest extends PropSpec with PropertyChecks {
  type NodeRel = Set[(ExpressionNode, ExpressionNode)]
  private val fullExpressions = ExpressionGenerator.genExpression suchThat (_.nonEmpty)
  private val infixExpressions = ExpressionGenerator.genInfixExpression suchThat (_.nonEmpty)
  private val parser = ConcreteExpressionParser

  property("Add operator match") {
    operatorCountProperty("\\+") {
      case ADD(_, _) => true
      case _ => false
    }
  }
  property("Minus operator match") {
    operatorCountProperty("-") {
      case NEG(_) => true
      case SUB(_, _) => true
      case _ => false
    }
  }
  property("Div operator match") {
    operatorCountProperty("/") {
      case DIV(_, _) => true
      case _ => false
    }
  }
  property("Mul operator match)") {
    operatorCountProperty("\\*") {
      case MUL(_, _) => true
      case _ => false
    }
  }
  property("Not operator match") {
    operatorCountProperty("!") {
      case NOT(_) => true
      case NEQ(_, _) => true
      case _ => false
    }
  }
  property("And operator match") {
    operatorCountProperty("&&") {
      case AND(_, _) => true
      case _ => false
    }
  }
  property("Or operator match") {
    operatorCountProperty("\\|\\|") {
      case OR(_, _) => true
      case _ => false
    }
  }
  property("G/GE operator match") {
    operatorCountProperty(">") {
      case GT(_, _) => true
      case GEQ(_, _) => true
      case _ => false
    }
  }
  property("L/LE operator match") {
    operatorCountProperty("<") {
      case LT(_, _) => true
      case LEQ(_, _) => true
      case _ => false
    }
  }
  property("Equals operator match") {
    operatorCountProperty("==") {
      case EQ(_, _) => true
      case _ => false
    }
  }

  property("Operator precedence add/sub") {
    forAll(infixExpressions) {
      e: String =>
        {
          val nodeToChildren = trCls(flattenExpressionRelations(parser.parseExpression(e)))
          val invalidRelations = nodeToChildren.filter {
            case (ADD(_, _), ADD(_, _)) => false
            case (ADD(_, _), SUB(_, _)) => false
            case (SUB(_, _), SUB(_, _)) => false
            case (SUB(_, _), ADD(_, _)) => false
            case (ADD(_, _), _: InfixNode) => true
            case (SUB(_, _), _: InfixNode) => true
            case (_, _) => false
          }
          invalidRelations.isEmpty
        }
    }
  }

  private def trCls(input: NodeRel): NodeRel = {
    def trClsRec(rel: NodeRel): NodeRel = {
      val res = rel ++ (for ((x, y) <- rel; (a, b) <- input if a == y) yield (x, b))
      if (res == rel) res else trClsRec(res)
    }
    trClsRec(input)
  }
  private def operatorCountProperty(operator: String)(matcher: ExpressionNode => Boolean) =
    forAll(fullExpressions) {
      e: String => operatorCount(e, operator) == nodeCount(parser.parseExpression(e), matcher)
    }

  private def operatorCount(input: String, operator: String): Int = operator.r.findAllIn(input).length

  private def flattenExpressionRelations(expressionNode: ExpressionNode): NodeRel = expressionNode match {
    case i: InfixNode => Set((i, i.lhs), (i, i.rhs)) ++ flattenExpressionRelations(i.lhs) ++ flattenExpressionRelations(i.rhs)
    case p: PrefixNode => Set((p, p.rhs)) ++ flattenExpressionRelations(p.rhs)
    case _ => Set.empty
  }

  private def nodeCount(expressionNode: ExpressionNode, matcher: ExpressionNode => Boolean): Int = {
    val childResult = expressionNode match {
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
