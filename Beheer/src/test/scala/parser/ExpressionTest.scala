package parser

import ast._
import org.scalatest.PropSpec
import org.scalatest.prop.PropertyChecks
import parser.generators.ExpressionGenerator

class ExpressionParserTest extends PropSpec with PropertyChecks {
  type NodePair = (ExpressionNode, ExpressionNode)
  type NodeRel = Set[NodePair]
  private val fullExpressions = ExpressionGenerator.genExpression suchThat (_.nonEmpty)
  private val infixExpressions = ExpressionGenerator.genInfixExpression suchThat (_.nonEmpty)
  private val parser = ConcreteExpressionParser

  property("Add operator match") {
    operatorCountProperty("\\+") {
      case Add(_, _) => true
      case _ => false
    }
  }
  property("Minus operator match") {
    operatorCountProperty("-") {
      case Neg(_) => true
      case Sub(_, _) => true
      case _ => false
    }
  }
  property("Div operator match") {
    operatorCountProperty("/") {
      case Div(_, _) => true
      case _ => false
    }
  }
  property("Mul operator match)") {
    operatorCountProperty("\\*") {
      case Mul(_, _) => true
      case _ => false
    }
  }
  property("Not operator match") {
    operatorCountProperty("!") {
      case Not(_) => true
      case Neq(_, _) => true
      case _ => false
    }
  }
  property("And operator match") {
    operatorCountProperty("&&") {
      case And(_, _) => true
      case _ => false
    }
  }
  property("Or operator match") {
    operatorCountProperty("\\|\\|") {
      case Or(_, _) => true
      case _ => false
    }
  }
  property("G/GE operator match") {
    operatorCountProperty(">") {
      case Gt(_, _) => true
      case Geq(_, _) => true
      case _ => false
    }
  }
  property("L/LE operator match") {
    operatorCountProperty("<") {
      case Lt(_, _) => true
      case Leq(_, _) => true
      case _ => false
    }
  }
  property("Equals operator match") {
    operatorCountProperty("==") {
      case Eq(_, _) => true
      case _ => false
    }
  }

  property("Operator precedence.") {
    forAll(infixExpressions) {
      e: String =>
        {
          val nodeRelations = trCls(flattenExpressionRelations(parser.parseExpression(e)))
          nodeRelations.filter(isValidNodeRelation) == nodeRelations
        }
    }
  }

  private def isValidNodeRelation(nodePair: NodePair): Boolean = {
    def arithmeticChildAllowed(n: ExpressionNode): Boolean = n match {
      case Add(_, _) => true
      case Sub(_, _) => true
      case _: InfixNode => false
      case _ => true
    }

    def mulDivChildAllowed(n: ExpressionNode): Boolean = n match {
      case Mul(_, _) => true
      case Div(_, _) => true
      case e => arithmeticChildAllowed(e)
    }

    def comparisonChildAllowed(n: ExpressionNode): Boolean = n match {
      case Neq(_, _) => true
      case Geq(_, _) => true
      case Leq(_, _) => true
      case Eq(_, _) => true
      case Lt(_, _) => true
      case Gt(_, _) => true
      case e => mulDivChildAllowed(e)
    }

    def logicalChildAllowed(n: ExpressionNode): Boolean = n match {
      case And(_, _) => true
      case Or(_, _) => true
      case e => comparisonChildAllowed(e)
    }

    nodePair match {
      case (Add(_, _), child) => arithmeticChildAllowed(child)
      case (Sub(_, _), child) => arithmeticChildAllowed(child)
      case (Mul(_, _), child) => mulDivChildAllowed(child)
      case (Div(_, _), child) => mulDivChildAllowed(child)
      case (Neq(_, _), child) => comparisonChildAllowed(child)
      case (Geq(_, _), child) => comparisonChildAllowed(child)
      case (Leq(_, _), child) => comparisonChildAllowed(child)
      case (Eq(_, _), child) => comparisonChildAllowed(child)
      case (Gt(_, _), child) => comparisonChildAllowed(child)
      case (Lt(_, _), child) => comparisonChildAllowed(child)
      case (And(_, _), child) => logicalChildAllowed(child)
      case (Or(_, _), child) => logicalChildAllowed(child)
      case _ => true //non infix, not relevant for the test, so always 'valid'.
    }
  }

  private def trCls(input: NodeRel): NodeRel = {
    def trClsHelper(rel: NodeRel): NodeRel = {
      val res = rel ++ (for ((x, y) <- rel; (a, b) <- input if a == y) yield (x, b))
      if (res == rel) res else trClsHelper(res)
    }
    trClsHelper(input)
  }

  private def flattenExpressionRelations(expressionNode: ExpressionNode): NodeRel = expressionNode match {
    case i: InfixNode => Set((i, i.lhs), (i, i.rhs)) ++ flattenExpressionRelations(i.lhs) ++ flattenExpressionRelations(i.rhs)
    case p: PrefixNode => Set((p, p.operand)) ++ flattenExpressionRelations(p.operand)
    case _ => Set.empty
  }

  private def operatorCountProperty(operator: String)(matcher: ExpressionNode => Boolean) =
    forAll(fullExpressions) {
      e: String => operatorCount(e, operator) == nodeCount(parser.parseExpression(e), matcher)
    }

  private def operatorCount(input: String, operator: String): Int = operator.r.findAllIn(input).length

  private def nodeCount(expressionNode: ExpressionNode, matcher: ExpressionNode => Boolean): Int = {
    val childResult = expressionNode match {
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
