package parser

import ast._
import org.scalacheck.Gen
import org.scalatest.prop.PropertyChecks
import org.scalatest.{ Inside, Matchers, PropSpec }
import ExpressionGenerator

import scala.annotation.tailrec
import scala.util.matching.Regex

class ExpressionParserPropertyTest extends PropSpec with Inside with Matchers with PropertyChecks {
  type NodePair = (ExpressionNode, ExpressionNode)
  type NodeRel = Set[NodePair]
  private val parser = ConcreteExpressionParser

  private def fullExpressions: Gen[String] = ExpressionGenerator.genExpression suchThat (_.nonEmpty)

  private def infixExpressions: Gen[String] = ExpressionGenerator.genInfixExpression suchThat (_.nonEmpty)

  property("Integer literal scale should be 0") {
    forAll(Gen.numStr suchThat (_.nonEmpty)) {
      num =>
        inside(parser.parseExpression(num)) {
          case IntegerLiteral(decimal) => {
            decimal.scale should be(0)
          }
        }
    }
  }
  property("decimal literal scale should be equal to length of fractional") {
    forAll(Gen.numStr, Gen.numStr suchThat (_.nonEmpty)) {
      (num, fractional) =>
        inside(parser.parseExpression(s"$num.$fractional")) {
          case DecimalLiteral(decimal) => {
            decimal.scale should be(fractional.length)
          }
        }
    }
  }

  property("Add operator count match") {
    operatorCountProperty("\\+".r) {
      case Add(_, _) => 1
    }
  }
  property("Minus operator count match") {
    operatorCountProperty("-".r) {
      case Neg(_) => 1
      case Sub(_, _) => 1
    }
  }
  property("Div operator count match") {
    operatorCountProperty("/".r) {
      case Div(_, _) => 1
    }
  }
  property("Mul operator count match") {
    operatorCountProperty("\\*".r) {
      case Mul(_, _) => 1
    }
  }
  property("Not operator count match") {
    operatorCountProperty("!".r) {
      case Not(_) => 1
      case Neq(_, _) => 1
    }
  }
  property("And operator count match") {
    operatorCountProperty("&&".r) {
      case And(_, _) => 1
    }
  }
  property("Or operator count match") {
    operatorCountProperty("\\|\\|".r) {
      case Or(_, _) => 1
    }
  }
  property("G/GE operator count match") {
    operatorCountProperty(">".r) {
      case Gt(_, _) => 1
      case Geq(_, _) => 1
    }
  }
  property("L/LE operator count match") {
    operatorCountProperty("<".r) {
      case Lt(_, _) => 1
      case Leq(_, _) => 1
    }
  }
  property("Equals operator count match") {
    operatorCountProperty("==".r) {
      case Eq(_, _) => 1
    }
  }

  property("Operator precedence given infixExpression without parentheses.") {
    forAll(infixExpressions) {
      e: String =>
        {
          val nodeRelations = trCls(flattenExpressionRelations(parser.parseExpression(e)))
          nodeRelations.filter(isValidNodeRelation) == nodeRelations
        }
    }
  }

  private def isValidNodeRelation(nodePair: NodePair): Boolean = {
    def arithmeticChildAllowed(child: ExpressionNode): Boolean = child match {
      case Add(_, _) => true
      case Sub(_, _) => true
      case _: InfixNode => false
      case _ => true
    }

    def mulDivChildAllowed(child: ExpressionNode): Boolean = child match {
      case Mul(_, _) => true
      case Div(_, _) => true
      case e => arithmeticChildAllowed(e)
    }

    def comparisonChildAllowed(child: ExpressionNode): Boolean = child match {
      case Neq(_, _) => true
      case Geq(_, _) => true
      case Leq(_, _) => true
      case Eq(_, _) => true
      case Lt(_, _) => true
      case Gt(_, _) => true
      case e => mulDivChildAllowed(e)
    }

    def logicalChildAllowed(child: ExpressionNode): Boolean = child match {
      case And(_, _) => true
      case Or(_, _) => true
      case e => comparisonChildAllowed(e)
    }

    val (parentNode, child) = nodePair
    parentNode match {
      case Add(_, _) => arithmeticChildAllowed(child)
      case Sub(_, _) => arithmeticChildAllowed(child)
      case Mul(_, _) => mulDivChildAllowed(child)
      case Div(_, _) => mulDivChildAllowed(child)
      case Neq(_, _) => comparisonChildAllowed(child)
      case Geq(_, _) => comparisonChildAllowed(child)
      case Leq(_, _) => comparisonChildAllowed(child)
      case Eq(_, _) => comparisonChildAllowed(child)
      case Gt(_, _) => comparisonChildAllowed(child)
      case Lt(_, _) => comparisonChildAllowed(child)
      case And(_, _) => logicalChildAllowed(child)
      case Or(_, _) => logicalChildAllowed(child)
      case _ => true //non infix, not relevant for the property, so always 'valid'.
    }
  }

  private def trCls(input: NodeRel): NodeRel = {
    def expandRelations(orig: NodeRel, rel: NodeRel): NodeRel =
      for {
        (x, y) <- rel
        (a, b) <- orig if a == y
      } yield (x, b)

    @tailrec
    def trClsHelper(orig: NodeRel, rel: NodeRel): NodeRel = {
      val res = rel ++ expandRelations(orig, rel)
      if (res == rel) res else trClsHelper(orig, res)
    }

    trClsHelper(input, input)
  }

  private def flattenExpressionRelations(expressionNode: ExpressionNode): NodeRel = expressionNode match {
    case i: InfixNode => Set((i, i.lhs), (i, i.rhs)) ++ flattenExpressionRelations(i.lhs) ++ flattenExpressionRelations(i.rhs)
    case p: PrefixNode => Set((p, p.operand)) ++ flattenExpressionRelations(p.operand)
    case _ => Set.empty
  }

  private def operatorCountProperty(operator: Regex)(nodesToCountMatcher: PartialFunction[ExpressionNode, Int]) = {
    val fullMatcher = nodesToCountMatcher orElse { case _ => 0 }: PartialFunction[ExpressionNode, Int]
    forAll(fullExpressions) {
      e: String => operatorCount(e, operator) == nodeCount(parser.parseExpression(e), fullMatcher)
    }
  }

  private def operatorCount(input: String, operatorPattern: Regex): Int = operatorPattern.findAllIn(input).length

  private def nodeCount(expressionNode: ExpressionNode, matcher: ExpressionNode => Int): Int = {
    val childResult = expressionNode match {
      case i: InfixNode => nodeCount(i.lhs, matcher) + nodeCount(i.rhs, matcher)
      case p: PrefixNode => nodeCount(p.operand, matcher)
      case _ => 0
    }
    matcher(expressionNode) + childResult
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
