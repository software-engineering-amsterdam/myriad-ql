package parser.checker

import model.FormModel
import parser._
import parser.ast._

import scala.annotation.tailrec

class FormChecker(db: FormModel) extends Checker {
  private lazy val errors = List(duplicateIdentifiers, undefinedReferences, dependencyCycles, checkExpressions)
  private lazy val warnings = List(duplicateLabels)

  lazy val check: Issues = {
    errors.flatten ++ warnings.flatten
  }

  private lazy val undefinedReferences: Errors =
    (db.referencedIdentifiers -- db.definedIdentifiers.toSet).map(i => Error(s"Undefined reference: $i")).toSeq

  private lazy val duplicateIdentifiers: Errors =
    db.definedIdentifiers.diff(db.definedIdentifiers.distinct).distinct.map(i => Error(s"Duplicate identifier: $i"))

  private lazy val duplicateLabels: Warnings =
    db.questionLabels.diff(db.questionLabels.distinct).distinct.map(l => Warning(s"Duplicate label: $l"))

  private lazy val dependencyCycles: Errors =
    db.definedIdentifiers.filter(findCycle).map(q => Error(s"Dependency cycle in question: $q"))

  private def findCycle(rootIdentifier: String): Boolean = {
    @tailrec
    def findCycleInLayer(currentLayer: Set[String], visited: Set[String]): Boolean = {
      lazy val newVisited = visited ++ currentLayer
      lazy val nextLayer = currentLayer.flatMap(db.questionsWithReferences.getOrElse(_, Set.empty) -- visited)

      currentLayer.nonEmpty && (currentLayer.contains(rootIdentifier) || findCycleInLayer(nextLayer, newVisited))
    }

    findCycleInLayer(db.questionsWithReferences.getOrElse(rootIdentifier, Set.empty), Set.empty)
  }

  lazy val checkExpressions: Issues =
    db.expressions.flatMap { case (expression, expectedType) => ExpressionChecker(db, expression, expectedType) }

}

object FormChecker {
  def apply(formModel: FormModel): Seq[Issue] = new FormChecker(formModel).check
}

