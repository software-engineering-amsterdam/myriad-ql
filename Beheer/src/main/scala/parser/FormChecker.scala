package parser

import model.{ ComputedQuestion, OpenQuestion }
import parser.ast._

import scala.annotation.tailrec

class FormChecker(form: Form) extends Checker[model.Form] {
  private lazy val questionsWithShowConditions: Seq[(ast.Question, Seq[ExpressionNode])] = form.block.statements.flatMap(flattenForm(_, Nil))
  private lazy val questions: Seq[ast.Question] = questionsWithShowConditions.map { case (q, _) => q }
  private lazy val questionLabels: Seq[String] = questions.map(_.label)
  private lazy val questionIdentifiers: Seq[String] = questions.map(_.identifier)
  private lazy val questionsWithReferences: Map[String, Set[String]] = questionsWithShowConditions.map {
    case (Question(identifier, _, _, None), conditionals) => (identifier, extractIdentifiers(conditionals))
    case (Question(identifier, _, _, Some(expr)), conditionals) => (identifier, extractIdentifiers(expr) ++ extractIdentifiers(conditionals))
  }.toMap
  //private lazy val allExpressions: Seq[ast.ExpressionNode] = questionsWithShowConditions.flatMap {
  //  case (Question(_,_,_, None), conditionals) => conditionals
  //  case (Question(_,_,_, Some(expr)), conditionals) => expr +: conditionals
  //}

  //private lazy val checkedExpressions: Map[ast.ExpressionNode, Either[Errors, (model.Expression, Warnings)]] = allExpressions.map(e =>(e, new ExpressionChecker(e, questions)).check())

  private lazy val errors = List(duplicateIdentifiers, undefinedReferences, dependencyCycles)
  private lazy val warnings = List(duplicateLabels)

  def check: Either[Seq[Issue], (model.Form, Seq[Warning])] = {
    errors.flatten match {
      case Nil => Right((buildFormModel, warnings.flatten))

      case e => Left(e ++ warnings.flatten)
    }
  }

  private def buildFormModel: model.Form = {
    val questions = questionsWithShowConditions.map {
      case (Question(identifier, label, typeName, None), conditionals) => model.OpenQuestion(identifier, label, Nil, getTypeModel(typeName.typeName))
      case (Question(identifier, label, typeName, Some(expr)), conditionals) => model.ComputedQuestion(identifier, label, Nil, getTypeModel(typeName.typeName), model.BooleanLiteral(true))
    }
    model.Form(questions)
  }

  private def getTypeModel(typeName: String) = typeName match {
    case "boolean" => model.Boolean
    case "string" => model.String
    case "integer" => model.Integer
    case "date" => model.Date
    case "decimal" => model.Decimal
    case "money" => model.Money
    case _ => sys.error(s"Type parser should not recognise encountered $typeName.")
  }

  private def duplicateIdentifiers: Errors =
    questionIdentifiers.diff(questionIdentifiers.distinct).distinct.map(i => Error(s"Duplicate identifier: $i"))

  private def undefinedReferences: Errors = {
    val allReferencedIdentifiers = questionsWithReferences.values.flatten.toSet
    (allReferencedIdentifiers -- questionIdentifiers.toSet).map(i => Error(s"Undefined reference: $i")).toSeq
  }

  private def dependencyCycles: Errors =
    questionIdentifiers.filter(findCycle).map(q => Error(s"Dependency cycle in question: $q"))

  private def findCycle(rootIdentifier: String): Boolean = {
    @tailrec
    def findCycleInLayer(currentLayer: Set[String], visited: Set[String]): Boolean = {
      lazy val newVisited = visited ++ currentLayer
      lazy val nextLayer = currentLayer.flatMap(questionsWithReferences.getOrElse(_, Set.empty) -- visited)

      currentLayer.nonEmpty && (currentLayer.contains(rootIdentifier) || findCycleInLayer(nextLayer, newVisited))
    }

    findCycleInLayer(questionsWithReferences.getOrElse(rootIdentifier, Set.empty), Set.empty)
  }

  private def duplicateLabels: Warnings =
    questionLabels.diff(questionLabels.distinct).distinct.map(l => Warning(s"Duplicate label: $l"))

  private def flattenForm(statement: Statement, conditionals: Seq[ExpressionNode]): Seq[(ast.Question, Seq[ExpressionNode])] = statement match {
    case Conditional(condition, Block(statements)) => statements.flatMap(flattenForm(_, condition +: conditionals))
    case q: ast.Question => Seq((q, conditionals))
  }

  private def extractIdentifiers(expressionNodes: Seq[ExpressionNode]): Set[String] = expressionNodes match {
    case Nil => Set.empty
    case e :: es => extractIdentifiers(e) ++ extractIdentifiers(es)
  }

  private def extractIdentifiers(expressionNode: ExpressionNode): Set[String] = expressionNode match {
    case Identifier(value) => Set(value)
    case PrefixOperation(_, rhs) => extractIdentifiers(rhs)
    case InfixOperation(lhs, _, rhs) => extractIdentifiers(lhs) ++ extractIdentifiers(rhs)
    case _ => Set.empty
  }

}

object FormChecker {
  def apply(form: Form): Either[Seq[Issue], (model.Form, Seq[Warning])] = new FormChecker(form).check
}