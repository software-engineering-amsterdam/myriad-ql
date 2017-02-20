package parser

import javax.xml.crypto.dsig.keyinfo.X509IssuerSerial

import model.{ ComputedQuestion, OpenQuestion }
import parser.ast._

import scala.annotation.tailrec

class AstChecker(db: AstFacts) extends Checker {
  private lazy val errors = List(duplicateIdentifiers, undefinedReferences, dependencyCycles)
  private lazy val warnings = List(duplicateLabels)

  def check: Issues = {
    errors.flatten ++ warnings.flatten
  }

  private def undefinedReferences: Errors =
    (db.referencedIdentifiers -- db.definedIdentifiers.toSet).map(i => Error(s"Undefined reference: $i")).toSeq

  private def duplicateIdentifiers: Errors =
    db.definedIdentifiers.diff(db.definedIdentifiers.distinct).distinct.map(i => Error(s"Duplicate identifier: $i"))

  private def duplicateLabels: Warnings =
    db.questionLabels.diff(db.questionLabels.distinct).distinct.map(l => Warning(s"Duplicate label: $l"))

  private def dependencyCycles: Errors =
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
}

object AstChecker {
  def apply(form: Form): Seq[Issue] = new AstChecker(new AstFacts(form)).check
}
/* private def buildFormModel: model.Form = {
    val questions = db.questionsWithShowConditions.map {
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
  }*/

