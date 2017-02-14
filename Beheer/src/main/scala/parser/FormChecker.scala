package parser

import model.{ ComputedQuestion, OpenQuestion }
import parser.ast._

import scala.annotation.tailrec

sealed trait Issue
sealed trait Warning extends Issue
case class DuplicateLabel(label: String) extends Warning

sealed trait Error extends Issue
case class DuplicateIdentifier(identifier: String) extends Error
case class UndefinedReference(identifier: String) extends Error
case class CyclicDependency(question: String) extends Error

class FormChecker(form: Form) {
  private lazy val questions: Seq[model.Question] = form.block.statements.flatMap(buildModel(_, Nil))
  private lazy val questionLabels: Seq[String] = questions.map(_.label)
  private lazy val questionIdentifiers: Seq[String] = questions.map(_.identifier)
  private lazy val questionReferences: Map[String, Set[String]] = questions.map(q => (q.identifier, extractExpressions(q).flatMap(e => extractIdentifiers(e)).toSet)).toMap

  private lazy val errors = List(duplicateIdentifiers, undefinedReferences, dependencyCycles)
  private lazy val warnings = List(duplicateLabels)

  def check: Either[Seq[Issue], (model.Form, Seq[Warning])] = {
    errors.flatten match {
      case Nil => Right((model.Form(questions), warnings.flatten))
      case e => Left(e ++ warnings.flatten)
    }
  }

  private def duplicateIdentifiers: Seq[DuplicateIdentifier] =
    questionIdentifiers.diff(questionIdentifiers.distinct).distinct.map(i => DuplicateIdentifier(i))

  private def duplicateLabels: Seq[DuplicateLabel] =
    questionLabels.diff(questionLabels.distinct).distinct.map(i => DuplicateLabel(i))

  private def undefinedReferences: Seq[UndefinedReference] = {
    val allReferencedIdentifiers = questionReferences.values.flatten.toSet
    (allReferencedIdentifiers -- questionIdentifiers.toSet).toSeq.map(i => UndefinedReference(i))
  }

  private def dependencyCycles: Seq[CyclicDependency] =
    questionIdentifiers.filter(findCycle).map(q => CyclicDependency(q))

  private def findCycle(rootIdentifier: String): Boolean = {
    @tailrec
    def findCycleInLayer(currentLayer: Seq[String], visited: Set[String]): Boolean = {
      currentLayer match {
        case Nil => false
        case ids =>
          lazy val newVisited = visited ++ ids
          lazy val nextLayer = ids.flatMap(questionReferences.getOrElse(_, Set.empty) -- visited)
          ids.contains(rootIdentifier) || findCycleInLayer(nextLayer, newVisited)
      }
    }
    findCycleInLayer(questionReferences.getOrElse(rootIdentifier, Set.empty).toSeq, Set.empty)
  }

  private def buildModel(statement: Statement, conditionals: Seq[ExpressionNode]): Seq[model.Question] = statement match {
    case Conditional(condition, Block(statements)) => statements.flatMap(buildModel(_, condition +: conditionals))
    case Question(identifier, label, typename, None) => Seq(OpenQuestion(identifier, label, conditionals, typename))
    case Question(identifier, label, typename, Some(expr)) => Seq(ComputedQuestion(identifier, label, conditionals, typename, expr))
  }

  private def extractExpressions(question: model.Question): Seq[ExpressionNode] = question match {
    case OpenQuestion(_, _, show, _) => show
    case ComputedQuestion(_, _, show, _, value) => value +: show
  }

  private def extractIdentifiers(expressionNode: ExpressionNode): Seq[String] = expressionNode match {
    case Identifier(value) => Seq(value)
    case PrefixOperation(_, rhs) => extractIdentifiers(rhs)
    case InfixOperation(lhs, _, rhs) => extractIdentifiers(lhs) ++ extractIdentifiers(rhs)
    case _ => Nil
  }

}

object FormChecker {
  def apply(form: Form): Either[Seq[Issue], (model.Form, Seq[Warning])] = new FormChecker(form).check
}