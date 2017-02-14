package parser

import model.{ComputedQuestion, OpenQuestion}
import parser.ast._

import scala.annotation.tailrec

class FormChecker(form: Form) extends Checker[model.Form] {

  private lazy val questions: Seq[model.Question] = form.block.statements.flatMap(buildModel(_, Nil))
  private lazy val questionLabels: Seq[String] = questions.map(_.label)
  private lazy val questionIdentifiers: Seq[String] = questions.map(_.identifier)
  private lazy val questionExpressions: Map[String, Seq[ExpressionNode]] = questions.map(q => (q.identifier, extractExpressions(q))).toMap
  private lazy val questionReferences: Map[String, Set[String]] = questionExpressions.map {
    case (q, Nil) => (q, Set.empty: Set[String])
    case (q, e) => (q, e.map(extractIdentifiers).reduce(_ ++ _))
  }

  private lazy val errors = List(duplicateIdentifiers, undefinedReferences, dependencyCycles)
  private lazy val warnings = List(duplicateLabels)

  def check: Either[Seq[Issue], (model.Form, Seq[Warning])] = {
    errors.flatten match {
      case Nil => Right((model.Form(questions), warnings.flatten))
      case e => Left(e ++ warnings.flatten)
    }
  }

  private def duplicateIdentifiers: Errors =
    questionIdentifiers.diff(questionIdentifiers.distinct).distinct.map(i => Error(s"Duplicate identifier: $i"))

  private def undefinedReferences: Errors = {
    val allReferencedIdentifiers = questionReferences.values.flatten.toSet
    (allReferencedIdentifiers -- questionIdentifiers.toSet).map(i => Error(s"Undefined reference: $i")).toSeq
  }

  private def dependencyCycles: Errors =
    questionIdentifiers.filter(findCycle).map(q => Error(s"Dependency cycle in question: $q"))

  private def findCycle(rootIdentifier: String): Boolean = {
    @tailrec
    def findCycleInLayer(currentLayer: Set[String], visited: Set[String]): Boolean = {
      lazy val newVisited = visited ++ currentLayer
      lazy val nextLayer = currentLayer.flatMap(questionReferences.getOrElse(_, Set.empty) -- visited)

      currentLayer.nonEmpty && (currentLayer.contains(rootIdentifier) || findCycleInLayer(nextLayer, newVisited))
    }

    findCycleInLayer(questionReferences.getOrElse(rootIdentifier, Set.empty), Set.empty)
  }

  private def duplicateLabels: Warnings =
    questionLabels.diff(questionLabels.distinct).distinct.map(l => Warning(s"Duplicate label: $l"))

  private def buildModel(statement: Statement, conditionals: Seq[ExpressionNode]): Seq[model.Question] = statement match {
    case Conditional(condition, Block(statements)) => statements.flatMap(buildModel(_, condition +: conditionals))
    case Question(identifier, label, typename, None) => Seq(OpenQuestion(identifier, label, conditionals, typename))
    case Question(identifier, label, typename, Some(expr)) => Seq(ComputedQuestion(identifier, label, conditionals, typename, expr))
  }

  private def extractExpressions(question: model.Question): Seq[ExpressionNode] = question match {
    case OpenQuestion(_, _, show, _) => show
    case ComputedQuestion(_, _, show, _, value) => value +: show
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