package checker

import model.FormModel

import scala.annotation.tailrec

class FormChecker(db: FormModel) extends Checker {
  lazy val check: Issues = duplicateIdentifiers ++ undefinedReferences ++ dependencyCycles ++ checkExpressions ++ duplicateLabels

  private lazy val undefinedReferences: Errors =
    (db.referencedIdentifiers -- db.definedIdentifiers.toSet).map(i => Error(s"Undefined reference: $i")).toSeq
  private lazy val duplicateIdentifiers: Errors =
    db.definedIdentifiers.diff(db.definedIdentifiers.distinct).distinct.map(i => Error(s"Duplicate identifier: $i"))
  private lazy val duplicateLabels: Warnings =
    db.questionLabels.diff(db.questionLabels.distinct).distinct.map(l => Warning(s"Duplicate label: $l"))
  private lazy val dependencyCycles: Errors =
    db.definedIdentifiers.filter(findCycle).map(q => Error(s"Dependency cycle in question: $q"))
  private lazy val checkExpressions: Issues = db.expressions.flatMap { case (expression, expectedType) => ExpressionChecker(db, expression, expectedType) }

  private def findCycle(rootIdentifier: String): Boolean = {
    @tailrec
    def findCycleInLayer(currentLayer: Set[String], visited: Set[String]): Boolean = {
      lazy val newVisited = visited ++ currentLayer
      lazy val nextLayer = currentLayer.flatMap(db.questionsWithReferences.getOrElse(_, Set.empty) -- visited)

      currentLayer.nonEmpty && (currentLayer.contains(rootIdentifier) || findCycleInLayer(nextLayer, newVisited))
    }

    findCycleInLayer(db.questionsWithReferences.getOrElse(rootIdentifier, Set.empty), Set.empty)
  }
}

object FormChecker {
  def apply(formModel: FormModel): Seq[Issue] = new FormChecker(formModel).check
}