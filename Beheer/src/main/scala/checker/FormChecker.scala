package checker

import checker.Issue.{ Errors, Issues, Warnings }
import model.FormModel

import scala.annotation.tailrec

class FormChecker(model: FormModel) {
  def check: Issues = duplicateIdentifiers ++ undefinedReferences ++ dependencyCycles ++ checkExpressions ++ duplicateLabels

  private val undefinedReferences: Errors =
    (model.referencedIdentifiers -- model.definedIdentifiers.toSet).map(i => Error(s"Undefined reference: $i")).toSeq
  private val duplicateIdentifiers: Errors =
    model.definedIdentifiers.diff(model.definedIdentifiers.distinct).distinct.map(i => Error(s"Duplicate identifier: $i"))
  private val duplicateLabels: Warnings =
    model.questionLabels.diff(model.questionLabels.distinct).distinct.map(l => Warning(s"Duplicate label: $l"))
  private val dependencyCycles: Errors =
    model.definedIdentifiers.filter(findCycle).map(q => Error(s"Dependency cycle in question: $q"))
  private val checkExpressions: Issues = model.expressions.flatMap {
    case (expression, expectedType) => ExpressionChecker(model.identifiersWithType, expression, expectedType)
  }

  private def findCycle(rootIdentifier: String): Boolean = {
    @tailrec
    def findCycleInLayer(currentLayer: Set[String], visited: Set[String]): Boolean = {
      lazy val newVisited = visited ++ currentLayer
      lazy val nextLayer = currentLayer.flatMap(model.questionsWithReferences.getOrElse(_, Set.empty) -- visited)

      currentLayer.nonEmpty && (currentLayer.contains(rootIdentifier) || findCycleInLayer(nextLayer, newVisited))
    }

    findCycleInLayer(model.questionsWithReferences.getOrElse(rootIdentifier, Set.empty), Set.empty)
  }
}

object FormChecker {
  def apply(formModel: FormModel): Seq[Issue] = new FormChecker(formModel).check
}