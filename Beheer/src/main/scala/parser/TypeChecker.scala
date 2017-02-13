package parser

import model.{ ComputedQuestion, OpenQuestion }
import parser.ast._
import scala.language.postfixOps

class TypeChecker {

  def checkIdentifiers(questions: Seq[model.Question]): Either[String, (model.Form, Seq[String])] = {
    val identifiers = questions.map(_.identifier)
    val duplicates = identifiers.diff(identifiers.distinct).distinct
    if (duplicates.nonEmpty) {
      Left(s"Duplicate identifier(s) found: ${duplicates.mkString("\n", "\n", "")}")
    } else {
      val labels = questions.map(_.label)
      val warnings = labels.diff(labels.distinct).distinct.map(l => s"Duplicate label: $l")
      val references = checkReferences(questions)
      references match {
        case Left(e) => Left(s"Reference(s) to undefined identifier found: ${e.mkString("\n", "\n", "")}")
        case Right(_) =>
          Right((model.Form(questions), warnings))
      }
    }
  }

  def checkReferences(questions: Seq[model.Question]): Either[Seq[String], Boolean] = {
    val referencedIdentifiers = questions.flatMap {
      case OpenQuestion(_, _, show, _) => show
      case ComputedQuestion(_, _, show, _, value) => value +: show
    }.flatMap(extractIdentifiers).distinct.toSet
    val definedIdentifiers = questions.map(_.identifier).distinct.toSet
    referencedIdentifiers -- definedIdentifiers toSeq match {
      case Nil => Right(true)
      case list => Left(list)
    }
  }

  private def buildModel(statement: Statement, conditionals: Seq[ExpressionNode]): Seq[model.Question] = statement match {
    case Conditional(condition, Block(statements)) => statements.flatMap(buildModel(_, condition +: conditionals))
    case Question(identifier, label, typename, None) => Seq(OpenQuestion(identifier, label, conditionals, typename))
    case Question(identifier, label, typename, Some(expr)) => Seq(ComputedQuestion(identifier, label, conditionals, typename, expr))

  }

  private def extractIdentifiers(expressionNode: ExpressionNode): Seq[String] = expressionNode match {
    case Value(_) => Nil
    case Identifier(value) => Seq(value)
    case PrefixOperation(_, rhs) => extractIdentifiers(rhs)
    case InfixOperation(lhs, _, rhs) => extractIdentifiers(lhs) ++ extractIdentifiers(rhs)
  }

}

object TypeChecker {
  def apply(form: Form) = {
    val typeChecker = new TypeChecker
    val questions = form.block.statements.flatMap(typeChecker.buildModel(_, Nil))
    typeChecker.checkIdentifiers(questions)
  }
}